package com.example.demo.domain.service;

import com.example.demo.domain.dto.Response.TourismPhotoGalleryResponseDto;
import com.example.demo.domain.entity.Gallery;
import com.example.demo.domain.repository.GalleryRepository;
import com.example.demo.global.error.exception.BusinessLogicException;
import com.example.demo.global.error.exception.ExceptionCode;
import com.example.demo.global.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DataCollectionService {
    private final GalleryRepository galleryRepository;
    private final RestTemplate restTemplate;

    private static final String API_URL = "https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1";

    @Value("${openapi.api-key}")
    private String apiKey;
    public void collectDataFromApi(int totalData) {
        int pageSize = 10;
        int totalPages;
        int totalCount;

        try {
            ResponseEntity<TourismPhotoGalleryResponseDto> initialResponse = makeGalleryApiCall(1, pageSize);

            if (initialResponse.getStatusCode().is2xxSuccessful() && initialResponse.getBody() != null) {
                totalCount = initialResponse.getBody().getResponse().getBody().getTotalCount();
                totalPages = Math.min((int) Math.ceil((double) totalData / pageSize),
                        (int) Math.ceil((double) totalCount / pageSize));

                for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
                    ResponseEntity<TourismPhotoGalleryResponseDto> response = makeGalleryApiCall(pageNo, pageSize);

                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        processGalleryItems(response);
                    }
                }
            } else {
                throw new BusinessLogicException(ExceptionCode.INTERNAL_SERVER_ERROR, "API response error");
            }
        } catch (RestClientException e) {
            throw new BusinessLogicException(ExceptionCode.INTERNAL_SERVER_ERROR, "API call error");
        }
    }

    private ResponseEntity<TourismPhotoGalleryResponseDto> makeGalleryApiCall(int pageNo, int pageSize) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                API_URL + "?serviceKey={serviceKey}&numOfRows={numOfRows}&pageNo={pageNo}&MobileOS={mobileOS}&MobileApp={mobileApp}&arrange={arrange}&_type={type}",
                HttpMethod.GET,
                entity,
                TourismPhotoGalleryResponseDto.class,
                apiKey,
                pageSize,
                pageNo,
                "ETC",
                "AppTest",
                "A",
                "json"
        );
    }

    private void processGalleryItems(ResponseEntity<TourismPhotoGalleryResponseDto> response) {
        List<TourismPhotoGalleryResponseDto.GalleryItem> items = Objects.requireNonNull(response.getBody()).getResponse().getBody().getItems().getItem();
        items.forEach(item -> {
            Optional<Gallery> existingGalleryOptional = galleryRepository.findByGalContentId(item.getGalContentId());
            if (existingGalleryOptional.isPresent()) {
                Gallery existingGallery = existingGalleryOptional.get();
                LocalDateTime existingModifiedTime = existingGallery.getGalModifiedtime();
                LocalDateTime newModifiedTime = DateTimeUtil.parseToLocalDateTime(item.getGalModifiedtime());
                if (newModifiedTime.isAfter(existingModifiedTime)) {
                    existingGallery.updateFromDto(item);
                    galleryRepository.save(existingGallery);
                }
            } else {
                try {
                    galleryRepository.save(item.toEntity());
                } catch (DataIntegrityViolationException e) {
                    throw new BusinessLogicException(ExceptionCode.DUPLICATE_RESOURCE, "Duplicate entry for gallery item with content ID: " + item.getGalContentId());
                }
            }
        });
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledDataCollection() {
        collectDataFromApi(200);
    }

    public void deleteAllData() {
        galleryRepository.deleteAll();
    }
}



package com.example.demo.domain.service;

import com.example.demo.domain.dto.GalleryDto;
import com.example.demo.domain.dto.Request.GalleryCreationRequest;
import com.example.demo.domain.entity.Gallery;
import com.example.demo.domain.repository.GallerySpecifications;
import com.example.demo.global.error.exception.BusinessLogicException;
import com.example.demo.global.error.exception.ExceptionCode;
import com.example.demo.domain.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class GalleryService {


    private final GalleryRepository galleryRepository;

    @Transactional
    public void createGallery(GalleryCreationRequest request) {
        Gallery gallery = Gallery.of(request);
        galleryRepository.save(gallery);
    }

    @Transactional
    public GalleryDto updateGallery(Long id, GalleryCreationRequest request) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.GALLERY_NOT_FOUNT,"해당 갤러리를 찾을 수 없음"));
        gallery.update(request);
        return GalleryDto.from(galleryRepository.save(gallery));
    }

    @Transactional
    public void deleteGallery(Long id) {
        if (!galleryRepository.existsById(id)) {
            throw new BusinessLogicException(ExceptionCode.GALLERY_NOT_FOUNT,"해당 갤러리를 찾을 수 없음");
        }

        galleryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<GalleryDto> galleryList(Pageable pageable){
        return galleryRepository.findAll(pageable).map(GalleryDto::from);
    }

    @Transactional(readOnly = true)
    public Page<GalleryDto> searchGalleries(String keyword, Pageable pageable) {
        Specification<Gallery> spec = Specification.where(GallerySpecifications.hasKeywordAndPhotographer(keyword));
        return galleryRepository.findAll(spec, pageable).map(GalleryDto::from);
    }

}

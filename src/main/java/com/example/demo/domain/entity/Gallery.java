package com.example.demo.domain.entity;

import com.example.demo.domain.dto.Request.GalleryCreationRequest;
import com.example.demo.domain.dto.Response.TourismPhotoGalleryResponseDto;
import com.example.demo.global.utils.DateTimeUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "gallery")
@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id", nullable = false, unique = true)
    private String galContentId;

    @Column(name = "content_type_id", nullable = false)
    private String galContentTypeId;

    @Column(name = "title", nullable = false)
    private String galTitle;

    @Column(name = "web_image_url", nullable = false)
    private String galWebImageUrl;

    @Column(name = "created_time", updatable = false, nullable = false)
    private LocalDateTime galCreatedtime;

    @Column(name = "modified_time")
    private LocalDateTime galModifiedtime;

    @Column(name = "photography_month", nullable = false)
    private String galPhotographyMonth;

    @Column(name = "photography_location", nullable = false)
    private String galPhotographyLocation;

    @Column(name = "photographer", nullable = false)
    private String galPhotographer;

    @Column(name = "search_keyword", nullable = false)
    private String galSearchKeyword;

    public static Gallery of(GalleryCreationRequest request) {
        String galContentId = "c" + UUID.randomUUID().toString();

        return Gallery.builder()
                .galContentId(galContentId)
                .galContentTypeId(request.getGalContentTypeId())
                .galTitle(request.getGalTitle())
                .galWebImageUrl(request.getGalWebImageUrl())
                .galPhotographer(request.getGalPhotographer())
                .galPhotographyLocation(request.getGalPhotographyLocation())
                .galPhotographyMonth(request.getGalPhotographyMonth())
                .galSearchKeyword(request.getGalSearchKeyword())
                .build();
    }

    public void update(GalleryCreationRequest request) {
        this.galContentTypeId = request.getGalContentTypeId();
        this.galTitle = request.getGalTitle();
        this.galWebImageUrl = request.getGalWebImageUrl();
        this.galPhotographer = request.getGalPhotographer();
        this.galPhotographyLocation = request.getGalPhotographyLocation();
        this.galPhotographyMonth = request.getGalPhotographyMonth();
        this.galSearchKeyword = request.getGalSearchKeyword();
        this.galModifiedtime = LocalDateTime.now();
    }
    public void updateFromDto(TourismPhotoGalleryResponseDto.GalleryItem dto) {
        this.galContentTypeId = dto.getGalContentTypeId();
        this.galTitle = dto.getGalTitle();
        this.galWebImageUrl = dto.getGalWebImageUrl();
        this.galCreatedtime = DateTimeUtil.parseToLocalDateTime(dto.getGalCreatedtime());
        this.galModifiedtime = DateTimeUtil.parseToLocalDateTime(dto.getGalModifiedtime());
        this.galPhotographyMonth = dto.getGalPhotographyMonth();
        this.galPhotographyLocation = dto.getGalPhotographyLocation();
        this.galPhotographer = dto.getGalPhotographer();
        this.galSearchKeyword = dto.getGalSearchKeyword();
    }

    @PrePersist
    protected void onPersist() {
        if (this.galCreatedtime == null) {
            this.galCreatedtime = LocalDateTime.now();
        }
        if (this.galModifiedtime == null) {
            this.galModifiedtime = this.galCreatedtime;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.galModifiedtime = LocalDateTime.now();
    }
}

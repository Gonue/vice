package com.example.demo.domain.entity;

import com.example.demo.domain.dto.GalleryUpdateDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @Builder
    public Gallery(String galContentId, String galContentTypeId, String galTitle, String galWebImageUrl,
                   String galPhotographyMonth, String galPhotographyLocation, String galPhotographer,
                   String galSearchKeyword, LocalDateTime galCreatedtime, LocalDateTime galModifiedtime) {
        this.galContentId = galContentId;
        this.galContentTypeId = galContentTypeId;
        this.galTitle = galTitle;
        this.galWebImageUrl = galWebImageUrl;
        this.galPhotographyMonth = galPhotographyMonth;
        this.galPhotographyLocation = galPhotographyLocation;
        this.galPhotographer = galPhotographer;
        this.galSearchKeyword = galSearchKeyword;
        this.galCreatedtime = galCreatedtime;
        this.galModifiedtime = galModifiedtime;
    }

    public void update(GalleryUpdateDto updateDto) {
        this.galContentTypeId = updateDto.getGalContentTypeId();
        this.galTitle = updateDto.getGalTitle();
        this.galWebImageUrl = updateDto.getGalWebImageUrl();
        this.galPhotographyMonth = updateDto.getGalPhotographyMonth();
        this.galPhotographyLocation = updateDto.getGalPhotographyLocation();
        this.galPhotographer = updateDto.getGalPhotographer();
        this.galSearchKeyword = updateDto.getGalSearchKeyword();
        this.galModifiedtime = updateDto.getGalModifiedTime() != null ? updateDto.getGalModifiedTime() : LocalDateTime.now();
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

}

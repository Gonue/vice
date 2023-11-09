package com.example.demo.domain.dto;

import com.example.demo.domain.entity.Gallery;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GalleryDto {
    private Long id;
    private String galContentId;
    private String galContentTypeId;
    private String galTitle;
    private String galWebImageUrl;
    private LocalDateTime galCreatedTime;
    private LocalDateTime galModifiedTime;
    private String galPhotographyMonth;
    private String galPhotographyLocation;
    private String galPhotographer;
    private String galSearchKeyword;

    public static GalleryDto from(Gallery entity){
        return new GalleryDto(
                entity.getId(),
                entity.getGalContentId(),
                entity.getGalContentTypeId(),
                entity.getGalTitle(),
                entity.getGalWebImageUrl(),
                entity.getGalCreatedtime(),
                entity.getGalModifiedtime(),
                entity.getGalPhotographyMonth(),
                entity.getGalPhotographyLocation(),
                entity.getGalPhotographer(),
                entity.getGalSearchKeyword()
        );
    }
}

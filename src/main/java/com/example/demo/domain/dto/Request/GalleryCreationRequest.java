package com.example.demo.domain.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class GalleryCreationRequest {
    @Schema(description = "갤러리 타입 ID", example = "17")
    private String galContentTypeId;
    @Schema(description = "갤러리 제목", example = "더베이")
    private String galTitle;
    @Schema(description = "갤러리 URL", example = "https://image.example.jpg")
    private String galWebImageUrl;
    @Schema(description = "사진 촬영일", example = "202309")
    private String galPhotographyMonth;
    @Schema(description = "촬영장소", example = "부산광역시 영도구 대교동1가")
    private String galPhotographyLocation;
    @Schema(description = "촬영자", example = "김덕배")
    private String galPhotographer;
    @Schema(description = "검색키워드", example = "영도대교, 부산광역시 영도구, 다리, 9월 추천여행지")
    private String galSearchKeyword;
}

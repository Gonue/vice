package com.example.demo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GalleryUpdateDto {

    @Schema(description = "갤러리 타입 ID", example = "17")
    @NotBlank(message = "갤러리 타입 ID는 필수 항목입니다.")
    private String galContentTypeId;

    @Schema(description = "갤러리 제목", example = "더베이")
    @NotBlank(message = "갤러리 제목은 필수 항목입니다.")
    private String galTitle;

    @Schema(description = "갤러리 URL", example = "https://image.example.jpg")
    @NotBlank(message = "갤러리 이미지 URL은 필수 항목입니다.")
    private String galWebImageUrl;

    @Schema(description = "사진 촬영일", example = "202309")
    @NotBlank(message = "갤러리 촬열일은 필수 항목입니다.")
    @Pattern(regexp = "^[0-9]{6}$", message = "사진 촬영일은 'YYYYMM' 형식이어야 합니다.")
    private String galPhotographyMonth;

    @Schema(description = "촬영장소", example = "부산광역시 영도구 대교동1가")
    @NotBlank(message = "촬영 장소는 필수 항목입니다.")
    private String galPhotographyLocation;

    @Schema(description = "촬영자", example = "김덕배")
    @NotBlank(message = "촬영자는 필수 항목입니다.")
    private String galPhotographer;

    @Schema(description = "검색키워드", example = "영도대교, 부산광역시 영도구, 다리, 9월 추천여행지")
    @Size(max = 255, message = "검색 키워드는 255자를 넘을 수 없습니다.")
    private String galSearchKeyword;

    @Schema(hidden = true)
    private LocalDateTime galModifiedTime;
}

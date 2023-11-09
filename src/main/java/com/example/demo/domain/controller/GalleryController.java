package com.example.demo.domain.controller;


import com.example.demo.domain.dto.GalleryDto;
import com.example.demo.domain.dto.GalleryUpdateDto;
import com.example.demo.domain.service.GalleryService;
import com.example.demo.domain.dto.Request.GalleryCreationRequest;
import com.example.demo.global.response.Response;
import com.example.demo.global.utils.SortUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gallery-Controller", description = "갤러리 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    @Operation(summary = "갤러리 생성", description = "새로운 데이터를 추가합니다. \n\n contentTypeId의 경우 주 식별자가 아닌 컨텐츠가 속한 분류 타입입니다.")
    @ApiResponse(responseCode = "200", description = "생성 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content())
    @PostMapping
     public Response<Void> createGallery(@Valid @RequestBody GalleryCreationRequest request) {
         galleryService.createGallery(request);
         return Response.success();
     }

    @Operation(summary = "갤러리 수정", description = "기존 데이터를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content())
    @ApiResponse(responseCode = "404", description = "id 찾을 수 없음", content = @Content())
    @PutMapping("/{id}")
    public Response<GalleryDto> updateGallery(
            @Parameter(description = "갤러리 식별자", required = true)
            @PathVariable Long id,
            @Valid @RequestBody GalleryUpdateDto request) {
        GalleryDto galleryDto = galleryService.updateGallery(id, request);
        return Response.success(galleryDto);
    }

    @Operation(summary = "갤러리 삭제", description = "기존 데이터를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "삭제 성공")
    @ApiResponse(responseCode = "404", description = "id 찾을 수 없음", content = @Content())
    @DeleteMapping("/{id}")
    public Response<Void> deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
        return Response.success();
    }

    @Operation(summary = "갤러리 목록 조회", description = "갤러리 목록 조회")
    @GetMapping("/list")
    public Response<Page<GalleryDto>> galleryList(
            @Parameter(description = "페이지 번호", required = false) @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", required = false) @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @Parameter(description = "정렬 구분 a=촬영일, b=제목, c=수정일, 내림차순=a,desc", required = false) @RequestParam(value = "sort", required = false) String sortStr
        ) {
        Sort sort = SortUtils.mapToSortField(sortStr);
        Pageable pageable = PageRequest.of(page, size, sort);
        return Response.success(galleryService.galleryList(pageable));
    }

    @Operation(summary = "갤러리 키워드 & 사진작가이름 검색 목록 조회", description = "갤러리 키워드 & 사진작가이름 검색 목록 조회")
    @GetMapping("/search")
    public Response<Page<GalleryDto>> searchGalleries(
            @Parameter(description = "검색할 키워드 & 사진작가", required = false) @RequestParam(value = "keyword", required = false) String keyword,
            @Parameter(description = "페이지 번호", required = false) @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", required = false) @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @Parameter(description = "정렬 구분 a=촬영일, b=제목, c=수정일, 내림차순=a,desc", required = false) @RequestParam(value = "sort", required = false) String sortStr
    ) {
        Sort sort = SortUtils.mapToSortField(sortStr);
        Pageable pageable = PageRequest.of(page, size, sort);
        return Response.success(galleryService.searchGalleries(keyword, pageable));
    }
}

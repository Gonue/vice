package com.example.demo.domain.controller;


import com.example.demo.domain.service.DataCollectionService;
import com.example.demo.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Data-Collection", description = "데이터 수집 API 수동")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collect-data")
public class DataCollectionController {

    private final DataCollectionService dataCollectionService;

    @Operation(summary = "데이터 수집", description = "공공 데이터 포털에서 데이터를 수집하여 저장합니다.")
    @ApiResponse(responseCode = "200", description = "데이터 수집 성공")
    @ApiResponse(responseCode = "409", description = "중복 데이터", content = @Content())
    @PostMapping
    public Response<Void> collectData(
            @Parameter(description = "가져올 데이터 수를 지정합니다.", required = true)
            @RequestParam int totalData){
        dataCollectionService.collectDataFromApi(totalData);
        return Response.success();
    }

    @Operation(summary = "모든 데이터 삭제", description = "데이터베이스에서 모든 데이터를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "데이터 삭제 성공")
    @DeleteMapping
    public Response<Void> deleteAllData() {
        dataCollectionService.deleteAllData();
        return Response.success();
    }
}

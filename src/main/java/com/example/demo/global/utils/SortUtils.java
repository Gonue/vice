package com.example.demo.global.utils;

import com.example.demo.global.error.exception.BusinessLogicException;
import com.example.demo.global.error.exception.ExceptionCode;
import org.springframework.data.domain.Sort;

public class SortUtils {

    public static Sort mapToSortField(String sortKey) {

        if (sortKey == null || sortKey.trim().isEmpty()) {
            return Sort.by(Sort.Direction.ASC, "id");
        }

        String[] parts = sortKey.split(",");
        String field = parts[0];
        Sort.Direction direction;

        if (parts.length > 1) {
            // 사용자가 정렬 방향을 지정한 경우
            direction = Sort.Direction.fromString(parts[1]);
        } else {
            // 사용자가 정렬 방향을 지정하지 않은 경우 기본값으로 설정
            direction = Sort.Direction.ASC;
        }

        field = switch (field) {
            case "a" -> "galPhotographyMonth";
            case "b" -> "galTitle";
            case "c" -> "galModifiedtime";
            default -> throw new BusinessLogicException(ExceptionCode.INVALID_SORT_KEY, "유효하지 않은 SORT");
        };

        return Sort.by(direction, field);
    }
}
package com.example.demo.global.error.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    INTERNAL_SERVER_ERROR(500,"내부 서버 오류"),
    DUPLICATE_RESOURCE(409, "중복 리소스"),
    GALLERY_NOT_FOUNT(404, "해당 갤러리 찾을 수 없음"),
    INVALID_SORT_KEY(400,"잘못된 SORT");

    private final int status;
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

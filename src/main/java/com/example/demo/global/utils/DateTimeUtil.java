package com.example.demo.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;

public class DateTimeUtil {

    public static LocalDateTime parseToLocalDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}

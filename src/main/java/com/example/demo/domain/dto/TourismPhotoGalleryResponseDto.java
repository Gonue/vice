package com.example.demo.domain.dto;

import com.example.demo.domain.entity.Gallery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourismPhotoGalleryResponseDto {
    private Response response;

    @Getter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    public static class Items {
        private List<GalleryItem> item;
    }

    @Getter
    public static class GalleryItem {
        private String galContentId;
        private String galContentTypeId;
        private String galTitle;
        private String galWebImageUrl;
        private String galCreatedtime;
        private String galModifiedtime;
        private String galPhotographyMonth;
        private String galPhotographyLocation;
        private String galPhotographer;
        private String galSearchKeyword;


        public LocalDateTime getCreatedTimeAsLocalDateTime() {
            return LocalDateTime.parse(this.galCreatedtime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }

        public LocalDateTime getModifiedTimeAsLocalDateTime() {
            return LocalDateTime.parse(this.galModifiedtime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }


        public Gallery toEntity() {
            return Gallery.builder()
                    .galContentId(this.galContentId)
                    .galContentTypeId(this.galContentTypeId)
                    .galTitle(this.galTitle)
                    .galWebImageUrl(this.galWebImageUrl)
                    .galCreatedtime(this.getCreatedTimeAsLocalDateTime())
                    .galModifiedtime(this.getModifiedTimeAsLocalDateTime())
                    .galPhotographyMonth(this.galPhotographyMonth)
                    .galPhotographyLocation(this.galPhotographyLocation)
                    .galPhotographer(this.galPhotographer)
                    .galSearchKeyword(this.galSearchKeyword)
                    .build();
        }
    }
}


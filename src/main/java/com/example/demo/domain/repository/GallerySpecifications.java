package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Gallery;
import org.springframework.data.jpa.domain.Specification;

public class GallerySpecifications {


    public static Specification<Gallery> hasKeywordAndPhotographer(String keyword) {
        return isEmpty(keyword) ? null : (root, query, criteriaBuilder) ->
            criteriaBuilder.or(
                criteriaBuilder.like(root.get("galSearchKeyword"), "%" + keyword + "%"),
                criteriaBuilder.like(root.get("galPhotographer"), "%" + keyword + "%")
            );
    }

    private static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}

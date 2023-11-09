package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long>, JpaSpecificationExecutor<Gallery> {
    Optional<Gallery> findByGalContentId(String galContentId);
}

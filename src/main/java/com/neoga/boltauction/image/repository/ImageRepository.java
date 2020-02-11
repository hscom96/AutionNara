package com.neoga.boltauction.image.repository;

import com.neoga.boltauction.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

package com.tarsem.urlforge.repository;

import com.tarsem.urlforge.entity.UrlEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity,Long> {
    Optional<UrlEntity> findByShortCode(String shortCode);

    Optional<UrlEntity> findByOriginalUrl(@NotBlank(message = "URL cant be null") @Pattern(
            regexp = "^https?://([\\w-]+\\.)+[\\w-]+(:\\d+)?(/.*)?$",
            message = "Please enter a valid URL"
    ) String longUrl);
}

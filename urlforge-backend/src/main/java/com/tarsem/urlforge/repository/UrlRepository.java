package com.tarsem.urlforge.repository;

import com.tarsem.urlforge.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity,Long> {
}

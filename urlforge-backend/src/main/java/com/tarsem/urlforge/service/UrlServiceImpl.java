package com.tarsem.urlforge.service;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;
import com.tarsem.urlforge.entity.UrlEntity;
import com.tarsem.urlforge.exception.UrlNotFoundException;
import com.tarsem.urlforge.repository.UrlRepository;
import com.tarsem.urlforge.service.interfaces.UrlService;
import com.tarsem.urlforge.util.Base62Encoder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private static final Logger logger =
            LoggerFactory.getLogger(UrlServiceImpl.class);

    @Value("${app.base-url}")
    private String baseUrl;

    private final UrlRepository urlRepository;

    @Override
    @Transactional
    public UrlResponse forge(UrlRequest request) {

        logger.info("Received URL shortening request for URL: {}", request.getLongUrl());

        Optional<UrlEntity> existingUrl =
                urlRepository.findByOriginalUrl(request.getLongUrl());

        if (existingUrl.isPresent()) {

            logger.info(
                    "Duplicate URL found. Returning existing shortCode={}",
                    existingUrl.get().getShortCode()
            );

            return new UrlResponse(
                    baseUrl + existingUrl.get().getShortCode()
            );
        }

        UrlEntity urlEntity = new UrlEntity();

        urlEntity.setOriginalUrl(request.getLongUrl());
        urlEntity.setClickCount(0);

        urlEntity = urlRepository.save(urlEntity);

        logger.info(
                "Database generated ID={}",
                urlEntity.getId()
        );

        String shortCode = Base62Encoder.encode(urlEntity.getId());

        urlEntity.setShortCode(shortCode);

        urlRepository.save(urlEntity);

        logger.info(
                "Short URL created successfully. ID={}, ShortCode={}",
                urlEntity.getId(),
                shortCode
        );

        return new UrlResponse(
                baseUrl + shortCode
        );
    }


    @Override
    @Transactional
    public String redirect(String shortCode) {

        logger.info(
                "Redirect request received for shortCode={}",
                shortCode
        );

        UrlEntity urlEntity = urlRepository
                .findByShortCode(shortCode)
                .orElseThrow(() -> {

                    logger.warn(
                            "ShortCode={} not found",
                            shortCode
                    );

                    return new UrlNotFoundException("URL not found");
                });

        urlEntity.setClickCount(urlEntity.getClickCount() + 1);

        urlRepository.save(urlEntity);

        logger.info(
                "Redirecting shortCode={} to {}",
                shortCode,
                urlEntity.getOriginalUrl()
        );

        return urlEntity.getOriginalUrl();
    }
}
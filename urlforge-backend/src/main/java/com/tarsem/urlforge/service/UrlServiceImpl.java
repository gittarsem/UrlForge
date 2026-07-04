package com.tarsem.urlforge.service;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;
import com.tarsem.urlforge.entity.UrlEntity;
import com.tarsem.urlforge.exception.UrlNotFoundException;
import com.tarsem.urlforge.repository.UrlRepository;
import com.tarsem.urlforge.service.interfaces.UrlService;
import com.tarsem.urlforge.util.Base62Encoder;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlServiceImpl implements UrlService {
    @Value("${app.base-url}")
    private String baseUrl;

    @Autowired
    private UrlRepository urlRepository;

    @Override
    @Transactional
    public UrlResponse forge(UrlRequest url) {
        UrlEntity urlEntity=new UrlEntity();

        urlEntity.setOriginalUrl(url.getLongUrl());
        urlEntity.setClickCount(0);

        urlEntity=urlRepository.save(urlEntity);
        String shortCode=Base62Encoder.encode(urlEntity.getId());
        urlEntity.setShortCode(shortCode);

        urlRepository.save(urlEntity);
        return new UrlResponse(url.getLongUrl(), baseUrl + shortCode);
    }

    @Override
    public String redirect(String shortCode) throws UrlNotFoundException {
       UrlEntity urlEntity=urlRepository.findByShortCode(shortCode)
               .orElseThrow(
                       ()-> new UrlNotFoundException("Url Not Found")
               );
       urlEntity.setClickCount(urlEntity.getClickCount()+1);
       urlRepository.save(urlEntity);
       return urlEntity.getOriginalUrl();
    }
}

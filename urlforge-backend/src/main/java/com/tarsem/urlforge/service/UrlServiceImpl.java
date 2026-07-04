package com.tarsem.urlforge.service;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;
import com.tarsem.urlforge.repository.UrlRepository;
import com.tarsem.urlforge.service.interfaces.UrlService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private UrlRepository urlRepository;


    @Override
    public @Nullable UrlResponse forge(UrlRequest url) {

        return new UrlResponse(url.getLongUrl(),"https://www.youtube.com/watch?v=2fDzCWNS3ig&list=RD-ZvsGmYKhcU&index=9");
    }
}

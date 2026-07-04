package com.tarsem.urlforge.service.interfaces;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;
import org.jspecify.annotations.Nullable;

public interface UrlService {

    @Nullable UrlResponse forge(UrlRequest url);
}

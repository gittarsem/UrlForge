package com.tarsem.urlforge.service.interfaces;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;
import com.tarsem.urlforge.exception.UrlNotFoundException;

public interface UrlService {

    UrlResponse forge(UrlRequest url);

    String redirect(String shortCode) throws UrlNotFoundException;
}

package com.tarsem.urlforge.service.interfaces;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;

public interface UrlService {

     UrlResponse forge(UrlRequest url);
}

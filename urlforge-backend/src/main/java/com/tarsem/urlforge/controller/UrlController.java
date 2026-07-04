package com.tarsem.urlforge.controller;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;
import com.tarsem.urlforge.service.interfaces.UrlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UrlController {

    private UrlService urlService;

    @PostMapping("/urls")
    private ResponseEntity<UrlResponse> urlforge(@Valid @RequestBody UrlRequest url){
        return ResponseEntity.ok(urlService.forge(url));
    }
}

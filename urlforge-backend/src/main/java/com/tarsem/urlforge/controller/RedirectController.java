package com.tarsem.urlforge.controller;


import com.tarsem.urlforge.exception.UrlNotFoundException;
import com.tarsem.urlforge.service.interfaces.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import org.springframework.http.HttpHeaders;

@RestController
@AllArgsConstructor
public class RedirectController {

    private UrlService urlService;

    @GetMapping("{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) throws UrlNotFoundException {
        String originalUrl=urlService.redirect(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}

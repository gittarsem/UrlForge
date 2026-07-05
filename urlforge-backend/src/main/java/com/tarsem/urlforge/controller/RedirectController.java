package com.tarsem.urlforge.controller;


import com.tarsem.urlforge.exception.UrlNotFoundException;
import com.tarsem.urlforge.service.interfaces.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class RedirectController {

    private UrlService urlService;

    @Operation(
            summary = "Redirect to Original URL",
            description = "Redirects the client to the original URL using the provided short code."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Short URL created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid URL"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) throws UrlNotFoundException {
        String originalUrl=urlService.redirect(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}

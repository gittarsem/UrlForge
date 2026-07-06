package com.tarsem.urlforge.controller;

import com.tarsem.urlforge.dto.UrlRequest;
import com.tarsem.urlforge.dto.UrlResponse;
import com.tarsem.urlforge.service.interfaces.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UrlController {

    private UrlService urlService;

    @Operation(
            summary = "Create Short URL",
            description = "Creates a Base62 encoded short URL for the provided long URL."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Short URL created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid URL"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/urls")
    private ResponseEntity<UrlResponse> urlforge(@Valid @RequestBody UrlRequest url){
        return ResponseEntity.ok(urlService.forge(url));
    }

    @GetMapping("/validate/{shortCode}")
    public boolean isValid(@PathVariable String shortCode){
        return urlService.isValid(shortCode);
    }
}

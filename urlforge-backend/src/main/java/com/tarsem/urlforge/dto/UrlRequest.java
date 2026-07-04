package com.tarsem.urlforge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UrlRequest {
    @NotBlank
    private String longUrl;
}

package com.tarsem.urlforge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlResponse {
    private String longUrl;
    private String shortUrl;
}

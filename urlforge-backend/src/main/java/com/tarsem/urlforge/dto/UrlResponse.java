package com.tarsem.urlforge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response containing the generated short URL")
public class UrlResponse {

    @Schema(description = "Response containing the generated short URL")
    private String shortUrl;
}

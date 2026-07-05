package com.tarsem.urlforge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(
        description = "Original URL to shorten"
)
public class UrlRequest {

    @Schema(
            description = "Original URL to shorten",
            example = "https://www.google.com"
    )
    @NotBlank(message = "URL cant be null")
    @Pattern(
            regexp = "^https?://([\\w-]+\\.)+[\\w-]+(:\\d+)?(/.*)?$",
            message = "Please enter a valid URL"
    )
    private String longUrl;
}

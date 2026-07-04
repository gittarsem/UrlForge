package com.tarsem.urlforge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UrlRequest {
    @NotBlank(message = "URL cant be null")
    @Pattern(
            regexp = "^https?://([\\w-]+\\.)+[\\w-]+(:\\d+)?(/.*)?$",
            message = "Please enter a valid URL"
    )
    private String longUrl;
}

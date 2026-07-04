package com.tarsem.urlforge.exception;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException(String urlNotFound) {
        super(urlNotFound);
    }
}

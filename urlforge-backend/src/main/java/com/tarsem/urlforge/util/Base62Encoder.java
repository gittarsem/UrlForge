package com.tarsem.urlforge.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Base62Encoder {

    private static final String BASE62_CHARACTERS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int BASE = BASE62_CHARACTERS.length();


    public static String encode(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }

        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative.");
        }

        if (id == 0) {
            return "0";
        }

        StringBuilder encoded = new StringBuilder();

        while (id > 0) {

            int remainder = (int) (id % BASE);

            encoded.append(BASE62_CHARACTERS.charAt(remainder));

            id /= BASE;
        }

        return encoded.reverse().toString();
    }
}
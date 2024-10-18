package com.example.olimpo_api_nosql.exception;

public class ExceptionThrower {
    public ExceptionThrower() {
    }
    public static void throwNotFoundException(String message) {
        throw new CustomNotFoundException(message);
    }

    public static void throwBadRequestException(String message) {
        throw new CustomBadRequestException(message);
    }

    public static void throwIllegalArgumentException(String message) {
        throw new IllegalArgumentException(message);
    }

    public static void throwUnauthorizedException(String message) {
        throw new CustomUnauthorizedException(message);
    }

    public static void throwNullPointerException(String message) {
        throw new CustomNullPointerException(message);
    }

    public static void throwGenericException(String message) {
        throw new CustomGenericException(message);
    }
}

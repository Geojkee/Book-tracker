package com.dwtd.book_tracker.bookTracker.DTO.Error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorResponse(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public static ErrorResponse of(
            HttpStatus status,
            String message,
            String path
    ) {
        return new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }

    public static ErrorResponse badRequest(String message, String path){
        return of(HttpStatus.BAD_REQUEST, message, path);
    }

    public static ErrorResponse notFound(String message, String path) {
        return of(HttpStatus.NOT_FOUND, message, path);
    }

    public static ErrorResponse unauthorized(String message, String path){
        return of(HttpStatus.UNAUTHORIZED, message, path);
    }

    public static ErrorResponse conflict(String message, String path){
        return of(HttpStatus.CONFLICT, message, path);
    }

    public static ErrorResponse forbidden(String message, String path){
        return of(HttpStatus.FORBIDDEN, message, path);
    }
}
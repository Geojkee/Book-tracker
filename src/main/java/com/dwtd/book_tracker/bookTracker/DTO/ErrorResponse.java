package com.dwtd.book_tracker.bookTracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ErrorResponse(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
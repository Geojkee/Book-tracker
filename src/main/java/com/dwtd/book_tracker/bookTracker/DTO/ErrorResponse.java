package com.dwtd.book_tracker.bookTracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ErrorResponse(
        String message,
        int status,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
        Instant timestamp
) {
}

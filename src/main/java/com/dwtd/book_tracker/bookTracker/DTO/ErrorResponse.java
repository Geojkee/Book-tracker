package com.dwtd.book_tracker.bookTracker.DTO;

public record ErrorResponse(
        String message,
        int status,
        long timestamp
) {
}

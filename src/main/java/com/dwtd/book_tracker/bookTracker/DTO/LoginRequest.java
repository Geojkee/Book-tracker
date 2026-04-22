package com.dwtd.book_tracker.bookTracker.DTO;

public record LoginRequest(
        String email,
        String password
) {
}

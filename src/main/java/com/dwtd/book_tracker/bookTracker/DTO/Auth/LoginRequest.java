package com.dwtd.book_tracker.bookTracker.DTO.Auth;

public record LoginRequest(
        String email,
        String password
) {
}

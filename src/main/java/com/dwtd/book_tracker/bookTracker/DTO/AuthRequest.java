package com.dwtd.book_tracker.bookTracker.DTO;

public record AuthRequest(
        String email,
        String password
) {
}

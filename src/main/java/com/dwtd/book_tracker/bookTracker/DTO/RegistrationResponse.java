package com.dwtd.book_tracker.bookTracker.DTO;

public record RegistrationResponse(
        Long id,
        String email,
        String token
) {
}

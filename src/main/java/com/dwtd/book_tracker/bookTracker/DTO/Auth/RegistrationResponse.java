package com.dwtd.book_tracker.bookTracker.DTO.Auth;

public record RegistrationResponse(
        Long id,
        String email,
        String token
) {
}

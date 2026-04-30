package com.dwtd.book_tracker.bookTracker.DTO.Author;

public record AuthorResponse(
        Long id,
        String firstName,
        String lastName,
        String biography
) {
}
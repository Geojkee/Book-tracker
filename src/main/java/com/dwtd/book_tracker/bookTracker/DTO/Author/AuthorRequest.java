package com.dwtd.book_tracker.bookTracker.DTO.Author;

public record AuthorRequest(
        String firstName,
        String lastName,
        String biography
) {
}

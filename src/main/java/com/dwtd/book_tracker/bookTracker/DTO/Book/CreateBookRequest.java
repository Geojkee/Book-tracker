package com.dwtd.book_tracker.bookTracker.DTO.Book;

public record CreateBookRequest(
        String title,
        String isbn,
        Integer publishYear,
        Long authorId
) {
}

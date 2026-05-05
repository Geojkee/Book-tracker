package com.dwtd.book_tracker.bookTracker.DTO.Book;

import com.dwtd.book_tracker.bookTracker.Enums.BookStatus;

public record BookResponse(
        Long id,
        String title,
        String isbn,
        Integer publishYear,
        BookStatus status,
        Long authorId
) {
}

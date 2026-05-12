package com.dwtd.book_tracker.bookTracker.Services;

import com.dwtd.book_tracker.bookTracker.DTO.Book.BookFilter;
import com.dwtd.book_tracker.bookTracker.Models.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withFilters(BookFilter filters, Long userId) {
        return Specification.where(hasUser(userId))
                .and(hasTitle(filters.title()))
                .and(hasAuthor(filters.authorId()));
    }

    private static Specification<Book> hasUser(Long userId) {
        return (root, query, cb) ->
                cb.equal(root.get("user").get("id"), userId);
    }

    private static Specification<Book> hasTitle(String title) {
        return (root, query, cb) -> {

            if (title == null || title.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Book> hasAuthor(Long authorId) {
        return (root, query, cb) -> {

            if (authorId == null) {
                return cb.conjunction();
            }

            return cb.equal(root.get("author").get("id"), authorId);
        };
    }
}

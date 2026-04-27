package com.dwtd.book_tracker.bookTracker.Repository;

import com.dwtd.book_tracker.bookTracker.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

package com.dwtd.book_tracker.bookTracker.Repository;

import com.dwtd.book_tracker.bookTracker.Models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByUserId(Long userId, Pageable pageable);
}

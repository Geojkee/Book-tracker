package com.dwtd.book_tracker.bookTracker.Repository;

import com.dwtd.book_tracker.bookTracker.Models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Page<Book> findByUserId(Long userId, Pageable pageable);
}

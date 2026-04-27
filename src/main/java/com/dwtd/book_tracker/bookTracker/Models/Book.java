package com.dwtd.book_tracker.bookTracker.Models;

import com.dwtd.book_tracker.bookTracker.Enums.BookStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @NotNull
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @NotNull
    @Column(name = "publish_year", nullable = false)
    private Integer publishYear;

    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authors_id", nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Book(
            String title,
            String isbn,
            Integer publishYear,
            Author authors,
            User userId
    ) {
        this.title = title;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.status = BookStatus.PLANNED;
        this.author = authors;
        this.user = userId;
    }
}

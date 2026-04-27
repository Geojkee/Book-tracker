package com.dwtd.book_tracker.bookTracker.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull
    @Column(name = "biography", nullable = false, length = Integer.MAX_VALUE)
    private String biography;

    @Column(name = "created_at")
    private Instant createdAt;

    public Author(
            String firstName,
            String lastName,
            String biography
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.createdAt = Instant.now();
    }
}


package com.dwtd.book_tracker.bookTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "created_at")
    private Instant createdAt;

    public User(
            String email,
            String password
    ) {
        this.email = email;
        this.password = password;
        this.createdAt = Instant.now();
    }
}

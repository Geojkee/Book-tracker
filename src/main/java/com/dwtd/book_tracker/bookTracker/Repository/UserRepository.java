package com.dwtd.book_tracker.bookTracker.Repository;

import com.dwtd.book_tracker.bookTracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

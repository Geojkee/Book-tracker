package com.dwtd.book_tracker.bookTracker.Controller;

import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorRequest;
import com.dwtd.book_tracker.bookTracker.Models.Author;
import com.dwtd.book_tracker.bookTracker.Services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<Author> create(
            @RequestBody AuthorRequest request
    ) {
        return ResponseEntity.ok(authorService.create(request));
    }
}

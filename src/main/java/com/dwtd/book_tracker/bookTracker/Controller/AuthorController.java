package com.dwtd.book_tracker.bookTracker.Controller;

import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorRequest;
import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorResponse;
import com.dwtd.book_tracker.bookTracker.Services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<AuthorResponse> create(
            @Valid @RequestBody AuthorRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
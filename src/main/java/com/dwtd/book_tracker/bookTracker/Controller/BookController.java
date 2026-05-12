package com.dwtd.book_tracker.bookTracker.Controller;

import com.dwtd.book_tracker.bookTracker.DTO.Book.BookResponse;
import com.dwtd.book_tracker.bookTracker.DTO.Book.CreateBookRequest;
import com.dwtd.book_tracker.bookTracker.Services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookResponse> create(
            @Valid @RequestBody CreateBookRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CreateBookRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

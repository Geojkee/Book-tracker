package com.dwtd.book_tracker.bookTracker.Services;

import com.dwtd.book_tracker.bookTracker.DTO.Book.BookResponse;
import com.dwtd.book_tracker.bookTracker.DTO.Book.CreateBookRequest;
import com.dwtd.book_tracker.bookTracker.Exception.ForbiddenException;
import com.dwtd.book_tracker.bookTracker.Exception.NotFoundException;
import com.dwtd.book_tracker.bookTracker.Models.Author;
import com.dwtd.book_tracker.bookTracker.Models.Book;
import com.dwtd.book_tracker.bookTracker.Models.User;
import com.dwtd.book_tracker.bookTracker.Repository.AuthorRepository;
import com.dwtd.book_tracker.bookTracker.Repository.BookRepository;
import com.dwtd.book_tracker.bookTracker.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;

    public BookResponse create(CreateBookRequest request) {

        User user = getCurrentUser();

        log.info("Create book attempt: user={}", user.getEmail());

        Author author = getAuthor(request.authorId());

        Book book = new Book(
                request.title(),
                request.isbn(),
                request.publishYear(),
                author,
                user
        );

        Book savedBook = bookRepository.save(book);

        log.info("Book created: id={}, userId={}", savedBook.getId(), user.getId());

        return map(savedBook);
    }

    public Page<BookResponse> getAll(Pageable pageable) {

        User user = getCurrentUser();

        return bookRepository.
                findAllBooksByUserId(user.getId(), pageable)
                .map(this::map);
    }

    public BookResponse getById(Long id) {

        User user = getCurrentUser();

        Book book = getBook(id);

        checkOwner(book, user);

        return map(book);
    }

    public BookResponse update(Long id, CreateBookRequest request) {

        User user = getCurrentUser();

        Book book = getBook(id);

        checkOwner(book, user);

        Author author = getAuthor(request.authorId());

        book.setTitle(request.title());
        book.setIsbn(request.isbn());
        book.setPublishYear(request.publishYear());
        book.setAuthor(author);

        Book updateBook = bookRepository.save(book);

        log.info("Book updated successfully id={}", id);

        return map(updateBook);
    }

    public void delete(Long id) {

        User user = getCurrentUser();

        Book book = getBook(id);

        checkOwner(book, user);

        bookRepository.delete(book);

        log.info("Book deleted: id={}, user={}", id, user.getEmail());
    }

    private User getCurrentUser() {
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));

    }

    private void checkOwner(Book book, User user){
        if (!book.getUser().getId().equals(user.getId())){
            throw new ForbiddenException("You don't have access to this book");
        }
    }

    private BookResponse map(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublishYear(),
                book.getStatus(),
                book.getAuthor().getId()
        );
    }
}

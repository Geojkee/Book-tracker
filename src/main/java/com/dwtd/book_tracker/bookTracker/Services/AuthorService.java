package com.dwtd.book_tracker.bookTracker.Services;

import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorRequest;
import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorResponse;
import com.dwtd.book_tracker.bookTracker.Exception.NotFoundException;
import com.dwtd.book_tracker.bookTracker.Models.Author;
import com.dwtd.book_tracker.bookTracker.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponse create(AuthorRequest request) {

        log.info("Creating author: {} {}", request.firstName(), request.lastName());

        Author author = new Author(
                request.firstName(),
                request.lastName(),
                request.biography()
        );

        Author savedAuthor = authorRepository.save(author);

        log.info("Author created successfully with id={}", savedAuthor.getId());

        return map(savedAuthor);
    }

    public Page<AuthorResponse> getAll(Pageable pageable) {

        log.info("Fetching authors: page={}, size={}",
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        return authorRepository
                .findAll(pageable)
                .map(this::map);
    }

    public AuthorResponse getById(Long id) {

        log.info("Fetching authors by id={}", id);

        return map(authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Author not found with id={}", id);
                    return new NotFoundException("Author not found");
                }));
    }

    public AuthorResponse update(Long id, AuthorRequest request) {

        log.info("Updating author id={}", id);

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Update failed - author not found id={}", id);
                    return new NotFoundException("Author not found");
                });

        author.setFirstName(request.firstName());
        author.setLastName(request.lastName());
        author.setBiography(request.biography());

        Author updatedAuthor = authorRepository.save(author);

        log.info("Author updated successfully id={}", id);

        return map(updatedAuthor);
    }

    public void delete(Long id) {

        log.info("Deleting author id={}", id);

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Delete failed - author not found id={}", id);
                    return new NotFoundException("Author not found");
                });

        authorRepository.delete(author);

        log.info("Author deleted successfully id={}", id);
    }

    private AuthorResponse map(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBiography()
        );
    }
}
package com.dwtd.book_tracker.bookTracker.Services;

import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorRequest;
import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorResponse;
import com.dwtd.book_tracker.bookTracker.Exception.NotFoundException;
import com.dwtd.book_tracker.bookTracker.Models.Author;
import com.dwtd.book_tracker.bookTracker.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponse create(AuthorRequest request) {

        Author author = new Author(
                request.firstName(),
                request.lastName(),
                request.biography()
        );

        Author savedAuthor = authorRepository.save(author);

        return map(savedAuthor);
    }

    public Page<AuthorResponse> getAll(Pageable pageable) {
        return authorRepository
                .findAll(pageable)
                .map(this::map);
    }

    public AuthorResponse getById(Long id) {
        return map(authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found")));
    }

    public AuthorResponse update(Long id, AuthorRequest request){

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));

        author.setFirstName(request.firstName());
        author.setLastName(request.lastName());
        author.setBiography(request.biography());

        return map(author);
    }

    public void delete(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));

        authorRepository.delete(author);
    }

    private AuthorResponse map(Author author){
        return new AuthorResponse(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBiography()
        );
    }
}
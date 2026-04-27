package com.dwtd.book_tracker.bookTracker.Services;

import com.dwtd.book_tracker.bookTracker.DTO.Author.AuthorRequest;
import com.dwtd.book_tracker.bookTracker.Models.Author;
import com.dwtd.book_tracker.bookTracker.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author create(AuthorRequest request){
        Author author = new Author(
                request.firstName(),
                request.lastName(),
                request.biography()
        );

        return authorRepository.save(author);
    }

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public Author getById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }
}

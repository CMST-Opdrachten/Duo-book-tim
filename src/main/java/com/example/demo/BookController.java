package com.example.demo;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class BookController {

    private final BookRepo repository;
    public Book emptyBook;

    BookController(BookRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    List<Book> all() {
        return repository.findAll();
    }

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @GetMapping("/books/{id}")
    Book one(@PathVariable String id) {
        if (convertToBigInt(id) != null) {
            return repository.findById(convertToBigInt(id)).orElseThrow(() -> new BookNotFoundException(convertToBigInt(id)));
        } else {
            return this.emptyBook;
        }
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable String id){
        if (convertToBigInt(id) != null) {
            BigInteger convertedId = convertToBigInt(id);
            return repository.findById(convertedId)
                    .map(book -> {
                        book.setTitle(newBook.getTitle());
                        book.setPublisher(newBook.getTitle());
                        return repository.save(book);
                    })
                    .orElseGet(() -> {
                        newBook.setId(convertedId);
                        return repository.save(newBook);
                    });
        } else {
            return this.emptyBook;
        }
    }

    @DeleteMapping("/book/{id}")
    void deleteBook(@PathVariable String id) {
        if (convertToBigInt(id) != null) {
            repository.deleteById(convertToBigInt(id));
        }
    }

    public BigInteger convertToBigInt(String id) {
        try {
            return new BigInteger(id);
        } catch (Exception e) {
            return null;
        }
    }
}

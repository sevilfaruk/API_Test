package com.example.part2.controller;

import com.example.part2.model.Book;
import com.example.part2.model.ErrorMessage;
import com.example.part2.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/api/books")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Test
    public ResponseEntity createBook(@RequestBody Book book) throws JsonProcessingException {

        if (book.getId() != 999999999) {
            return new ResponseEntity<>("you should not set the id", HttpStatus.BAD_REQUEST);
        }

        boolean isError = true;
        ErrorMessage error = new ErrorMessage();

        if (book.getAuthor() != null && book.getAuthor().isEmpty()) {
            error.setError("Field 'author' is connot be empty");
        }
        if (book.getAuthor() == null) {
            error.setError("Field 'author' is required");
        }

        if (book.getTitle() != null && book.getTitle().isEmpty()) {
            error.setError("Field 'title' is connot be empty");
        }
        if (book.getTitle() == null) {
            error.setError("Field 'title' is required");
        }

        if (book.getAuthor() != null && book.getTitle() != null && !book.getAuthor().isEmpty() && !book.getTitle().isEmpty()) {
            Book byTitle = bookService.findByTitle(book.getTitle());
            Book byAuthor = bookService.findByAuthor(book.getAuthor());

            if (byTitle != null && byAuthor != null) {
                error.setError("Another book with similar title and author already exists");
            } else {
                isError = false;
                bookService.save(book);
            }
        }

        if (isError) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(error);

            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }
        getBooks();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    @Test
    public ResponseEntity getTutorialById(@PathVariable int id) {
        Book byId = bookService.findById(id);

        if (byId != null) {
            return new ResponseEntity<>(byId, HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    @Test
    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        Iterable<Book> all = bookService.findAll();
        all.iterator().forEachRemaining(bookList::add);
        System.out.println(bookList);

        return bookList;
    }
}

package com.example.part2.service;

import com.example.part2.model.Book;
import com.example.part2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book){
        bookRepository.save(book);
    }

    public Book findById(int id){
        return bookRepository.findById(id);
    }

    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}

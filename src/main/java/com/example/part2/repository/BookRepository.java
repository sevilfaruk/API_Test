package com.example.part2.repository;

import com.example.part2.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findById(int id);

    Book findByTitle(String title);

    Book findByAuthor(String author);
}

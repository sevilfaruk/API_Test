package com.example.part2.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id = 999999999;


    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    public Book() {
    }


    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", author=" + author + ", title=" + title + "]";
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
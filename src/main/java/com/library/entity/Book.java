package com.library.entity;

import javax.persistence.*;

@Table(name = "book")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "edition", nullable = false)
    private int edition;

    public Book() { }

    public Book(String title, String author, int edition) {
        this.title  = title;
        this.author = author;
        this.edition = edition;
    }

    public static String validate(String title, String author, int edition) {
        return "";
    }

    public static boolean isEmpty(Book book) {
        if (book == null) return true;

        return book.getId() == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
}
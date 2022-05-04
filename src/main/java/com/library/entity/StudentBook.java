package com.library.entity;

import javax.persistence.*;

@Table(name = "Student_Book")
@Entity
public class StudentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "studentId", nullable = false)
    private int studentId;

    @Column(name = "bookId", nullable = false)
    private int bookId;

    public StudentBook() {}

    public StudentBook(int studentId, int bookId) {
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public static boolean isEmpty(StudentBook studentBook) {
        if (studentBook == null) return true;

        return studentBook.getId() == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}

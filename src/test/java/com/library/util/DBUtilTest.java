package com.library.util;

import com.library.entity.Book;
import com.library.entity.Student;
import com.library.entity.StudentBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    @Test
    void addStudent() {
        Student student = DBUtil.addStudent("", "", 0);
        assertNotNull(student);
    }

    @Test
    void addBook() {
        Book book = DBUtil.addBook("", "", 0);
        assertNotNull(book);
    }

    @Test
    void borrowBook() {
        Student student = DBUtil.addStudent("", "", 0);
        Book book = DBUtil.addBook("", "", 0);

        StudentBook studentBook1 = DBUtil.borrowBook(student.getId(), book.getId());
        StudentBook studentBook2 = DBUtil.borrowBook(5, 7);

        assertNotNull(studentBook1);
        assertNull(studentBook2);
    }

    @Test
    void returnBook() {
        Student student = DBUtil.addStudent("", "", 0);
        Book book = DBUtil.addBook("", "", 0);

        DBUtil.borrowBook(student.getId(), book.getId());

        StudentBook studentBook1 = DBUtil.returnBook(student.getId());
        StudentBook studentBook2 = DBUtil.returnBook(4);

        assertNotNull(studentBook1);
        assertNull(studentBook2);
    }
}
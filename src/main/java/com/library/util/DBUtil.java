package com.library.util;

import com.library.entity.Book;
import com.library.entity.Student;
import com.library.entity.StudentBook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DBUtil {
    public static Student addStudent(String name, String grade, int yob) {
        Student student = new Student(name, grade, yob);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int savedId = (int) session.save(student);
            student = session.get(Student.class, savedId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return student;
    }

    public static Book addBook(String title, String author, int edition) {
        Book book = new Book(title, author, edition);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int savedId = (int) session.save(book);
            book = session.get(Book.class, savedId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return book;
    }

    public static StudentBook borrowBook(int studentId, int bookId) {
        Transaction transaction = null;

        Student student = DBUtil.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student with id "+ studentId + " does not exists");
            return null;
        }

        Book book = DBUtil.getBookById(bookId);
        if (book == null) {
            System.out.println("Book with id "+ bookId + " does not exists");
            return null;
        }

        StudentBook studentBook = DBUtil.getStudentBookByStudentId(studentId);
        if (studentBook != null) {
            System.out.println("Return the previously borrowed book");
            return null;
        }

        StudentBook sb = new StudentBook(studentId, bookId);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int savedId = (int) session.save(sb);
            sb = session.get(StudentBook.class, savedId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return sb;
    }

    public static StudentBook returnBook(int studentId) {
        StudentBook studentBook = DBUtil.getStudentBookByStudentId(studentId);
        if (studentBook == null) {
            System.out.println("No borrowed book");
            return null;
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(studentBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return studentBook;
    }

    public static Student getStudentById(int id) {
        Transaction transaction = null;
        Student student = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return student;
    }

    public static Book getBookById(int id) {
        Transaction transaction = null;
        Book book = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            book = session.get(Book.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return book;
    }

    public static StudentBook getStudentBookByStudentId(int id) {
        AtomicReference<StudentBook> studentBook = new AtomicReference<>();
        studentBook.set(null);

        getAllStudentBooks().forEach(sb -> {
            if (sb.getStudentId() == id) studentBook.set(sb);
        });

        return studentBook.get();
    }

    public static List<StudentBook> getAllStudentBooks() {
        List<StudentBook> studentBooks = new ArrayList<>();

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            studentBooks = session.createQuery("from StudentBook", StudentBook.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return studentBooks;
    }
}

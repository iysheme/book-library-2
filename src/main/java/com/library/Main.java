package com.library;

import com.library.entity.Book;
import com.library.entity.Student;
import com.library.entity.StudentBook;
import com.library.util.DBUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        logger.trace("Welcome to the application");

        requestOption();

        sc.close();
    }

    private static void requestOption() {
        logger.info("\nEnter 1 to create a new Student\n" +
                "Enter 2 to create a new Book\n" +
                "Enter 3 to borrow a book (You can only borrow one book at a time)\n" +
                "Enter 4 to return a book\n" +
                "Enter any other key to exit.\n");

        try {
            int option = sc.nextInt();

            if (option < 1 || option > 4) throw new InputMismatchException();

            doOption(option);
        }
        catch (InputMismatchException e) {
            logger.trace("Thank you for using the application");
        }
    }

    static void doOption(int option) {
        switch (option) {
            case 1:
                createStudent();
                requestOption();
                break;
            case 2:
                createBook();
                requestOption();
                break;
            case 3:
                borrowBook();
                requestOption();
                break;
            case 4:
                returnBook();
                requestOption();
                break;
            default:
                logger.warn("Option not supported");
        }
    }

    private static void createStudent() {
        logger.info("Enter student name");
        String name = sc.next();

        logger.info("Enter student grade");
        String grade = sc.next();

        logger.info("Enter student year of birth");
        int yob = sc.nextInt();

        Student student = DBUtil.addStudent(name, grade, yob);
        logger.info(String.format("New student created successfully with id = %d!", student.getId()));
    }

    private static void createBook() {
        logger.info("Enter book title");
        String title = sc.next();

        logger.info("Enter book author's name");
        String author = sc.next();

        logger.info("Enter book edition");
        int edition = sc.nextInt();

        Book book = DBUtil.addBook(title, author, edition);
        logger.info(String.format("New book created successfully with id = %d!", book.getId()));
    }

    private static void borrowBook() {
        logger.info("Enter student id");
        int id1 = sc.nextInt();

        logger.info("Enter book id");
        int id2 = sc.nextInt();

        StudentBook studentBook = DBUtil.borrowBook(id1, id2);
        if (studentBook == null) return;

        logger.info("Book borrowed");
    }

    private static void returnBook() {
        logger.info("Enter student id");
        int id = sc.nextInt();

        StudentBook studentBook = DBUtil.returnBook(id);
        if (studentBook == null) return;

        logger.info("Book returned");
    }
}

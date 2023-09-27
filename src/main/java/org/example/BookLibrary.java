package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BookLibrary {
    private static final Logger logger = Logger.getLogger("Logger");
    private List<Book> books;

    public BookLibrary() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public void borrowBook(Book book, String data) {
        if (books.contains(book) && !book.isBorrowed()) {
            book.setBorrowed(true);
            logger.log(Level.INFO, "Data: {0} ", data);
        }
    }

    public void returnBook(Book book) {
        if (books.contains(book) && book.isBorrowed()) {
            book.setBorrowed(false);
        }
    }

    public int getBookCount() {
        return books.size();
    }

    public int getAvailableBookCount() {
        int count = 0;
        for (Book book : books) {
            if (!book.isBorrowed()) {
                count++;
            }
        }
        return count;
    }
    public boolean hasBook(Book book) {
        return books.contains(book);
    }
}
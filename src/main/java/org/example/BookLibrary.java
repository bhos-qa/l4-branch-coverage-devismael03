package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

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

    public List<Book> findBooksByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        scanner.close();

        String json = "{\"author\":\""+ author +"\"}";
        JSONObject obj = new JSONObject(json);


        File tempDir;
        try {
            tempDir = File.createTempFile("log_book_borrow", null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tempDir.delete();
        tempDir.mkdir();

        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        logger.log(Level.INFO, "Following author books requested: {0} ", obj);
        return result;
    }

    public void borrowBook(Book book, String data) throws IOException {
        if (books.contains(book) && !book.isBorrowed()) {
            book.setBorrowed(true);
            logger.log(Level.INFO, "Data: {0} ", data);
            File file = new File("log.txt");
            if (!file.exists()) { // Noncompliant
                System.out.print("FILE NOT FOUND");
            }
            else{
                FileWriter writer = new FileWriter(file);
                writer.write("BOOK BORROWED");
                writer.close();
            }
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
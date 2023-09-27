package org.example;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookLibraryTest {
    private BookLibrary library;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void Setup() {
        library = new BookLibrary();
        book1 = new Book("QA Testing", "Karim Karimov");
        book2 = new Book("Software Engineering", "Ismayil Mammadzada");
        library.addBook(book1);
        library.addBook(book2);
    }

    @Test
    public void AddBook() {
        Book newBook = new Book("Cloud Engineering", "Elshan Farzaliyev");
        library.addBook(newBook);
        assertTrue(library.getAllBooks().contains(newBook));
    }

    @Test
    public void RemoveBook() {
        library.removeBook(book1);
        assertFalse(library.getAllBooks().contains(book1));
    }

    @Test
    public void FindBooksByTitle() {
        List<Book> foundBooks = library.findBooksByTitle("QA Testing");
        assertEquals(1, foundBooks.size());
        assertEquals(book1, foundBooks.get(0));
    }

    @Test
    public void BorrowBook() {
        library.borrowBook(book1, "BOOK BORROWED");
        assertTrue(book1.isBorrowed());
    }

    @Test
    public void ReturnBook() {
        library.borrowBook(book1, "BOOK BORROWED");
        library.returnBook(book1);
        assertFalse(book1.isBorrowed());
    }
}
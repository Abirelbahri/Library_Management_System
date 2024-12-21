package controller;

import model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookController {
    private List<Book> books = new ArrayList<>();

    // Add a new book with details such as title, author, publication year, and genre
    public void addBook(String title, String author, int publicationYear, String genre) {
        Book newBook = new Book(title, author, publicationYear, genre);
        books.add(newBook);
        System.out.println("Book added: " + newBook);
    }

    // Modify an existing book's information by its ID
    public void modifyBook(int id, String title, String author, int publicationYear, String genre) {
        Book book = getBookById(id);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublicationYear(publicationYear);
            book.setGenre(genre);
            System.out.println("Book modified: " + book);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    // Delete a book from the library by its ID
    public void deleteBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted: " + book);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    // Search for books by title, author, or genre (supports partial matching)
    public List<Book> searchBooks(String searchTerm) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) || 
                                book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()) || 
                                book.getGenre().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Filter books by publication year
    public List<Book> filterBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    // List all books
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    // Get a book by its ID
    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Get all books (for testing or external use)
    public List<Book> getAllBooks() {
        return books;
    }
}

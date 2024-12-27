package controller;

import model.Book;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookController {
    private List<Book> books;
    private String filePath;

    public BookController(String filePath) {
        this.filePath = filePath;
        this.books = loadBooksFromCSV();
        
        if (!books.isEmpty()) {
            int maxId = books.stream().mapToInt(Book::getId).max().orElse(1);
            Book.setIdCounter(maxId + 1);
        }
    }

    private List<Book> loadBooksFromCSV() {
        List<Book> bookList = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(filePath);
        for (String[] row : data) {
            try {
                int id = Integer.parseInt(row[0]);
                String title = row[1];
                String author = row[2];
                int publicationYear = Integer.parseInt(row[3]);
                String genre = row[4];
                bookList.add(new Book(id, title, author, publicationYear, genre));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return bookList;
    }

    private void saveBooksToCSV() {
        List<String[]> data = books.stream()
                .map(book -> new String[]{
                        String.valueOf(book.getId()),
                        book.getTitle(),
                        book.getAuthor(),
                        String.valueOf(book.getPublicationYear()),
                        book.getGenre()
                })
                .collect(Collectors.toList());
        CSVUtils.writeToCSV(filePath, data);
    }

    public void addBook(String title, String author, int publicationYear, String genre) {
        Book newBook = new Book(title, author, publicationYear, genre);
        books.add(newBook);
        saveBooksToCSV();
    }

    public void modifyBook(int id, String title, String author, int publicationYear, String genre) {
        Book book = getBookById(id);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublicationYear(publicationYear);
            book.setGenre(genre);
            saveBooksToCSV();
        }
    }

    public void deleteBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            books.remove(book);
            saveBooksToCSV();
        }
    }

    public List<Book> searchBooks(String searchTerm) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) || 
                                book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()) || 
                                book.getGenre().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> filterBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    public void listBooks() {
        books.forEach(System.out::println);
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Book> getAllBooks() {
        return books;
    }
}

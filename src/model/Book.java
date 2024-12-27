package model;

import java.time.LocalDate;

/**
 * The {@code Book} class represents a book entity in the library system.
 * Each book has an auto-incremented unique ID, title, author, publication year, and genre.
 * It provides getter and setter methods for each attribute, along with a string representation.
 */
public class Book {
    private static int idCounter = 1; // Counter to generate unique IDs for books
    private int id; // Unique ID of the book
    private String title; // Title of the book
    private String author; // Author of the book
    private int publicationYear; // Publication year of the book
    private String genre; // Genre of the book

    /**
     * Constructs a new {@code Book} object with a unique ID and the specified attributes.
     *
     * @param title            the title of the book
     * @param author           the author of the book
     * @param publicationYear  the year the book was published
     * @param genre            the genre of the book
     */
    public Book(String title, String author, int publicationYear, String genre) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    /**
     * Constructs a new {@code Book} object with a specific ID and the specified attributes.
     *
     * @param id               the unique ID of the book
     * @param title            the title of the book
     * @param author           the author of the book
     * @param publicationYear  the year the book was published
     * @param genre            the genre of the book
     */
    public Book(int id, String title, String author, int publicationYear, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    /**
     * Gets the unique ID of the book.
     *
     * @return the unique ID of the book
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the starting value of the ID counter.
     * This method is used when loading books from a persistent storage.
     *
     * @param newIdCounter the new starting value for the ID counter
     */
    public static void setIdCounter(int newIdCounter) {
        idCounter = newIdCounter;
    }

    /**
     * Gets the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the new author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publication year of the book.
     *
     * @return the publication year of the book
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year of the book.
     *
     * @param publicationYear the new publication year of the book
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Gets the genre of the book.
     *
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre the new genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Returns a string representation of the {@code Book} object.
     * The format is: "ID,Title,Author,PublicationYear,Genre".
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + publicationYear + "," + genre;
    }
}

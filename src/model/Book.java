package model;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Book {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;

    public Book(String title, String author, int publicationYear, String genre) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public Book(int id, String title, String author, int publicationYear, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }
    
    public static void setIdCounter(int newIdCounter) {
        idCounter = newIdCounter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + publicationYear + "," + genre;
    }
}



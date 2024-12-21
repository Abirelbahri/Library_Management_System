package model;

public class Borrowing {
    private static int idCounter = 1;
    private int id;
    private int userId;
    private int bookId;
    private String borrowDate;
    private String dueDate;

    // Constructor
    public Borrowing(int userId, int bookId, String borrowDate, String dueDate) {
        this.id = idCounter++;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }


    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return id + "," + userId + "," + bookId + "," + borrowDate + "," + dueDate;
    }
}


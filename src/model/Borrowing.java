package model;

public class Borrowing {
    private static int idCounter = 1;
    private int id;
    private int userId;
    private int bookId;
    private String borrowDate;
    private String dueDate;
    private String status;
    

    // Constructor
    public Borrowing(int userId, int bookId, String borrowDate, String dueDate,String status) {
        this.id = idCounter++;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }
    
 // Constructor for CSV reading (with ID passed)
    public Borrowing(int id, int userId, int bookId, String borrowDate, String dueDate,String status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public static void setIdCounter(int newCounter) {
        idCounter = newCounter;
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
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    @Override
    public String toString() {
        return id + "," + userId + "," + bookId + "," + borrowDate + "," + dueDate+ "," + status;
    }
}


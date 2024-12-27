package model;

public class Return {
    private static int idCounter = 1;
    private int id;
    private int borrowingId;
    private String userName;
    private String bookTitle;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String status;
    private double penalty;

    // Constructor for creating new returns
    public Return(int borrowingId, String userName, String bookTitle, String borrowDate, String dueDate, String returnDate, String status, double penalty) {
        this.id = idCounter++;
        this.borrowingId = borrowingId;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.penalty = penalty;
    }

    // Constructor for loading from CSV (with ID passed)
    public Return(int id, int borrowingId, String userName, String bookTitle, String borrowDate, String dueDate, String returnDate, String status, double penalty) {
        this.id = id;
        this.borrowingId = borrowingId;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.penalty = penalty;
    }

    public static void setIdCounter(int newCounter) {
        idCounter = newCounter;
    }

    public int getId() {
        return id;
    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public String getUserName() {
        return userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public double getPenalty() {
        return penalty;
    }

    @Override
    public String toString() {
        return id + "," + borrowingId + "," + userName + "," + bookTitle + "," + borrowDate + "," + dueDate + "," + returnDate + "," + status + "," + penalty;
    }
}

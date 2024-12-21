package model;

public class Return {
    private static int idCounter = 1;
    private int id;
    private Borrowing borrowing;
    private String returnDate;
    private double penalty;

    // Constructor
    public Return(Borrowing borrowing, String returnDate) {
        this.id = idCounter++; 
        this.borrowing = borrowing;
        this.returnDate = returnDate;
        this.penalty = calculatePenalty();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public double getPenalty() {
        return penalty;
    }

    private double calculatePenalty() {
        // For simplicity,the penalty is calculated based on overdue days
        long diff = Long.parseLong(returnDate) - Long.parseLong(borrowing.getDueDate());
        long daysLate = diff / (1000 * 60 * 60 * 24); 
        
        if (daysLate > 0) {
            return daysLate * 1.0;
        }
        return 0;
    }

    @Override
    public String toString() {
        return id + "," + borrowing.getId() + "," + returnDate + "," + penalty;
    }
}


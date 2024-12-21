package controller;

import model.Borrowing;
import model.Book;
import model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowingController {
    private List<Borrowing> borrowings = new ArrayList<>();

    // Register a new borrowing
    public void borrowBook(Book book, User user, String borrowDate, String dueDate) {
        Borrowing newBorrowing = new Borrowing(user.getId(), book.getId(), borrowDate, dueDate);
        borrowings.add(newBorrowing);
        System.out.println("Book borrowed: " + newBorrowing);
    }

    // List all borrowings
    public void listBorrowings() {
        if (borrowings.isEmpty()) {
            System.out.println("No borrowings found.");
        } else {
            borrowings.forEach(System.out::println);
        }
    }

    // Get the borrowing history of a specific user by their ID
    public void viewBorrowingHistory(User user) {
        System.out.println("Borrowing history for user " + user.getName() + ":");
        borrowings.stream()
                .filter(borrowing -> borrowing.getUserId() == user.getId())
                .forEach(System.out::println);
    }

    // Extend the borrowing period for a specific borrowing by ID
    public void extendBorrowing(int id, String newDueDate) {
        Borrowing borrowing = getBorrowingById(id);
        if (borrowing != null) {
            borrowing.setDueDate(newDueDate);
            System.out.println("Borrowing extended: " + borrowing);
        } else {
            System.out.println("Borrowing with ID " + id + " not found.");
        }
    }

    // Search borrowings by book title, user name, or borrow date
    public List<Borrowing> searchBorrowings(String searchTerm) {
        return borrowings.stream()
                .filter(borrowing -> {
                    Book book = getBookById(borrowing.getBookId());
                    User user = getUserById(borrowing.getUserId());
                    return book != null && book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                           user != null && user.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                           borrowing.getBorrowDate().toLowerCase().contains(searchTerm.toLowerCase());
                })
                .collect(Collectors.toList());
    }

    // Filter borrowings by due date
    public List<Borrowing> filterBorrowingsByDueDate(String dueDate) {
        return borrowings.stream()
                .filter(borrowing -> borrowing.getDueDate().equals(dueDate))
                .collect(Collectors.toList());
    }

    // Get a borrowing by its ID
    public Borrowing getBorrowingById(int id) {
        return borrowings.stream()
                .filter(borrowing -> borrowing.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Get all borrowings (for testing or external use)
    public List<Borrowing> getAllBorrowings() {
        return borrowings;
    }

    // Helper method to get book by ID (for searching/filtering)
    private Book getBookById(int bookId) {
        // Assume BookController is initialized somewhere in the program and has a method to get books by ID
        return new BookController().getBookById(bookId);  // Assuming you have a BookController with this method
    }

    // Helper method to get user by ID (for searching/filtering)
    private User getUserById(int userId) {
        // Assume UserController is initialized somewhere in the program and has a method to get users by ID
        return new UserController().getUserById(userId);  // Assuming you have a UserController with this method
    }
}

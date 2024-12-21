package controller;

import model.Return;
import model.Borrowing;
import model.User;
import model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReturnController {
    private List<Return> returns = new ArrayList<>();

    // Register a return with the actual return date
    public void registerReturn(Borrowing borrowing, String returnDate) {
        Return newReturn = new Return(borrowing, returnDate);
        returns.add(newReturn);
        System.out.println("Book returned: " + newReturn);
    }

    // List all returns
    public void listReturns() {
        if (returns.isEmpty()) {
            System.out.println("No returns found.");
        } else {
            returns.forEach(System.out::println);
        }
    }

    // Get return history by user
    public void viewReturnHistory(User user) {
        System.out.println("Return history for user " + user.getName() + ":");
        returns.stream()
                .filter(ret -> ret.getBorrowing().getUserId() == user.getId())
                .forEach(System.out::println);
    }

    // Search returns by book title, user name, or return date
    public List<Return> searchReturns(String searchTerm) {
        return returns.stream()
                .filter(ret -> {
                    Book book = getBookById(ret.getBorrowing().getBookId());
                    User user = getUserById(ret.getBorrowing().getUserId());
                    return (book != null && book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) ||
                           (user != null && user.getName().toLowerCase().contains(searchTerm.toLowerCase())) ||
                           ret.getReturnDate().toLowerCase().contains(searchTerm.toLowerCase());
                })
                .collect(Collectors.toList());
    }

    // Filter returns by due date
    public List<Return> filterReturnsByDueDate(String dueDate) {
        return returns.stream()
                .filter(ret -> ret.getBorrowing().getDueDate().equals(dueDate))
                .collect(Collectors.toList());
    }

    // Get all returns (for testing or external use)
    public List<Return> getAllReturns() {
        return returns;
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


package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.BookController;
import controller.UserController;

public class StatisticsReport {

    // Method to get the most borrowed books
    public Map<String, Integer> getMostBorrowedBooks(List<Borrowing> borrowings, BookController bookController) {
        Map<String, Integer> bookCount = new HashMap<>();
        for (Borrowing borrowing : borrowings) {
            Book book = bookController.getBookById(borrowing.getBookId());
            if (book != null) {
                String bookTitle = book.getTitle();
                bookCount.put(bookTitle, bookCount.getOrDefault(bookTitle, 0) + 1);
            }
        }
        return bookCount;
    }

    // Method to get the most active users
    public Map<String, Integer> getMostActiveUsers(List<Borrowing> borrowings, UserController userController) {
        Map<String, Integer> userCount = new HashMap<>();
        for (Borrowing borrowing : borrowings) {
            User user = userController.getUserById(borrowing.getUserId());
            if (user != null) {
                String userName = user.getName();
                userCount.put(userName, userCount.getOrDefault(userName, 0) + 1);
            }
        }
        return userCount;
    }

    // Method to generate general statistics about the library
    public void generateGeneralStatistics(List<Book> books, List<User> users, List<Borrowing> borrowings) {
        System.out.println("Library Statistics:");
        System.out.println("Total Books: " + books.size());
        System.out.println("Total Users: " + users.size());
        System.out.println("Total Borrowings: " + borrowings.size());
    }
}


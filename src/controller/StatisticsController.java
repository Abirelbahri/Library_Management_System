package controller;

import model.Book;
import model.User;
import model.Borrowing;
import model.StatisticsReport;
import java.util.List;
import java.util.Map;

public class StatisticsController {

    private StatisticsReport statisticsReport = new StatisticsReport();

    // Method to show the most borrowed books
    public void showMostBorrowedBooks(List<Borrowing> borrowings, BookController bookController) {
        Map<String, Integer> mostBorrowedBooks = statisticsReport.getMostBorrowedBooks(borrowings, bookController);
        System.out.println("Most Borrowed Books:");
        mostBorrowedBooks.forEach((bookTitle, count) -> System.out.println(bookTitle + ": " + count + " times"));
    }

    // Method to show the most active users
    public void showMostActiveUsers(List<Borrowing> borrowings, UserController userController) {
        Map<String, Integer> mostActiveUsers = statisticsReport.getMostActiveUsers(borrowings, userController);
        System.out.println("Most Active Users:");
        mostActiveUsers.forEach((userName, count) -> System.out.println(userName + ": " + count + " borrowings"));
    }

    // Method to show general library statistics
    public void showGeneralStatistics(List<Book> books, List<User> users, List<Borrowing> borrowings) {
        statisticsReport.generateGeneralStatistics(books, users, borrowings);
    }
}

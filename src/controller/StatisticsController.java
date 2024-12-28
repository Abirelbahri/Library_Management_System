package controller;

import model.Borrowing;
import model.StatisticsReport;
import model.User;
import model.Book;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsController {
    private List<Borrowing> borrowings;
    private List<User> users;
    private List<Book> books;

    public StatisticsController(List<Borrowing> borrowings, List<User> users, List<Book> books) {
        this.borrowings = borrowings;
        this.users = users;
        this.books = books;
    }

    public List<StatisticsReport> generateStatistics() {
        List<StatisticsReport> stats = new ArrayList<>();

        Map<Integer, Long> bookBorrowCounts = borrowings.stream()
                .collect(Collectors.groupingBy(Borrowing::getBookId, Collectors.counting()));

        int mostBorrowedBookId = bookBorrowCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);

        String mostBorrowedBookTitle = books.stream()
                .filter(book -> book.getId() == mostBorrowedBookId)
                .map(Book::getTitle)
                .findFirst()
                .orElse("No Data");

        stats.add(new StatisticsReport("Most Borrowed Book", mostBorrowedBookTitle, bookBorrowCounts.getOrDefault(mostBorrowedBookId, 0L).intValue()));

        Map<Integer, Long> userActivityCounts = borrowings.stream()
                .collect(Collectors.groupingBy(Borrowing::getUserId, Collectors.counting()));

        int mostActiveUserId = userActivityCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);

        String mostActiveUserName = users.stream()
                .filter(user -> user.getId() == mostActiveUserId)
                .map(User::getName)
                .findFirst()
                .orElse("No Data");

        stats.add(new StatisticsReport("Most Active User", mostActiveUserName, userActivityCounts.getOrDefault(mostActiveUserId, 0L).intValue()));

        stats.add(new StatisticsReport("Total Books", "Total number of books in the library", books.size()));
        stats.add(new StatisticsReport("Total Users", "Total number of registered users", users.size()));
        stats.add(new StatisticsReport("Total Borrowings", "Total number of borrowings", borrowings.size()));

        return stats;
    }
}

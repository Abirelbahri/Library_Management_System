//package controller;
//
//import model.*;
//import utils.CSVUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class StatisticsController {
//
//    private StatisticsReport statisticsReport = new StatisticsReport();
//    private String booksFilePath;
//    private String usersFilePath;
//    private String borrowingsFilePath;
//
//    public StatisticsController(String booksFilePath, String usersFilePath, String borrowingsFilePath) {
//        this.booksFilePath = booksFilePath;
//        this.usersFilePath = usersFilePath;
//        this.borrowingsFilePath = borrowingsFilePath;
//    }
//
//    private List<Book> loadBooksFromCSV() {
//        List<Book> bookList = new ArrayList<>();
//        List<String[]> data = CSVUtils.readFromCSV(booksFilePath);
//        for (String[] row : data) {
//            try {
//                int id = Integer.parseInt(row[0]);
//                String title = row[1];
//                String author = row[2];
//                int publicationYear = Integer.parseInt(row[3]);
//                String genre = row[4];
//                bookList.add(new Book(id, title, author, publicationYear, genre));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
//        return bookList;
//    }
//
//    private List<User> loadUsersFromCSV() {
//        List<User> userList = new ArrayList<>();
//        List<String[]> data = CSVUtils.readFromCSV(usersFilePath);
//        for (String[] row : data) {
//            try {
//                int id = Integer.parseInt(row[0]);
//                String name = row[1];
//                String email = row[2];
//                String role = row[3];
//                userList.add(new User(id, name, email, role));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
//        return userList;
//    }
//
//    private List<Borrowing> loadBorrowingsFromCSV() {
//        List<Borrowing> borrowingList = new ArrayList<>();
//        List<String[]> data = CSVUtils.readFromCSV(borrowingsFilePath);
//        for (String[] row : data) {
//            try {
//                int id = Integer.parseInt(row[0]);
//                int userId = Integer.parseInt(row[1]);
//                int bookId = Integer.parseInt(row[2]);
//                String borrowDate = row[3];
//                String dueDate = row[4];
//                borrowingList.add(new Borrowing(id, userId, bookId, borrowDate, dueDate));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
//        return borrowingList;
//    }
//
//    public void showMostBorrowedBooks() {
//        List<Borrowing> borrowings = loadBorrowingsFromCSV();
//        List<Book> books = loadBooksFromCSV();
//        Map<String, Integer> mostBorrowedBooks = statisticsReport.getMostBorrowedBooks(borrowings, new BookController(booksFilePath));
//        System.out.println("Most Borrowed Books:");
//        mostBorrowedBooks.forEach((bookTitle, count) -> System.out.println(bookTitle + ": " + count + " times"));
//    }
//
//    public void showMostActiveUsers() {
//        List<Borrowing> borrowings = loadBorrowingsFromCSV();
//        List<User> users = loadUsersFromCSV();
//        Map<String, Integer> mostActiveUsers = statisticsReport.getMostActiveUsers(borrowings, new UserController(usersFilePath));
//        System.out.println("Most Active Users:");
//        mostActiveUsers.forEach((userName, count) -> System.out.println(userName + ": " + count + " borrowings"));
//    }
//
//    public void showGeneralStatistics() {
//        List<Book> books = loadBooksFromCSV();
//        List<User> users = loadUsersFromCSV();
//        List<Borrowing> borrowings = loadBorrowingsFromCSV();
//        statisticsReport.generateGeneralStatistics(books, users, borrowings);
//    }
//}

import controller.BookController;
import controller.UserController;
import controller.BorrowingController;
import controller.StatisticsController;
import model.Book;
import model.User;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Initialize controllers
        BookController bookController = new BookController();
        UserController userController = new UserController();
        BorrowingController borrowingController = new BorrowingController();
        StatisticsController statisticsController = new StatisticsController();

        // Create books
        bookController.addBook("1984", "George Orwell", 1949, "Dystopian");
        bookController.addBook("To Kill a Mockingbird", "Harper Lee", 1960, "Fiction");
        bookController.addBook("Moby Dick", "Herman Melville", 1851, "Adventure");

        // Create users
        userController.registerUser("John Doe", "john.doe@example.com", "member");
        userController.registerUser("Jane Smith", "jane.smith@example.com", "librarian");

        // Borrow books
        Book book1 = bookController.getBookById(1);  
        Book book2 = bookController.getBookById(2);  
        User user1 = userController.getUserById(1);  
        User user2 = userController.getUserById(2);  
        Date borrowDate1 = new Date();
        Date dueDate1 = new Date(borrowDate1.getTime() + (7L * 24 * 60 * 60 * 1000)); // 1 week later
        Date borrowDate2 = new Date();
        Date dueDate2 = new Date(borrowDate2.getTime() + (7L * 24 * 60 * 60 * 1000)); // 1 week later
        borrowingController.borrowBook(book1, user1, borrowDate1.toString(), dueDate1.toString());
        borrowingController.borrowBook(book2, user2, borrowDate2.toString(), dueDate2.toString());
        borrowingController.borrowBook(book1, user2, borrowDate2.toString(), dueDate2.toString());  // Jane also borrows "1984"

        // Show statistics
        statisticsController.showMostBorrowedBooks(borrowingController.getAllBorrowings(), bookController);
        statisticsController.showMostActiveUsers(borrowingController.getAllBorrowings(), userController);
        statisticsController.showGeneralStatistics(bookController.getAllBooks(), userController.getAllUsers(), borrowingController.getAllBorrowings());
    }
}

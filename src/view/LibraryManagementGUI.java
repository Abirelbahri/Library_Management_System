package view;

import controller.BookController;
import controller.UserController;
import controller.BorrowingController;
import controller.ReturnController;
import model.Book;
import model.User;
import model.Borrowing;
import model.Return;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LibraryManagementGUI extends JFrame {
    private JTable booksTable, usersTable, borrowingsTable, returnsTable;
    private DefaultTableModel booksTableModel, usersTableModel, borrowingsTableModel, returnsTableModel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private BookController bookController;
    private UserController userController;
    private BorrowingController borrowingController;
    private ReturnController returnController;

    public LibraryManagementGUI() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        bookController = new BookController("books.csv");
        userController = new UserController("users.csv");
        borrowingController = new BorrowingController("borrowings.csv");
        returnController = new ReturnController("returns.csv");

        initializeSidePanel();

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        initializeBooksPanel();
        initializeUsersPanel();
        initializeBorrowingsPanel();
        initializeReturnsPanel();

        container.add(mainPanel, BorderLayout.CENTER);

        // Show books panel by default
        loadBookList();
        cardLayout.show(mainPanel, "BOOKS");
    }

    private void initializeSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(200, 0));
        sidePanel.setBackground(new Color(51, 51, 51));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        JButton booksButton = createNavButton("Books");
        booksButton.addActionListener(e -> {
            loadBookList();
            cardLayout.show(mainPanel, "BOOKS");
        });
        sidePanel.add(booksButton);

        JButton usersButton = createNavButton("Users");
        usersButton.addActionListener(e -> {
            loadUserList();
            cardLayout.show(mainPanel, "USERS");
        });
        sidePanel.add(usersButton);

        JButton borrowingsButton = createNavButton("Borrowings");
        borrowingsButton.addActionListener(e -> {
            loadBorrowingList();
            cardLayout.show(mainPanel, "BORROWINGS");
        });
        sidePanel.add(borrowingsButton);

        JButton returnsButton = createNavButton("Returns");
        returnsButton.addActionListener(e -> {
            loadReturnList();
            cardLayout.show(mainPanel, "RETURNS");
        });
        sidePanel.add(returnsButton);

        getContentPane().add(sidePanel, BorderLayout.WEST);
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 51, 51));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 51, 51)); // Dark gray background
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
        return button;
    }


    private void initializeBooksPanel() {
        JPanel booksPanel = new JPanel(new BorderLayout());

        String[] columns = {"ID", "Title", "Author", "Year", "Genre"};
        booksTableModel = new DefaultTableModel(columns, 0);
        booksTable = new JTable(booksTableModel);
        booksPanel.add(new JScrollPane(booksTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = createStyledButton("Add Book");
        JButton deleteButton = createStyledButton("Delete Book");
        JButton modifyButton = createStyledButton("Modify Book");
        JButton refreshButton = createStyledButton("Refresh");
        JButton searchButton = createStyledButton("Search");

        addButton.addActionListener(e -> showAddBookDialog());
        deleteButton.addActionListener(e -> deleteSelectedBook());
        modifyButton.addActionListener(e -> showModifyBookDialog());
        refreshButton.addActionListener(e -> loadBookList());
        searchButton.addActionListener(e -> showSearchBookDialog());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(searchButton);

        booksPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(booksPanel, "BOOKS");
    }

	private void initializeUsersPanel() {
        JPanel usersPanel = new JPanel(new BorderLayout());

        String[] columns = {"ID", "Name", "Email", "Role"};
        usersTableModel = new DefaultTableModel(columns, 0);
        usersTable = new JTable(usersTableModel);
        usersPanel.add(new JScrollPane(usersTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = createStyledButton("Add User");
        JButton deleteButton = createStyledButton("Delete User");
        JButton modifyButton = createStyledButton("Modify User");
        JButton refreshButton = createStyledButton("Refresh");
        JButton searchButton = createStyledButton("Search");

        addButton.addActionListener(e -> showAddUserDialog());
        deleteButton.addActionListener(e -> deleteSelectedUser());
        modifyButton.addActionListener(e -> showModifyUserDialog());
        refreshButton.addActionListener(e -> loadUserList());
        searchButton.addActionListener(e -> showSearchUserDialog());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(searchButton);

        usersPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(usersPanel, "USERS");
    }

	private void initializeBorrowingsPanel() {
	    JPanel borrowingsPanel = new JPanel(new BorderLayout());

	    // Update columns to include User Name, Book Title, and Status
	    String[] columns = {"ID", "User Name", "Book Title", "Borrow Date", "Due Date", "Status"};
	    borrowingsTableModel = new DefaultTableModel(columns, 0);
	    borrowingsTable = new JTable(borrowingsTableModel);
	    borrowingsPanel.add(new JScrollPane(borrowingsTable), BorderLayout.CENTER);

	    // Buttons for Borrowings
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    JButton addButton = createStyledButton("Add Borrowing");
	    JButton deleteButton = createStyledButton("Delete Borrowing");
	    JButton returnButton = createStyledButton("Mark as Returned");
	    JButton refreshButton = createStyledButton("Refresh");

	    addButton.addActionListener(e -> showAddBorrowingDialog());
	    deleteButton.addActionListener(e -> deleteSelectedBorrowing());
	    returnButton.addActionListener(e -> markBorrowingAsReturned());
	    refreshButton.addActionListener(e -> loadBorrowingList());

	    buttonPanel.add(addButton);
	    buttonPanel.add(deleteButton);
	    buttonPanel.add(returnButton);
	    buttonPanel.add(refreshButton);

	    borrowingsPanel.add(buttonPanel, BorderLayout.SOUTH);
	    mainPanel.add(borrowingsPanel, "BORROWINGS");
	}


	private void initializeReturnsPanel() {
	    JPanel returnsPanel = new JPanel(new BorderLayout());

	    // Updated columns to match requirements
	    String[] columns = {"Return ID", "Borrowing ID", "User Name", "Book Title", "Borrow Date", "Due Date", "Return Date", "Status", "Penalty"};
	    returnsTableModel = new DefaultTableModel(columns, 0);
	    returnsTable = new JTable(returnsTableModel);
	    returnsPanel.add(new JScrollPane(returnsTable), BorderLayout.CENTER);

	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    JButton refreshButton = createStyledButton("Refresh");

	    refreshButton.addActionListener(e -> loadReturnList());

	    buttonPanel.add(refreshButton);

	    returnsPanel.add(buttonPanel, BorderLayout.SOUTH);
	    mainPanel.add(returnsPanel, "RETURNS");
	}


    private void showAddBookDialog() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField genreField = new JTextField();

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Publication Year:"));
        panel.add(yearField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Add New Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                String author = authorField.getText();
                int year = Integer.parseInt(yearField.getText());
                String genre = genreField.getText();

                bookController.addBook(title, author, year, genre);
                loadBookList();
                JOptionPane.showMessageDialog(this, "Book added successfully.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please ensure all fields are correctly filled.");
            }
        }
    }


    private void showModifyBookDialog() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to modify.");
            return;
        }

        int bookId = (int) booksTableModel.getValueAt(selectedRow, 0);
        Book book = bookController.getBookById(bookId); // Ensure this method exists in BookController

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField titleField = new JTextField(book.getTitle());
        JTextField authorField = new JTextField(book.getAuthor());
        JTextField yearField = new JTextField(String.valueOf(book.getPublicationYear()));
        JTextField genreField = new JTextField(book.getGenre());

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Publication Year:"));
        panel.add(yearField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Modify Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String newTitle = titleField.getText();
                String newAuthor = authorField.getText();
                int newYear = Integer.parseInt(yearField.getText());
                String newGenre = genreField.getText();

                bookController.modifyBook(bookId, newTitle, newAuthor, newYear, newGenre);
                loadBookList();
                JOptionPane.showMessageDialog(this, "Book updated successfully.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please ensure all fields are correctly filled.");
            }
        }
    }


    private void showSearchBookDialog() {
        String searchTerm = JOptionPane.showInputDialog(this, "Enter search term:");
        List<Book> results = bookController.searchBooks(searchTerm);
        booksTableModel.setRowCount(0);
        for (Book book : results) {
            booksTableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getGenre()});
        }
    }

    private void showAddUserDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField roleField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Add New User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                String role = roleField.getText();

                userController.registerUser(name, email, role);
                loadUserList();
                JOptionPane.showMessageDialog(this, "User added successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please ensure all fields are correctly filled.");
            }
        }
    }

    private void showModifyUserDialog() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to modify.");
            return;
        }

        int userId = (int) usersTableModel.getValueAt(selectedRow, 0);
        User user = userController.getUserById(userId); // Ensure this method exists in UserController

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JTextField nameField = new JTextField(user.getName());
        JTextField emailField = new JTextField(user.getEmail());
        JTextField roleField = new JTextField(user.getRole());

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Modify User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String newName = nameField.getText();
                String newEmail = emailField.getText();
                String newRole = roleField.getText();

                userController.modifyUser(userId, newName, newEmail, newRole);
                loadUserList();
                JOptionPane.showMessageDialog(this, "User updated successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please ensure all fields are correctly filled.");
            }
        }
    }


    private void showSearchUserDialog() {
        String searchTerm = JOptionPane.showInputDialog(this, "Enter search term:");
        List<User> results = userController.searchUsers(searchTerm);
        usersTableModel.setRowCount(0);
        for (User user : results) {
            usersTableModel.addRow(new Object[]{user.getId(), user.getName(), user.getEmail(), user.getRole()});
        }
    }
    
    private void deleteSelectedBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to delete.");
            return;
        }

        int bookId = (int) booksTableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete the selected book?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            bookController.deleteBook(bookId); // Assuming deleteBook is implemented in BookController
            loadBookList();
            JOptionPane.showMessageDialog(this, "Book deleted successfully.");
        }
    }


    private void deleteSelectedUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }

        int userId = (int) usersTableModel.getValueAt(selectedRow, 0);
        userController.deleteUser(userId);
        loadUserList();
    }

    private void showAddBorrowingDialog() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JComboBox<String> userDropdown = new JComboBox<>();
        JComboBox<String> bookDropdown = new JComboBox<>();

        // Populate dropdowns
        for (User user : userController.getAllUsers()) {
            userDropdown.addItem(user.getId() + " - " + user.getName());
        }
        for (Book book : bookController.getAllBooks()) {
            bookDropdown.addItem(book.getId() + " - " + book.getTitle());
        }

        JTextField borrowDateField = new JTextField(java.time.LocalDate.now().toString());
        JTextField dueDateField = new JTextField();

        panel.add(new JLabel("Select User:"));
        panel.add(userDropdown);
        panel.add(new JLabel("Select Book:"));
        panel.add(bookDropdown);
        panel.add(new JLabel("Borrow Date:"));
        panel.add(borrowDateField);
        panel.add(new JLabel("Due Date:"));
        panel.add(dueDateField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Add Borrowing", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int userId = Integer.parseInt(userDropdown.getSelectedItem().toString().split(" - ")[0]);
                int bookId = Integer.parseInt(bookDropdown.getSelectedItem().toString().split(" - ")[0]);
                String borrowDate = borrowDateField.getText();
                String dueDate = dueDateField.getText();

                borrowingController.borrowBook(userId, bookId, borrowDate, dueDate, "Not Returned");
                loadBorrowingList();
                JOptionPane.showMessageDialog(this, "Borrowing added successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please ensure all fields are correctly filled.");
            }
        }
    }
    
    

    private void markBorrowingAsReturned() {
        int selectedRow = borrowingsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a borrowing to mark as returned.");
            return;
        }

        // Retrieve the borrowing ID
        int borrowingId = (int) borrowingsTableModel.getValueAt(selectedRow, 0);
        Borrowing borrowing = borrowingController.getBorrowingById(borrowingId);

        if (borrowing == null) {
            JOptionPane.showMessageDialog(this, "Borrowing not found.");
            return;
        }

        // Prompt for return date
        String returnDate = JOptionPane.showInputDialog(this, "Enter Return Date (YYYY-MM-DD):", java.time.LocalDate.now().toString());
        if (returnDate == null || returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid return date.");
            return;
        }

        try {
            // Register return with penalty calculation, passing user and book controllers
            returnController.registerReturnWithPenalty(borrowing, returnDate, userController, bookController);

            // Remove borrowing from the borrowings list
            borrowingController.removeBorrowing(borrowingId);

            // Refresh both tables
            loadBorrowingList();
            loadReturnList();

            JOptionPane.showMessageDialog(this, "Borrowing marked as returned.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
        }
    }




    private void deleteSelectedBorrowing() {
        int selectedRow = borrowingsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a borrowing to delete.");
            return;
        }

        int borrowingId = (int) borrowingsTableModel.getValueAt(selectedRow, 0);
        borrowingController.deleteBorrowing(borrowingId);
        loadBorrowingList();
    }

    private void showAddReturnDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JTextField borrowingIdField = new JTextField();
        JTextField returnDateField = new JTextField(java.time.LocalDate.now().toString()); // Pre-filled with today's date

        panel.add(new JLabel("Borrowing ID:"));
        panel.add(borrowingIdField);
        panel.add(new JLabel("Return Date (YYYY-MM-DD):"));
        panel.add(returnDateField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Add Return", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int borrowingId = Integer.parseInt(borrowingIdField.getText());
                String returnDate = returnDateField.getText();

                // Fetch the borrowing object
                Borrowing borrowing = borrowingController.getBorrowingById(borrowingId);
                if (borrowing == null) {
                    JOptionPane.showMessageDialog(this, "Borrowing ID not found.");
                    return;
                }

                // Register the return with penalty
                returnController.registerReturnWithPenalty(borrowing, returnDate, userController, bookController);

                // Refresh the returns list
                loadReturnList();
                JOptionPane.showMessageDialog(this, "Return added successfully.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please ensure Borrowing ID is a number.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
            }
        }
    }


    private void deleteSelectedReturn() {
        int selectedRow = returnsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a return to delete.");
            return;
        }

        int returnId = (int) returnsTableModel.getValueAt(selectedRow, 0);
        returnController.deleteReturn(returnId);
        loadReturnList();
    }

    private void loadBookList() {
        List<Book> books = bookController.getAllBooks();
        booksTableModel.setRowCount(0);
        for (Book book : books) {
            booksTableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getGenre()});
        }
    }

    private void loadUserList() {
        List<User> users = userController.getAllUsers();
        usersTableModel.setRowCount(0);
        for (User user : users) {
            usersTableModel.addRow(new Object[]{user.getId(), user.getName(), user.getEmail(), user.getRole()});
        }
    }

    private void loadBorrowingList() {
        List<Borrowing> borrowings = borrowingController.getAllBorrowings();
        borrowingsTableModel.setRowCount(0);

        for (Borrowing borrowing : borrowings) {
            // Fetch user name and book title using their respective IDs
            User user = userController.getUserById(borrowing.getUserId());
            Book book = bookController.getBookById(borrowing.getBookId());
            String userName = user != null ? user.getName() : "Unknown User";
            String bookTitle = book != null ? book.getTitle() : "Unknown Book";

            // Add row to the table
            borrowingsTableModel.addRow(new Object[]{
                borrowing.getId(),
                userName,
                bookTitle,
                borrowing.getBorrowDate(),
                borrowing.getDueDate(),
                borrowing.getStatus()
            });
        }
    }


    private void loadReturnList() {
        List<Return> returns = returnController.getAllReturns();
        returnsTableModel.setRowCount(0);

        for (Return ret : returns) {
            returnsTableModel.addRow(new Object[]{
                ret.getId(),
                ret.getBorrowingId(),
                ret.getUserName(),
                ret.getBookTitle(),
                ret.getBorrowDate(),
                ret.getDueDate(),
                ret.getReturnDate(),
                ret.getStatus(),
                ret.getPenalty()
            });
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementGUI gui = new LibraryManagementGUI();
            gui.setVisible(true);
        });
    }
}

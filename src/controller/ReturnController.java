package controller;

import model.Return;
import model.Book;
import model.Borrowing;

import model.Return;
import model.Borrowing;
import utils.CSVUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReturnController {
    private List<Return> returns;
    private String filePath;

    public ReturnController(String filePath) {
        this.filePath = filePath;
        this.returns = loadReturnsFromCSV();
        
        if (!returns.isEmpty()) {
            int maxId = returns.stream().mapToInt(Return::getId).max().orElse(1);
            Return.setIdCounter(maxId + 1);
        }
    }

    private List<Return> loadReturnsFromCSV() {
        List<Return> returnList = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(filePath);

        for (String[] row : data) {
            try {
                int id = Integer.parseInt(row[0]);
                int borrowingId = Integer.parseInt(row[1]);
                String userName = row[2];
                String bookTitle = row[3];
                String borrowDate = row[4];
                String dueDate = row[5];
                String returnDate = row[6];
                String status = row[7];
                double penalty = Double.parseDouble(row[8]);
                returnList.add(new Return(id, borrowingId, userName, bookTitle, borrowDate, dueDate, returnDate, status, penalty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }

    
    public void registerReturn(Return ret) {
        returns.add(ret);
        saveReturnsToCSV();
    }
    
    
    public void registerReturnWithPenalty(Borrowing borrowing, String returnDate, UserController userController, BookController bookController) {
        // Calculate penalty if the return date is after the due date
        double penalty = 0.0;
        java.time.LocalDate dueDate = java.time.LocalDate.parse(borrowing.getDueDate());
        java.time.LocalDate actualReturnDate = java.time.LocalDate.parse(returnDate);

        if (actualReturnDate.isAfter(dueDate)) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, actualReturnDate);
            penalty = daysLate * 1.0; // Assuming $1/day penalty
        }

        // Fetch user name and book title
        String userName = userController.getUserById(borrowing.getUserId()).getName();
        String bookTitle = bookController.getBookById(borrowing.getBookId()).getTitle();

        // Register the return
        Return ret = new Return(
                borrowing.getId(),
                userName,
                bookTitle,
                borrowing.getBorrowDate(),
                borrowing.getDueDate(),
                returnDate,
                "Returned",
                penalty
        );
        registerReturn(ret);
    }




    private void saveReturnsToCSV() {
        List<String[]> data = returns.stream()
                .map(ret -> new String[]{
                        String.valueOf(ret.getId()),
                        String.valueOf(ret.getBorrowingId()),
                        ret.getUserName(),
                        ret.getBookTitle(),
                        ret.getBorrowDate(),
                        ret.getDueDate(),
                        ret.getReturnDate(),
                        ret.getStatus(),
                        String.valueOf(ret.getPenalty())
                })
                .collect(Collectors.toList());
        CSVUtils.writeToCSV(filePath, data);
    }
    

    public List<Return> searchReturns(String searchTerm) {
        return returns.stream()
                .filter(ret -> ret.getReturnDate().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                String.valueOf(ret.getBorrowingId()).contains(searchTerm))
                .collect(Collectors.toList());
    }

    public void listReturns() {
        returns.forEach(System.out::println);
    }

    public Return getReturnById(int id) {
        return returns.stream()
                .filter(ret -> ret.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public void deleteReturn(int returnId) {
        Return returnRecord = getReturnById(returnId);
        if (returnRecord != null) {
            returns.remove(returnRecord);
            saveReturnsToCSV();
        } else {
            System.err.println("Return ID not found.");
        }
    }
    
    public List<Return> getReturnsByUser(int userId) {
        return returns.stream()
                .filter(ret -> ret.getBorrowingId() != -1) // Ensure the borrowing exists
                .collect(Collectors.toList());
    }

    

    public List<Return> getAllReturns() {
        return returns;
    }
}

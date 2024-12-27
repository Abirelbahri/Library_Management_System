package controller;

import model.Borrowing;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowingController {
    private List<Borrowing> borrowings;
    private String filePath;

    public BorrowingController(String filePath) {
        this.filePath = filePath;
        this.borrowings = loadBorrowingsFromCSV();
        
        if (!borrowings.isEmpty()) {
            int maxId = borrowings.stream().mapToInt(Borrowing::getId).max().orElse(1);
            Borrowing.setIdCounter(maxId + 1);
        }
    }

    private List<Borrowing> loadBorrowingsFromCSV() {
        List<Borrowing> borrowingList = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(filePath);
        for (String[] row : data) {
            try {
                int id = Integer.parseInt(row[0]);
                int userId = Integer.parseInt(row[1]);
                int bookId = Integer.parseInt(row[2]);
                String borrowDate = row[3];
                String dueDate = row[4];
                String status = row[5];
                borrowingList.add(new Borrowing(id, userId, bookId, borrowDate, dueDate, status));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return borrowingList;
    }
    
    public void markAsReturned(int borrowingId) {
        Borrowing borrowing = getBorrowingById(borrowingId);
        if (borrowing != null) {
            borrowing.setStatus("Returned");
            saveBorrowingsToCSV();
        }
    }

    private void saveBorrowingsToCSV() {
        List<String[]> data = borrowings.stream()
                .map(borrowing -> new String[]{
                        String.valueOf(borrowing.getId()),
                        String.valueOf(borrowing.getUserId()),
                        String.valueOf(borrowing.getBookId()),
                        borrowing.getBorrowDate(),
                        borrowing.getDueDate(),
                        borrowing.getStatus()
                })
                .collect(Collectors.toList());
        CSVUtils.writeToCSV(filePath, data);
    }

    public void borrowBook(int userId, int bookId, String borrowDate, String dueDate,String status) {
        Borrowing newBorrowing = new Borrowing(userId, bookId, borrowDate, dueDate,"Not Returned");
        borrowings.add(newBorrowing);
        saveBorrowingsToCSV();
    }

    public void extendBorrowing(int id, String newDueDate) {
        Borrowing borrowing = getBorrowingById(id);
        if (borrowing != null) {
            borrowing.setDueDate(newDueDate);
            saveBorrowingsToCSV();
        }
    }
    
    public void removeBorrowing(int borrowingId) {
        Borrowing borrowing = getBorrowingById(borrowingId);
        if (borrowing != null) {
            borrowings.remove(borrowing);
            saveBorrowingsToCSV();
        }
    }

    public List<Borrowing> searchBorrowings(String searchTerm) {
        return borrowings.stream()
                .filter(borrowing -> borrowing.getBorrowDate().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                String.valueOf(borrowing.getUserId()).contains(searchTerm) ||
                                String.valueOf(borrowing.getBookId()).contains(searchTerm))
                .collect(Collectors.toList());
    }

    public void listBorrowings() {
        borrowings.forEach(System.out::println);
    }

    public Borrowing getBorrowingById(int id) {
        return borrowings.stream()
                .filter(borrowing -> borrowing.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public void deleteBorrowing(int borrowingId) {
        Borrowing borrowing = getBorrowingById(borrowingId);
        if (borrowing != null) {
            borrowings.remove(borrowing);
            saveBorrowingsToCSV();
        }
    }


    public List<Borrowing> getAllBorrowings() {
        return borrowings;
    }
}

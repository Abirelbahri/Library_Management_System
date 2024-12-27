package controller;

import model.User;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    private List<User> users;
    private String filePath;

    public UserController(String filePath) {
        this.filePath = filePath;
        this.users = loadUsersFromCSV();
        
        if (!users.isEmpty()) {
            int maxId = users.stream().mapToInt(User::getId).max().orElse(1);
            User.setIdCounter(maxId + 1);
        }
    }

    private List<User> loadUsersFromCSV() {
        List<User> userList = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(filePath);
        for (String[] row : data) {
            try {
                int id = Integer.parseInt(row[0]);
                String name = row[1];
                String email = row[2];
                String role = row[3];
                userList.add(new User(id, name, email, role));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    private void saveUsersToCSV() {
        List<String[]> data = users.stream()
                .map(user -> new String[]{
                        String.valueOf(user.getId()),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                })
                .collect(Collectors.toList());
        CSVUtils.writeToCSV(filePath, data);
    }

    public void registerUser(String name, String email, String role) {
        User newUser = new User(name, email, role);
        users.add(newUser);
        saveUsersToCSV();
    }

    public void modifyUser(int id, String name, String email, String role) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setRole(role);
            saveUsersToCSV();
        }
    }

    public void deleteUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            users.remove(user);
            saveUsersToCSV();
        }
    }

    public List<User> searchUsers(String searchTerm) {
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                user.getEmail().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                user.getRole().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByRole(String role) {
        return users.stream()
                .filter(user -> user.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public void listUsers() {
        users.forEach(System.out::println);
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return users;
    }
}

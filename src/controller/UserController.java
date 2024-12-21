package controller;

import model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    private List<User> users = new ArrayList<>();

    // Register a new user with personal information and role
    public void registerUser(String name, String email, String role) {
        User newUser = new User(name, email, role);
        users.add(newUser);
        System.out.println("User registered: " + newUser);
    }

    // Modify an existing user's information by ID
    public void modifyUser(int id, String name, String email, String role) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setRole(role);
            System.out.println("User modified: " + user);
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }

    // Delete a user by ID
    public void deleteUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            users.remove(user);
            System.out.println("User deleted: " + user);
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }

    // Search for users by name, email, or role (supports partial matching)
    public List<User> searchUsers(String searchTerm) {
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                user.getEmail().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                user.getRole().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Filter users by role
    public List<User> filterUsersByRole(String role) {
        return users.stream()
                .filter(user -> user.getRole().toLowerCase().equals(role.toLowerCase()))
                .collect(Collectors.toList());
    }

    // List all users
    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            users.forEach(System.out::println);
        }
    }

    // Get a user by their ID
    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Get all users (for testing or external use)
    public List<User> getAllUsers() {
        return users;
    }
}

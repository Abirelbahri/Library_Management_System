package model;

public class User {
    private static int idCounter = 1; 
    private int id;
    private String name;
    private String email;
    private String role;

    // Constructor
    public User(String name, String email, String role) {
        this.id = idCounter++;  
        this.name= name;
        this.email = email;
        this.role = role;
    }
    
 // Constructor for CSV reading (with ID passed)
    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public static void setIdCounter(int newIdCounter) {
        idCounter = newIdCounter;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + role;
    }
}


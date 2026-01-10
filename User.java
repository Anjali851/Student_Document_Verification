
// Abstract class representing a generic user in the system
public abstract class User {
    protected String userId;    // Unique identifier for the user
    protected String name;      // Name of the user
    protected String email;     // Email address of the user
    protected String password;  // Password for authentication
                                       
    // Constructor to initialize user details
    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Abstract method that must be implemented by subclasses
    public abstract void displayDashboard();

    // Getter for userId
    public String getUserId() {
        return userId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Protected setter for password (not publicly exposed)
    protected void setPassword(String password) {
        this.password = password;
    }

    // Verifies if the input password matches the stored password
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

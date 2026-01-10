
import java.util.List;


/**
 * Admin class extending the base User class
 */
public class Admin extends User {
    private String adminId;        // Admin ID
    private String department;     // Admin's department

    // Constructor to initialize admin fields
    public Admin(String userId, String name, String email, String password, 
                 String adminId, String department) {
        super(userId, name, email, password);  // Call superclass constructor
        this.adminId = adminId;
        this.department = department;
    }

    // Display admin dashboard details
    @Override
    public void displayDashboard() {
        System.out.println("Admin Dashboard");
        System.out.println("Name: " + name);
        System.out.println("Admin ID: " + adminId);
        System.out.println("Department: " + department);
    }

    // Verify and update document status
    public void verifyDocument(Document document, boolean isApproved, String comments) {
        if (isApproved) {
            document.approve(comments);  // Approve document
        } else {
            document.reject(comments);   // Reject document
        }
    }

    // Return admin ID
    public String getAdminId() {
        return adminId;
    }

    // Return department
    public String getDepartment() {
        return department;
    }
}

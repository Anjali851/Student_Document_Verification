
import java.util.ArrayList;
import java.util.List;

// Represents a Student user in the system
public class Student extends User {
    private String rollNumber; // Unique roll number for the student
    private String department;
    private List<Document> submittedDocuments; // List of documents submitted by the student

    // Constructor to initialize student details
    public Student(String userId, String name, String email, String password, 
                  String rollNumber, String department) {
        super(userId, name, email, password); // Call superclass constructor
        this.rollNumber = rollNumber;
        this.department = department;
        this.submittedDocuments = new ArrayList<>();
    }

    // Displays the student dashboard with personal and document details
    @Override
    public void displayDashboard() {
        System.out.println("Student Dashboard");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Department: " + department);
        System.out.println("\nSubmitted Documents:");
        for (Document doc : submittedDocuments) {
            System.out.println("- " + doc.getDocumentName() + " (" + doc.getStatus() + ")");
        }
    }

    // Adds a new document to the student's submitted documents list
    public void submitDocument(Document document) {
        submittedDocuments.add(document);
        System.out.println("Document submitted successfully!");
    }

    // Returns a copy of the list of submitted documents
    public List<Document> getSubmittedDocuments() {
        return new ArrayList<>(submittedDocuments);
    }

    // Getter for roll number
    public String getRollNumber() {
        return rollNumber;
    }

    // Getter for department
    public String getDepartment() {
        return department;
    }
}

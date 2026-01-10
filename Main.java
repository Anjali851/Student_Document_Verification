import java.util.Scanner;
import java.util.List;

// Main class to run the UoH Student Document Verification System (Console Mode)
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static VerificationSystem system = new VerificationSystem();

  public static void main(String[] args) {
    initializeSampleData();

    while (true) {
        System.out.println("\n--- UoH Document Verification System ---");
        System.out.println("1. Student Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Exit");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> studentMenu();
            case 2 -> adminMenu();
            case 3 -> {
                System.out.println("Goodbye!");
                return;
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}


    // ================= STUDENT =================

    private static void studentMenu() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Student student = system.findStudent(studentId);

        if (student == null || !student.verifyPassword(password)) {
            System.out.println("Invalid credentials!");
            return;
        }

        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Dashboard");
            System.out.println("2. Submit Document");
            System.out.println("3. View Submitted Documents");
            System.out.println("4. Logout");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> student.displayDashboard();
                case 2 -> submitDocument(student);
                case 3 -> viewStudentDocuments(student);
                case 4 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void submitDocument(Student student) {
        System.out.print("Document ID: ");
        String id = scanner.nextLine();
        System.out.print("Document Name: ");
        String name = scanner.nextLine();
        System.out.print("Document Type: ");
        String type = scanner.nextLine();
        System.out.print("File Path: ");
        String path = scanner.nextLine();

        Document doc = new Document(id, name, type, path, student.getUserId());
        student.submitDocument(doc);
        system.addDocument(doc);

    }

    private static void viewStudentDocuments(Student student) {
        List<Document> docs = system.getStudentDocuments(student.getUserId());

        if (docs.isEmpty()) {
            System.out.println("No documents submitted.");
            return;
        }

        for (Document d : docs) {
            System.out.println("\nID: " + d.getDocumentId());
            System.out.println("Name: " + d.getDocumentName());
            System.out.println("Type: " + d.getDocumentType());
            System.out.println("Status: " + d.getStatus());
            System.out.println("Comments: " + d.getVerificationComments());
        }
    }

    // ================= ADMIN =================

    private static void adminMenu() {
        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Admin admin = system.findAdmin(adminId);

        if (admin == null || !admin.verifyPassword(password)) {
            System.out.println("Invalid credentials!");
            return;
        }

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View Dashboard");
            System.out.println("2. View Pending Documents");
            System.out.println("3. Verify Document");
            System.out.println("4. View Students");
            System.out.println("5. Logout");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> admin.displayDashboard();
                case 2 -> viewPendingDocuments();
                case 3 -> verifyDocument(admin);
                case 4 -> viewAllStudents();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewPendingDocuments() {
        List<Document> pending = system.getPendingDocuments();

        if (pending.isEmpty()) {
            System.out.println("No pending documents.");
            return;
        }

        for (Document d : pending) {
            System.out.println("\nID: " + d.getDocumentId());
            System.out.println("Name: " + d.getDocumentName());
            System.out.println("Student ID: " + d.getStudentId());
        }
    }

    private static void verifyDocument(Admin admin) {
        System.out.print("Enter Document ID: ");
        String id = scanner.nextLine();

        Document doc = system.findDocument(id);
        if (doc == null) {
            System.out.println("Document not found.");
            return;
        }

        System.out.print("Approve? (Y/N): ");
        boolean approve = scanner.nextLine().equalsIgnoreCase("Y");

        System.out.print("Comments: ");
        String comments = scanner.nextLine();

        admin.verifyDocument(doc, approve, comments);
        System.out.println("Verification completed.");
    }

    // ================= COMMON =================

    private static void viewAllStudents() {
        List<Student> students = system.getStudents();

        for (Student s : students) {
            System.out.println("\nID: " + s.getUserId());
            System.out.println("Name: " + s.getName());
            System.out.println("Roll No: " + s.getRollNumber());
            System.out.println("Dept: " + s.getDepartment());
        }
    }

    private static void initializeSampleData() {
        Student s = new Student(
                "S1", "Anjali", "anjali@uoh.edu",
                "password1", "23MCCE34", "Computer Science"
        );
        system.addStudent(s);

        Admin a = new Admin(
                "A1", "Admin", "admin@uoh.edu",
                "admin123", "ADM001", "Administration"
        );
        system.addAdmin(a);
    }
}
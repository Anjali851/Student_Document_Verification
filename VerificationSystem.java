
import java.util.ArrayList;
import java.util.List;

// Main class that manages students, admins, and document verification
public class VerificationSystem {
    private List<Student> students;     // List of registered students
    private List<Admin> admins;         // List of registered admins
    private List<Document> documents;   // List of all submitted documents

    // Constructor to initialize empty lists
    public VerificationSystem() {
        this.students = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.documents = new ArrayList<>();
    }

    // Adds a new student to the system
    public void addStudent(Student student) {
        students.add(student);
    }

    // Adds a new admin to the system
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    // Adds a new document to the system
    public void addDocument(Document document) {
        documents.add(document);
    }

    // Finds a student by their ID
    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getUserId().equals(studentId)) {
                return student;
            }
        }
        return null; // Return null if student not found
    }

    // Finds an admin by their ID
    public Admin findAdmin(String adminId) {
        for (Admin admin : admins) {
            if (admin.getUserId().equals(adminId)) {
                return admin;
            }
        }
        return null; // Return null if admin not found
    }

    // Finds a document by its ID
    public Document findDocument(String documentId) {
        for (Document document : documents) {
            if (document.getDocumentId().equals(documentId)) {
                return document;
            }
        }
        return null; // Return null if document not found
    }

    // Returns a list of documents that are currently pending verification
    public List<Document> getPendingDocuments() {
        List<Document> pendingDocs = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getStatus().equals("PENDING")) {
                pendingDocs.add(doc);
            }
        }
        return pendingDocs;
    }

    // Returns all documents submitted by a specific student
    public List<Document> getStudentDocuments(String studentId) {
        List<Document> studentDocs = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getStudentId().equals(studentId)) {
                studentDocs.add(doc);
            }
        }
        return studentDocs;
    }

    // Returns a copy of the list of all students
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
}


import java.util.Date;

// Class representing a document submitted by a student
public class Document {
    // Fields to store document details
    private String documentId;
    private String documentName;
    private String documentType;
    private String filePath;
    private Date submissionDate;
    private String status; // PENDING, APPROVED, REJECTED
    private String verificationComments;
    private String studentId;

    // Constructor to initialize a new document with default status and submission date
    public Document(String documentId, String documentName, String documentType, 
                   String filePath, String studentId) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.documentType = documentType;
        this.filePath = filePath;
        this.studentId = studentId;
        this.submissionDate = new Date(); // Set current date as submission date
        this.status = "PENDING"; // Default status is PENDING
        this.verificationComments = ""; // No comments initially
    }

    // Approves the document and adds verification comments
    public void approve(String comments) {
        this.status = "APPROVED";
        this.verificationComments = comments;
    }

    // Rejects the document and adds verification comments
    public void reject(String comments) {
        this.status = "REJECTED";
        this.verificationComments = comments;
    }

    // Getters for accessing private fields

    public String getDocumentId() {
        return documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public String getVerificationComments() {
        return verificationComments;
    }

    public String getStudentId() {
        return studentId;
    }
}

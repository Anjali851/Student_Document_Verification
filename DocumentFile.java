
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DocumentFile {
    private static final String UPLOAD_DIR = "documents/";

    static {
        // Create upload directory if it doesn't exist
        new File(UPLOAD_DIR).mkdirs();
    }

    public static String saveDocument(String studentId, String documentName, String sourceFilePath) throws IOException {
        // Create student directory if it doesn't exist
        String studentDir = UPLOAD_DIR + studentId + "/";
        new File(studentDir).mkdirs();

        // Generate unique filename
        String fileName = documentName + "_" + System.currentTimeMillis() + ".pdf";
        String targetPath = studentDir + fileName;

        // Copy file to upload directory
        Path source = Paths.get(sourceFilePath);
        Path target = Paths.get(targetPath);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        return targetPath;
    }

    public static File getDocument(String filePath) {
        return new File(filePath);
    }

    public static boolean deleteDocument(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static boolean documentExists(String filePath) {
        return new File(filePath).exists();
    }
} 
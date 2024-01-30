import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class DatasetCleaner {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java DatasetCleaner <input_file_path> <output_file_path>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            cleanFile(Paths.get(inputFilePath), Paths.get(outputFilePath));
            System.out.println("Finished cleaning the dataset.");
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static void cleanFile(Path inputFile, Path outputFile) throws IOException {
        String content = new String(Files.readAllBytes(inputFile), StandardCharsets.UTF_8);

        // Replace this cleaning logic with your specific requirements
        content = content.replaceAll("\r", "");
        content = content.replaceAll("[\\s\\t]+", " ");
        content = content.replaceAll("[^a-zA-Z0-9 ]", "");

        Files.createDirectories(outputFile.getParent());
        Files.write(outputFile, content.getBytes(StandardCharsets.UTF_8));
    }
}

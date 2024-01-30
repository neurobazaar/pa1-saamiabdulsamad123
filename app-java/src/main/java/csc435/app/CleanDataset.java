import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Stream;

public class DatasetCleaner {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java DatasetCleaner <input_directory> <output_directory>");
            return;
        }

        String inputDirectory = args[0];
        String outputDirectory = args[1];

        try {
            cleanDatasets(inputDirectory, outputDirectory);
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static void cleanDatasets(String inputDirectory, String outputDirectory) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(inputDirectory))) {
            paths.filter(Files::isRegularFile)
                 .forEach(file -> {
                     try {
                         cleanFile(file, outputDirectory);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleanFile(Path inputFile, String outputDirectory) throws IOException {
        String content = new String(Files.readAllBytes(inputFile), StandardCharsets.UTF_8);

        // Replace this cleaning logic with your specific requirements
        content = content.replaceAll("\r", "");
        content = content.replaceAll("[\\s\\t]+", " ");
        content = content.replaceAll("[^a-zA-Z0-9 ]", "");

        Path outputFile = Paths.get(outputDirectory, inputFile.getParent().getFileName().toString(), inputFile.getFileName().toString());
        Files.createDirectories(outputFile.getParent());
        Files.write(outputFile, content.getBytes(StandardCharsets.UTF_8));
    }
}
        
        System.out.print("Finished cleaning " + cleanDataset.dataset_size + " MiB of data");
        System.out.println(" in " + cleanDataset.execution_time + " miliseconds");
    }
}

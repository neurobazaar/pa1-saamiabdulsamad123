import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UniqueWordCounter {
    public static void main(String[] args) throws IOException {
        Path sourceDir = Paths.get(args[0]);
        Path resultDir = Paths.get(args[1]);

        Files.walk(sourceDir)
            .filter(Files::isRegularFile)
            .forEach(sourceFile -> {
                try {
                    Map<String, Integer> uniqueWordCounts = new HashMap<>();
                    Files.readAllLines(sourceFile)
                        .stream()
                        .flatMap(line -> Arrays.stream(line.split("\\W+")))
                        .forEach(word -> uniqueWordCounts.put(word, uniqueWordCounts.getOrDefault(word, 0) + 1));

                    Path resultFile = resultDir.resolve(sourceDir.relativize(sourceFile));
                    Files.createDirectories(resultFile.getParent());
                    try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(resultFile))) {
                        uniqueWordCounts.forEach((word, count) -> writer.println(word + " " + count));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}

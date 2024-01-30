import java.io.*;
import java.util.*;

public class WordSorter {
    private long numberOfWords = 0;
    private double executionTimeInSeconds = 0.0;

    public void sortWords(String inputDirectory, String outputDirectory) {
        try {
            long startTime = System.currentTimeMillis();

            // Read words from the input file
            BufferedReader reader = new BufferedReader(new FileReader(inputDirectory));
            List<String> words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into words by whitespace
                String[] splitWords = line.split("\\s+");
                // Add each word to the list
                words.addAll(Arrays.asList(splitWords));
            }
            reader.close();

            // Sort the words
            Collections.sort(words);

            // Write sorted words to the output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory));
            for (String word : words) {
                writer.write(word);
                writer.newLine();
            }
            writer.close();

            // Calculate execution time
            long endTime = System.currentTimeMillis();
            executionTimeInSeconds = (endTime - startTime) / 1000.0;

            // Set the number of words
            numberOfWords = words.size();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java WordSorter <inputDirectory> <outputDirectory>");
            System.exit(1);
        }

        WordSorter wordSorter = new WordSorter();

        wordSorter.sortWords(args[0], args[1]);

        System.out.print("Finished sorting " + wordSorter.numberOfWords + " words");
        System.out.println(" in " + wordSorter.executionTimeInSeconds + " seconds");
    }
}

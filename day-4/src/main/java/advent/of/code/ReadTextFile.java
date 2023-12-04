package advent.of.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTextFile {

    public static void main(String[] args) {
        String resourcePath = "aoc4-input.txt";
        ClassLoader classLoader = ReadTextFile.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream != null) {
                String line;
                List<ScratchCard> scratchCards = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    scratchCards.add(ScratchCard.extractScratchCard(line));
                }
                // Take a seat in the large pile of colorful cards. How many points are they worth in total?
                System.out.println("There are %s points in total".formatted(scratchCards.stream().map(ScratchCard::getPoints).reduce(0, Integer::sum)));
            } else {
                System.out.println("Resource not found: " + resourcePath);
            }
        } catch (IOException e) {
            // Handle any potential exceptions that may occur during file reading
            e.printStackTrace();
        }
    }
}

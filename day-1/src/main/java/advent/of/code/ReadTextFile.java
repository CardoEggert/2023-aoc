package advent.of.code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadTextFile {

    public static void main(String[] args) {
        String resourcePath = "aoc1-input.txt";
        ClassLoader classLoader = ReadTextFile.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream != null) {
                String line;
                final List<Integer> calibrationValues = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    calibrationValues.add(
                            NumberExtractor.extractFirstAndLastNumberCombination(
                                    StringConverter.extractNumberedString(line)));
                }
                System.out.println(
                        "Sum of all calibration values is %s"
                                .formatted(calibrationValues.stream().reduce(0, Integer::sum)));
            } else {
                System.out.println("Resource not found: " + resourcePath);
            }
        } catch (IOException e) {
            // Handle any potential exceptions that may occur during file reading
            e.printStackTrace();
        }
    }
}

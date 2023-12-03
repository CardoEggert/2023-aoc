package advent.of.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadTextFile {

    public static void main(String[] args) {
        String resourcePath = "aoc3-input.txt";
        ClassLoader classLoader = ReadTextFile.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream != null) {
                String line;
                EngineSchematic schematic = new EngineSchematic(new ArrayList<>());
                while ((line = reader.readLine()) != null) {
                    schematic.lines().add(line);
                }
                // What is the sum of all of the part numbers in the engine schematic?
                System.out.println(
                        "Sum of all the part numbers in the engine schematic are %s"
                                .formatted(schematic
                                        .extractPartNumbers()
                                        .stream()
                                        .map(PartNumber::number)
                                        .map(EngineSchematicNumber::number)
                                        .reduce(0, Integer::sum)));
            } else {
                System.out.println("Resource not found: " + resourcePath);
            }
        } catch (IOException e) {
            // Handle any potential exceptions that may occur during file reading
            e.printStackTrace();
        }
    }
}

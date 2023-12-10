package advent.of.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadTextFile {

    public static void main(String[] args) {
        String resourcePath = "aoc6-input.txt";
        ClassLoader classLoader = ReadTextFile.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream != null) {
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                final RaceDetail actualRace = RaceDetail.read(sb.toString());
                final RaceBoat boat = new RaceBoat(1);
                System.out.println("How many ways to beat the record in one much longer race? Answer: %s".formatted(boat.countNumberOfWaysToBeatRace(actualRace)));
            } else {
                System.out.println("Resource not found: " + resourcePath);
            }
        } catch (IOException e) {
            // Handle any potential exceptions that may occur during file reading
            e.printStackTrace();
        }
    }
}

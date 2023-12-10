package advent.of.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
                /*
                Determine the number of ways you could beat the record in each race. What do you get if you multiply these numbers together?
                 */
                final RaceDetails raceDetails = RaceDetails.read(sb.toString());
                final RaceBoat boat = new RaceBoat(1);
                final List<Integer> combinationsCount = raceDetails.numberOfWaysToBeatRace(boat);
                System.out.println("Combinations are here for winning those races: %s".formatted(combinationsCount));
                System.out.println("Multiplying them together gives: %s".formatted(combinationsCount.stream().reduce(1, (a, b) -> a * b)));
            } else {
                System.out.println("Resource not found: " + resourcePath);
            }
        } catch (IOException e) {
            // Handle any potential exceptions that may occur during file reading
            e.printStackTrace();
        }
    }
}

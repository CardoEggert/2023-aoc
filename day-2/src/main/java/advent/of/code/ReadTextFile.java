package advent.of.code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadTextFile {

    public static void main(String[] args) {
        String resourcePath = "aoc2-input.txt";
        ClassLoader classLoader = ReadTextFile.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream != null) {
                String line;
                final List<Game> gameStats = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    gameStats.add(GameExtractor.extractGameWithMaxAmountOfBalls(line));
                }
                // Calculate the sum of game IDs from these values : 12 red cubes, 13 green cubes, and 14 blue cubes
                System.out.println(
                        "Sum of game IDs that would be playable with %s red cubes, %s green cubes, and %s blue cubes is %s".formatted(
                                12,
                                13,
                                14,
                                gameStats
                                        .stream()
                                        .filter(game -> game.isPlayable(12, 13, 14))
                                        .map(Game::id)
                                        .reduce(0, Integer::sum)
                        ));
                // For each game, find the minimum set of cubes that must have been present. What is the sum of the power of these sets?
                System.out.println("Sum of the power of these sets is %s".formatted(
                        gameStats
                                .stream()
                                .map(Game::power)
                                .reduce(0, Integer::sum)
                ));
            } else {
                System.out.println("Resource not found: " + resourcePath);
            }
        } catch (IOException e) {
            // Handle any potential exceptions that may occur during file reading
            e.printStackTrace();
        }
    }
}

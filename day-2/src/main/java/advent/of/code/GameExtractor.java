package advent.of.code;

public final class GameExtractor {

    private GameExtractor() {
    }

    /**
     * Extract game information from input
     *
     * @param input - game with how many balls (maxRed/maxGreen/maxBlue)
     * @return - game record with id and how many balls were shown as maximum
     */
    public static Game extractGameWithMaxAmountOfBalls(String input) {
        // Example "Game 11: 14 green; 2 green, 2 red, 11 blue; 9 blue, 3 green"
        final String[] splitGame = input.split(":");
        final int gameId = Integer.parseInt(splitGame[0].replaceAll("\\D", ""));
        final String extractedGameInfo = splitGame[1];
        int maxRedCount = 0;
        int maxGreenCount = 0;
        int maxBlueCount = 0;
        for (String gameSet : extractedGameInfo.split(";")) {
            maxRedCount = Math.max(maxRedCount, countColor(gameSet, "red"));
            maxGreenCount = Math.max(maxGreenCount, countColor(gameSet, "green"));
            maxBlueCount = Math.max(maxBlueCount, countColor(gameSet, "blue"));
        }
        return new Game(gameId, maxRedCount, maxGreenCount, maxBlueCount);
    }

    private static int countColor(String gameSet, String color) {
        if (gameSet.contains(",")) {
            for (String gameColor : gameSet.split(",")) {
                if (gameColor.contains(color)) {
                    return Integer.parseInt(gameColor.replaceAll("\\D", ""));
                }
            }
        }
        return gameSet.contains(color) ? Integer.parseInt(gameSet.replaceAll("\\D", "")) : 0;
    }
}

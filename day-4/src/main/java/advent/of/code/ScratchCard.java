package advent.of.code;

import java.util.Arrays;
import java.util.List;

public record ScratchCard(int id, List<Integer> winningNumbers, List<Integer> numbers) {

    public static ScratchCard extractScratchCard(String line) {
        // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        final String[] cardIdAndNumbersSplit = line.split(":");
        final Integer cardId = Integer.parseInt(cardIdAndNumbersSplit[0].replaceAll("\\D", ""));
        final String[] winningNumbersAndNumbers = cardIdAndNumbersSplit[1].split("\\|");
        final List<Integer> winningNumbers = extractNumbers(winningNumbersAndNumbers[0]);
        final List<Integer> numbers = extractNumbers(winningNumbersAndNumbers[1]);
        return new ScratchCard(cardId, winningNumbers, numbers);
    }

    public Integer getPoints() {
        final List<Integer> matchingWinningNumbers = numbers.stream().filter(winningNumbers::contains).toList();
        if (matchingWinningNumbers.isEmpty()) {
            return 0;
        }
        return calculatePoints(matchingWinningNumbers.size());
    }

    private Integer calculatePoints(Integer countOfMatchingNumbers) {
        if (countOfMatchingNumbers <= 1) {
            return 1;
        }
        return 2 * calculatePoints(countOfMatchingNumbers - 1);
    }

    private static List<Integer> extractNumbers(String numberString) {
        return Arrays.stream(numberString.trim().split(" ")).filter(x -> !x.isBlank()).map(Integer::parseInt).toList();
    }
}

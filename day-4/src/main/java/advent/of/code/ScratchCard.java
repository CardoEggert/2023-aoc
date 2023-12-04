package advent.of.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record ScratchCard(int id, List<Integer> winningNumbers, List<Integer> numbers) {

    public static ScratchCard extractScratchCard(String line) {
        final String[] cardIdAndNumbersSplit = line.split(":");
        final Integer cardId = Integer.parseInt(cardIdAndNumbersSplit[0].replaceAll("\\D", ""));
        final String[] winningNumbersAndNumbers = cardIdAndNumbersSplit[1].split("\\|");
        final List<Integer> winningNumbers = extractNumbers(winningNumbersAndNumbers[0]);
        final List<Integer> numbers = extractNumbers(winningNumbersAndNumbers[1]);
        return new ScratchCard(cardId, winningNumbers, numbers);
    }

    public List<ScratchCard> getWonCopies(List<ScratchCard> remainingCards) {
        if (remainingCards.isEmpty()) {
            return List.of();
        }
        final List<ScratchCard> wonCopies = new ArrayList<>();
        final Integer matchedNumbersCount = getMatchingNumbersCount();
        for (int countIter = 0; countIter < matchedNumbersCount; countIter++) {
            wonCopies.add(remainingCards.get(countIter % remainingCards.size()));
        }
        return wonCopies;
    }

    public Integer getPoints() {
        final List<Integer> matchingWinningNumbers = numbers.stream().filter(winningNumbers::contains).toList();
        if (matchingWinningNumbers.isEmpty()) {
            return 0;
        }
        return calculatePoints(matchingWinningNumbers.size());
    }

    public Integer getMatchingNumbersCount() {
        return (int) numbers.stream().filter(winningNumbers::contains).count();
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

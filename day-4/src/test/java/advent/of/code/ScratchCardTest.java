package advent.of.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ScratchCardTest {

    private static final ScratchCard CARD_1 = new ScratchCard(1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53));
    private static final ScratchCard CARD_2 = new ScratchCard(2, List.of(13, 32, 20, 16, 61), List.of(61, 30, 68, 82, 17, 32, 24, 19));
    private static final ScratchCard CARD_3 = new ScratchCard(3, List.of(1, 21, 53, 59, 44), List.of(69, 82, 63, 72, 16, 21, 14, 1));
    private static final ScratchCard CARD_4 = new ScratchCard(4, List.of(41, 92, 73, 84, 69), List.of(59, 84, 76, 51, 58, 5, 54, 83));
    private static final ScratchCard CARD_5 = new ScratchCard(5, List.of(87, 83, 26, 28, 32), List.of(88, 30, 70, 12, 93, 22, 82, 36));
    private static final ScratchCard CARD_6 = new ScratchCard(6, List.of(31, 18, 13, 56, 72), List.of(74, 77, 10, 23, 35, 67, 36, 11));

    private static Stream<? extends Arguments> scratchCards() {
        return Stream.of(
                Arguments.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53", 1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53), 4, 8),
                Arguments.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19", 2, List.of(13, 32, 20, 16, 61), List.of(61, 30, 68, 82, 17, 32, 24, 19), 2, 2),
                Arguments.of("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1", 3, List.of(1, 21, 53, 59, 44), List.of(69, 82, 63, 72, 16, 21, 14, 1), 2, 2),
                Arguments.of("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83", 4, List.of(41, 92, 73, 84, 69), List.of(59, 84, 76, 51, 58, 5, 54, 83), 1, 1),
                Arguments.of("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36", 5, List.of(87, 83, 26, 28, 32), List.of(88, 30, 70, 12, 93, 22, 82, 36), 0, 0),
                Arguments.of("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11", 6, List.of(31, 18, 13, 56, 72), List.of(74, 77, 10, 23, 35, 67, 36, 11), 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "scratchCards")
    void extractNumbers(String line, int id, List<Integer> winningNumbers, List<Integer> numbers, int matchingNumbers, int expectedPoints) {
        final var scratchCard = ScratchCard.extractScratchCard(line);
        assertThat(scratchCard.id()).isEqualTo(id);
        assertThat(scratchCard.winningNumbers()).containsExactlyElementsOf(winningNumbers);
        assertThat(scratchCard.numbers()).containsExactlyElementsOf(numbers);
        assertThat(scratchCard.getMatchingNumbersCount()).isEqualTo(matchingNumbers);
        assertThat(scratchCard.getPoints()).isEqualTo(expectedPoints);
    }

    private static Stream<? extends Arguments> copyWins() {
        return Stream.of(
                Arguments.of(CARD_6, List.of(), List.of()),
                Arguments.of(CARD_5, List.of(CARD_6), List.of()),
                Arguments.of(CARD_4, List.of(CARD_5, CARD_6), List.of(CARD_5)),
                Arguments.of(CARD_3, List.of(CARD_4, CARD_5, CARD_6), List.of(CARD_4, CARD_5)),
                Arguments.of(CARD_2, List.of(CARD_3, CARD_4, CARD_5, CARD_6), List.of(CARD_3, CARD_4)),
                Arguments.of(CARD_1, List.of(CARD_2, CARD_3, CARD_4, CARD_5, CARD_6), List.of(CARD_2, CARD_3, CARD_4, CARD_5)),
                Arguments.of(CARD_1, List.of(CARD_6), List.of(CARD_6, CARD_6, CARD_6, CARD_6)),
                Arguments.of(CARD_1, List.of(CARD_5, CARD_6), List.of(CARD_5, CARD_6, CARD_5, CARD_6))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "copyWins")
    void extractNumbers(ScratchCard card, List<ScratchCard> remainingCards, List<ScratchCard> wonCopies) {
        assertThat(card.getWonCopies(remainingCards)).containsExactlyElementsOf(wonCopies);
    }


}

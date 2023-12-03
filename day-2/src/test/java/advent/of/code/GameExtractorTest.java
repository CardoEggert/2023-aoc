package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

class GameExtractorTest {

    @org.junit.jupiter.api.Test
    @NullAndEmptySource
    void extractGameWithMaxAmountOfBalls_nullOrEmpty(String input) {
        Assertions.assertThat(GameExtractor.extractGameWithMaxAmountOfBalls(input)).isEqualTo(null);
    }

    private static Stream<? extends Arguments> stringsToExpectedGames() {
        return Stream.of(
                Arguments.of("Game 11: 14 green; 2 green, 2 red, 11 blue; 9 blue, 3 green", 11, 2, 14, 11),
                Arguments.of("Game 12: 2 green, 2 red, 11 blue; 9 blue, 3 green; 16 blue", 12, 2, 3, 16),
                Arguments.of("Game 13: 2 green, 2 red, 11 blue; 19 red; 9 blue, 3 green", 13, 19, 3, 11),
                Arguments.of("Game 28: 3 blue, 2 green, 10 red; 5 blue, 2 green; 4 green, 3 blue, 11 red", 28, 11, 4, 5)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "stringsToExpectedGames")
    void gameExtraction(String input, int gameId, int maxRed, int maxGreen, int maxBlue) {
        final Game extractedGame = GameExtractor.extractGameWithMaxAmountOfBalls(input);
        Assertions.assertThat(extractedGame.id()).isEqualTo(gameId);
        Assertions.assertThat(extractedGame.maxRed()).isEqualTo(maxRed);
        Assertions.assertThat(extractedGame.maxGreen()).isEqualTo(maxGreen);
        Assertions.assertThat(extractedGame.maxBlue()).isEqualTo(maxBlue);
    }

}
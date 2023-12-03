package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class GameTest {

    private static Stream<? extends Arguments> playableGames() {
        return Stream.of(
                Arguments.of(new Game(0, 0, 0, 0), 0, 0, 0, true),
                Arguments.of(new Game(0, 0, 0, 0), 1, 1, 1, true),
                Arguments.of(new Game(0, 2, 2, 2), 1, 1, 1, false),
                Arguments.of(new Game(0, 3, 2, 2), 2, 2, 2, false),
                Arguments.of(new Game(0, 2, 3, 2), 2, 2, 2, false),
                Arguments.of(new Game(0, 2, 2, 3), 2, 2, 2, false),
                Arguments.of(new Game(0, 2, 2, 3), 3, 3, 3, true),
                Arguments.of(new Game(0, 2, 3, 2), 3, 3, 3, true),
                Arguments.of(new Game(0, 3, 2, 2), 3, 3, 3, true),
                Arguments.of(new Game(0, 3, 3, 3), 3, 3, 3, true)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "playableGames")
    void playableTests(Game game, int countOfRed, int countOfGreen, int countOfBlue, boolean playable) {
        Assertions.assertThat(game.isPlayable(countOfRed, countOfGreen, countOfBlue)).isEqualTo(playable);
    }
}

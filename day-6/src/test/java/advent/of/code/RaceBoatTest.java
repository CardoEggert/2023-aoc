package advent.of.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RaceBoatTest {

    private static RaceBoat RACE_BOAT = new RaceBoat(1);

    private static Stream<? extends Arguments> holdButtonTests() {
        return Stream.of(
                Arguments.of(0, 7, 0),
                Arguments.of(1, 7, 6),
                Arguments.of(2, 7, 10),
                Arguments.of(3, 7, 12),
                Arguments.of(4, 7, 12),
                Arguments.of(5, 7, 10),
                Arguments.of(6, 7, 6),
                Arguments.of(7, 7, 0)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "holdButtonTests")
    void buttonHoldingTests(int holdForMilliseconds, int raceDuration, int expectedDistance) {
        assertThat(RACE_BOAT.getTravelledDistance(holdForMilliseconds, raceDuration)).isEqualTo(expectedDistance);
    }

    private static Stream<? extends Arguments> numberOfWaysToBeat() {
        return Stream.of(
                Arguments.of(new RaceDetail(7, 9), 4),
                Arguments.of(new RaceDetail(15, 40), 8),
                Arguments.of(new RaceDetail(30, 200), 9)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "numberOfWaysToBeat")
    void numberOfWaysToBeat(RaceDetail raceDetail, int numberOfWaysToBeatIt) {
        assertThat(RACE_BOAT.countNumberOfWaysToBeatRace(raceDetail)).isEqualTo(numberOfWaysToBeatIt);
    }

}
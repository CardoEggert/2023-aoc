package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RaceDetailsTest {

    private static final String RACE_DETAILS_STRING = """
            Time:      7  15   30
            Distance:  9  40  200
            """;
    @Test
    void readTest() {
        final RaceDetails raceDetails = RaceDetails.read(RACE_DETAILS_STRING);
        Assertions.assertThat(raceDetails).isNotNull();
        Assertions.assertThat(raceDetails.raceDetails()).containsExactly(
                new RaceDetail(7, 9),
                new RaceDetail(15, 40),
                new RaceDetail(30, 200));
    }
}
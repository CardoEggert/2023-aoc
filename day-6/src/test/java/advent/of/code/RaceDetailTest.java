package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RaceDetailTest {

    private static final String RACE_DETAIL_STRING = """
            Time:      71530
            Distance:  940200
            """;
    @Test
    void readTest() {
        final RaceDetail raceDetails = RaceDetail.read(RACE_DETAIL_STRING);
        Assertions.assertThat(raceDetails).isNotNull();
        Assertions.assertThat(raceDetails.raceTime()).isEqualTo(71530L);
        Assertions.assertThat(raceDetails.distanceRecord()).isEqualTo(940200L);
    }
}
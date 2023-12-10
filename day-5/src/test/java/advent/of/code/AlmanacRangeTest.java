package advent.of.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AlmanacRangeTest {

    private static Stream<? extends Arguments> ranges() {
        return Stream.of(
                Arguments.of("49 53 8", new AlmanacRange(49, 53, 8)),
                Arguments.of("0 11 42", new AlmanacRange(0, 11, 42)),
                Arguments.of("42 0 7", new AlmanacRange(42, 0, 7)),
                Arguments.of("57 7 4", new AlmanacRange(57, 7, 4))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "ranges")
    void readRanges(String line, AlmanacRange expectedRange) {
        assertThat(AlmanacRange.readRange(line)).isEqualTo(expectedRange);
    }

    private static Stream<? extends Arguments> withinSourceRange() {
        return Stream.of(
                Arguments.of(new AlmanacRange(49, 53, 8), 52, false),
                Arguments.of(new AlmanacRange(49, 53, 8), 53, true),
                Arguments.of(new AlmanacRange(49, 53, 8), 61, true),
                Arguments.of(new AlmanacRange(49, 53, 8), 62, false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "withinSourceRange")
    void withinSourceRangeTest(AlmanacRange range, long value, boolean expectedInRange) {
        assertThat(range.isWithinRange(value)).isEqualTo(expectedInRange);
    }

    private static Stream<? extends Arguments> destinationValues() {
        return Stream.of(
                Arguments.of(new AlmanacRange(49, 53, 8), 52, 52),
                Arguments.of(new AlmanacRange(49, 53, 8), 53, 49),
                Arguments.of(new AlmanacRange(49, 53, 8), 54, 50),
                Arguments.of(new AlmanacRange(49, 53, 8), 55, 51),
                Arguments.of(new AlmanacRange(49, 53, 8), 56, 52),
                Arguments.of(new AlmanacRange(49, 53, 8), 57, 53),
                Arguments.of(new AlmanacRange(49, 53, 8), 58, 54),
                Arguments.of(new AlmanacRange(49, 53, 8), 59, 55),
                Arguments.of(new AlmanacRange(49, 53, 8), 60, 56),
                Arguments.of(new AlmanacRange(49, 53, 8), 61, 57),
                Arguments.of(new AlmanacRange(49, 53, 8), 62, 62)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "destinationValues")
    void destinationValueTest(AlmanacRange range, long sourceValue, long expectedDestinationValue) {
        assertThat(range.getDestinationValue(sourceValue)).isEqualTo(expectedDestinationValue);
    }



}
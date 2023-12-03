package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class NumberExtractorTest {

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptyReturnsZero(String input) {
        Assertions.assertThat(NumberExtractor.extractFirstAndLastNumberCombination(input)).isZero();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "00", "0a0", "a0b0c", "ab0cd", "abc"})
    void zeroesReturnsZero(String input) {
        Assertions.assertThat(NumberExtractor.extractFirstAndLastNumberCombination(input)).isZero();
    }

    private static Stream<? extends Arguments> stringsAndExpectedInts() {
        return Stream.of(
                Arguments.of("ab1ce", 11),
                Arguments.of("ab11ce", 11),
                Arguments.of("ab121ce", 11),
                Arguments.of("ab112ce", 12),
                Arguments.of("3s1awr2", 32),
                Arguments.of("12", 12),
                Arguments.of("o2051s2", 22)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "stringsAndExpectedInts")
    void numberExtractionTests(String input, int expectedInt) {
        Assertions.assertThat(NumberExtractor.extractFirstAndLastNumberCombination(input)).isEqualTo(expectedInt);
    }
}
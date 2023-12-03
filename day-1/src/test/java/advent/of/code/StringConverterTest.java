package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class StringConverterTest {

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptyReturnsInput(String input) {
        Assertions.assertThat(StringConverter.extractNumberedString(input)).isEqualTo(input);
    }

    private static Stream<? extends Arguments> stringsAndExpectedOutputs() {
        return Stream.of(
                Arguments.of("zero", ""),
                Arguments.of("one", "1"),
                Arguments.of("two", "2"),
                Arguments.of("three", "3"),
                Arguments.of("four", "4"),
                Arguments.of("five", "5"),
                Arguments.of("six", "6"),
                Arguments.of("seven", "7"),
                Arguments.of("eight", "8"),
                Arguments.of("nine", "9"),
                Arguments.of("ab1ce", "1"),
                Arguments.of("4nineeightseven2", "49872"),
                Arguments.of("xtwone3four", "2134"),
                Arguments.of("two1nine", "219"),
                Arguments.of("eightwothree", "823"),
                Arguments.of("abcone2threexyz", "123"),
                Arguments.of("4nineeightseven2", "49872"),
                Arguments.of("zoneight234", "18234"),
                Arguments.of("7pqrstsixteen", "76"),
                Arguments.of("nctwonefourjzgskmxjmq2", "2142"),
                Arguments.of("4btrhpznzr8oneightg", "4818"),
                Arguments.of("5oneone1", "5111"),
                Arguments.of("27eighteightsevensix6six", "27887666"),
                Arguments.of("oneight", "18"),
                Arguments.of("twone", "21"),
                Arguments.of("ab1onece", "11")
        );
    }

    @ParameterizedTest
    @MethodSource(value = "stringsAndExpectedOutputs")
    void numberExtractionTests(String input, String expectedOutput) {
        Assertions.assertThat(StringConverter.extractNumberedString(input)).isEqualTo(expectedOutput);
    }
}
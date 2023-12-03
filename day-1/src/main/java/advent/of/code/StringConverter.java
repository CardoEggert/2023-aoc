package advent.of.code;

import java.util.Map;
import java.util.Set;

public final class StringConverter {

    private StringConverter() {
    }

    /*
    Your calculation isn't quite right.
    It looks like some of the digits are actually spelled out with letters:
    one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
     */
    private static final Map<String, String> numberByText = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    private static final Set<Character> digits = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    /**
     * Convert the string that it extracts numbers out of it
     * @param input - input line of text
     * @return - converted text with only numbers
     */
    public static String extractNumberedString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        final StringBuilder numberedString = new StringBuilder();
        for (int inputIndex = 0; inputIndex < input.length(); inputIndex++) {
            if (digits.contains(input.charAt(inputIndex))) {
                numberedString.append(input.charAt(inputIndex));
            } else {
                String lastNumberOfString = findNumbersInLastPartOfString(input.substring(0, inputIndex + 1));
                if (lastNumberOfString != null) {
                    numberedString.append(lastNumberOfString);
                }
            }
        }
        return numberedString.toString();
    }

    private static String findNumbersInLastPartOfString(String input) {
        for (Map.Entry<String, String> entry : numberByText.entrySet()) {
            final String numberAsText = entry.getKey();
            final String number = entry.getValue();
            if (input.endsWith(numberAsText)) {
                return number;
            }
        }
        return null;
    }
}

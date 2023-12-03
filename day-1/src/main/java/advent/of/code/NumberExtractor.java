package advent.of.code;

public final class NumberExtractor {

    private NumberExtractor() {}

    public static int extractFirstAndLastNumberCombination(String lineOfText) {
        if (lineOfText == null || lineOfText.isEmpty()) {
            return 0;
        }
        final String textWithOnlyDigits = lineOfText.replaceAll("\\D", "");
        if (textWithOnlyDigits.isEmpty()) {
            return 0;
        }
        final String firstFoundNumber = textWithOnlyDigits.substring(0, 1);
        final String lastFoundNumber = textWithOnlyDigits.substring(textWithOnlyDigits.length() - 1);
        return Integer.parseInt(firstFoundNumber) * 10 + Integer.parseInt(lastFoundNumber);
    }
}

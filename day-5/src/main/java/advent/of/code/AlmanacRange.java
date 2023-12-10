package advent.of.code;

public record AlmanacRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {

    public static AlmanacRange readRange(String row) {
        final String[] numbers = row.split(" ");
        return new AlmanacRange(
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[2]));
    }

    /**
     * Any source numbers that aren't mapped correspond to the same destination number. So, seed number 10 corresponds to soil number 10.
     */
    public boolean isWithinRange(long sourceValue) {
        return sourceRangeStart <= sourceValue && sourceValue <= (sourceRangeStart + rangeLength);
    }

    public Long getDestinationValue(Long sourceValue) {
        if (!isWithinRange(sourceValue)) {
            return sourceValue;
        }
        return destinationRangeStart + Math.abs(sourceRangeStart - sourceValue);
    }
}

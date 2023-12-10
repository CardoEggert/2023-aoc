package advent.of.code;

public record SeedValue(long start, long range) {

    public Long getStartingValue(long currentSeedId) {
        if (currentSeedId >= start) {
            return currentSeedId;
        }
        return start;
    }

    public long getEndingValue() {
        return start + range;
    }

}

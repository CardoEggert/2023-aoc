package advent.of.code;

import java.util.*;

public record Almanac(List<Long> seeds, Map<Category, AlmanacDestinationRange> map) {

    public static Almanac read(String almanacText) {
        final String[] splitByLines = almanacText.split("\n");
        final List<Long> seeds = Arrays.stream(splitByLines[0].split("seeds: ")[1].split(" ")).map(Long::parseLong).toList();
        final Map<Category, AlmanacDestinationRange> categoryMap = new HashMap<>();
        Category source = null;
        Category destination = null;
        List<AlmanacRange> ranges = new ArrayList<>();
        for (int i = 1; i < splitByLines.length; i++) {
            final String line = splitByLines[i];
            if (line.contains("map")) {
                final String[] mapInfo = line.split(" ")[0].split("-to-");
                source = Category.valueOf(mapInfo[0].toUpperCase());
                destination = Category.valueOf(mapInfo[1].toUpperCase());
                ranges = new ArrayList<>();
            } else if (source != null && !line.isBlank()) {
                ranges.add(AlmanacRange.readRange(line));
            } else if (source != null && line.isBlank()) {
                categoryMap.putIfAbsent(source, new AlmanacDestinationRange(destination, ranges));
            }
        }
        // Add last map as well
        categoryMap.putIfAbsent(source, new AlmanacDestinationRange(destination, ranges));
        return new Almanac(seeds, categoryMap);
    }

    public Long findLowest(Category endDestination) {
        Long lowestDestinationValue = Long.MAX_VALUE;
        for (Long seed : seeds) {
            final Long destinationValue = findDestinationValue(Category.SEED, endDestination, seed);
            if (destinationValue < lowestDestinationValue) {
                lowestDestinationValue = destinationValue;
            }
        }
        return lowestDestinationValue;
    }

    private Long findDestinationValue(Category source, Category endDestination, Long value) {
        if (source == endDestination) {
            return value;
        }
        final AlmanacDestinationRange destinationRange = map.get(source);
        for (AlmanacRange range : destinationRange.ranges()) {
            if (range.isWithinRange(value)) {
                return findDestinationValue(destinationRange.destination(), endDestination, range.getDestinationValue(value));
            }
        }
        return findDestinationValue(destinationRange.destination(), endDestination, value);
    }

}

package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class AlmanacTest {

    private static final String ALMANAC = """
            seeds: 79 14 55 13
                        
            seed-to-soil map:
            50 98 2
            52 50 48
                        
            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15
                        
            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4
                        
            water-to-light map:
            88 18 7
            18 25 70
                        
            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13
                        
            temperature-to-humidity map:
            0 69 1
            1 0 69
                        
            humidity-to-location map:
            60 56 37
            56 93 4
            
            """;

    @Test
    void alamanacRead() {
        final Almanac almanac = Almanac.read(ALMANAC);

        Assertions.assertThat(almanac).isNotNull();
        Assertions.assertThat(almanac.seedValues()).isEqualTo(List.of(new SeedValue(79L, 14L), new SeedValue(55L, 13L)));
        assertMapContains(
                almanac.map(),
                Category.SEED,
                Category.SOIL,
                List.of(
                        new AlmanacRange(50, 98, 2),
                        new AlmanacRange(52, 50, 48)));
        assertMapContains(
                almanac.map(),
                Category.SOIL,
                Category.FERTILIZER,
                List.of(
                        new AlmanacRange(0, 15, 37),
                        new AlmanacRange(37, 52, 2),
                        new AlmanacRange(39, 0, 15)));
        assertMapContains(
                almanac.map(),
                Category.FERTILIZER,
                Category.WATER,
                List.of(
                        new AlmanacRange(49, 53, 8),
                        new AlmanacRange(0, 11, 42),
                        new AlmanacRange(42, 0, 7),
                        new AlmanacRange(57, 7, 4)));
        assertMapContains(
                almanac.map(),
                Category.WATER,
                Category.LIGHT,
                List.of(
                        new AlmanacRange(88, 18, 7),
                        new AlmanacRange(18, 25, 70)));
        assertMapContains(
                almanac.map(),
                Category.LIGHT,
                Category.TEMPERATURE,
                List.of(
                        new AlmanacRange(45, 77, 23),
                        new AlmanacRange(81, 45, 19),
                        new AlmanacRange(68, 64, 13)));
        assertMapContains(
                almanac.map(),
                Category.TEMPERATURE,
                Category.HUMIDITY,
                List.of(
                        new AlmanacRange(0, 69, 1),
                        new AlmanacRange(1, 0, 69)));
        assertMapContains(
                almanac.map(),
                Category.HUMIDITY,
                Category.LOCATION,
                List.of(
                        new AlmanacRange(60, 56, 37),
                        new AlmanacRange(56, 93, 4)));
    }

    private void assertMapContains(Map<Category, AlmanacDestinationRange> map, Category source, Category destination, List<AlmanacRange> ranges) {
        Assertions.assertThat(map).containsKey(source);
        Assertions.assertThat(map.get(source).destination()).isEqualTo(destination);
        Assertions.assertThat(map.get(source).ranges()).isEqualTo(ranges);
    }

    @Test
    void lowestLocationNumber() {
        final Almanac almanac = Almanac.read(ALMANAC);
        Assertions.assertThat(almanac.findLowest(Category.LOCATION)).isEqualTo(46L);
    }
}
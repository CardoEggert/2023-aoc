package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EngineSchematicTest {

    @Test
    void engineSchematic() {
        final List<String> lines = new ArrayList<>();
        lines.add("467..114..");
        lines.add("...*......");
        lines.add("..35..633.");
        lines.add("......#...");
        lines.add("617*......");
        lines.add(".....+.58.");
        lines.add("..592.....");
        lines.add("......755.");
        lines.add("...$.*....");
        lines.add(".664.598..");

        EngineSchematic engineSchematic = new EngineSchematic(lines);

        Assertions.assertThat(engineSchematic.extractPartNumbers())
                .containsExactlyInAnyOrder(
                        new PartNumber(new EngineSchematicNumber(0, List.of(0, 1, 2), 467)),
                        new PartNumber(new EngineSchematicNumber(2, List.of(2, 3), 35)),
                        new PartNumber(new EngineSchematicNumber(2, List.of(6, 7, 8), 633)),
                        new PartNumber(new EngineSchematicNumber(4, List.of(0, 1, 2), 617)),
                        new PartNumber(new EngineSchematicNumber(6, List.of(2, 3, 4), 592)),
                        new PartNumber(new EngineSchematicNumber(7, List.of(6, 7, 8), 755)),
                        new PartNumber(new EngineSchematicNumber(9, List.of(1, 2, 3), 664)),
                        new PartNumber(new EngineSchematicNumber(9, List.of(5, 6, 7), 598))
                );

    }

    @Test
    void gearRatio() {
        final List<String> lines = new ArrayList<>();
        lines.add("467..114..");
        lines.add("...*......");
        lines.add("..35..633.");
        lines.add("......#...");
        lines.add("617*......");
        lines.add(".....+.58.");
        lines.add("..592.....");
        lines.add("......755.");
        lines.add("...$.*....");
        lines.add(".664.598..");

        EngineSchematic engineSchematic = new EngineSchematic(lines);

        Assertions.assertThat(engineSchematic.findGearRatios())
                .containsExactlyInAnyOrder(
                        new GearRatio(
                                new EngineSchematicSymbol(1, 3, '*'),
                                List.of(
                                        new EngineSchematicNumber(0, List.of(0, 1, 2), 467),
                                        new EngineSchematicNumber(2, List.of(2, 3), 35))),
                        new GearRatio(
                                new EngineSchematicSymbol(8, 5, '*'),
                                List.of(
                                        new EngineSchematicNumber(7, List.of(6, 7, 8), 755),
                                        new EngineSchematicNumber(9, List.of(5, 6, 7), 598)))
                );

    }


    private static Stream<? extends Arguments> symbolLines() {
        return Stream.of(
                Arguments.of(".664.598..", List.of()),
                Arguments.of("...*......", List.of(new EngineSchematicSymbol(0, 3, '*'))),
                Arguments.of("617*......", List.of(new EngineSchematicSymbol(0, 3, '*'))),
                Arguments.of("...$.*....", List.of(new EngineSchematicSymbol(0, 3, '$'), new EngineSchematicSymbol(0, 5, '*'))),
                Arguments.of(".....+.58.", List.of(new EngineSchematicSymbol(0, 5, '+'))),
                Arguments.of("......#...", List.of(new EngineSchematicSymbol(0, 6, '#')))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "symbolLines")
    void extractSymbols(String line, List<EngineSchematicSymbol> expectedSymbols) {
        Assertions.assertThat(EngineSchematic.findSymbolsFromLine(0, line)).containsExactlyInAnyOrderElementsOf(expectedSymbols);
    }


    private static Stream<? extends Arguments> numberLines() {
        return Stream.of(
                Arguments.of("467..114..", List.of(new EngineSchematicNumber(0, List.of(0, 1, 2), 467), new EngineSchematicNumber(0, List.of(5, 6, 7), 114))),
                Arguments.of("...*......", List.of()),
                Arguments.of("..35..633.", List.of(new EngineSchematicNumber(0, List.of(2, 3), 35), new EngineSchematicNumber(0, List.of(6,7,8), 633))),
                Arguments.of("617*......", List.of(new EngineSchematicNumber(0, List.of(0, 1, 2), 617))),
                Arguments.of(".....+.58.", List.of(new EngineSchematicNumber(0, List.of(7, 8), 58))),
                Arguments.of("..592.....", List.of(new EngineSchematicNumber(0, List.of(2, 3, 4), 592))),
                Arguments.of("...$.*....", List.of()),
                Arguments.of("......755.", List.of(new EngineSchematicNumber(0, List.of(6, 7, 8), 755))),
                Arguments.of(".664.598..", List.of(new EngineSchematicNumber(0, List.of(1,2,3), 664), new EngineSchematicNumber(0, List.of(5,6,7), 598))),
                Arguments.of(".......598", List.of(new EngineSchematicNumber(0, List.of(7, 8 ,9), 598)))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "numberLines")
    void extractNumbers(String line, List<EngineSchematicNumber> expectedNumbers) {
        Assertions.assertThat(EngineSchematic.findNumbersFromLine(0, line)).containsExactlyInAnyOrderElementsOf(expectedNumbers);
    }

}

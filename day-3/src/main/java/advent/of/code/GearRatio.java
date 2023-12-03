package advent.of.code;

import java.util.List;

public record GearRatio(EngineSchematicSymbol symbol, List<EngineSchematicNumber> gearNumbers) {

    public int getMultipliedRatio() {
        return gearNumbers.stream().map(EngineSchematicNumber::number).reduce(1, (a, b) -> a * b);
    }
}

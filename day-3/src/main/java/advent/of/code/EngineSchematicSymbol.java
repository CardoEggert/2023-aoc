package advent.of.code;
public record EngineSchematicSymbol(Integer rowIndex, Integer columnIndex, Character symbol) {

    public boolean isGearRatio() {
        return symbol == '*';
    }
}

package advent.of.code;

import java.util.*;

public record EngineSchematic(List<String> lines) {

    private static final List<EngineSchematicNumber> numbers = new ArrayList<>();
    private static final List<EngineSchematicSymbol> symbols = new ArrayList<>();
    private static final Set<Character> digits = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public List<GearRatio> findGearRatios() {
        load();
        // Extract gear ratios
        final List<GearRatio> gearRatios = new ArrayList<>();
        for (EngineSchematicSymbol symbol : symbols) {
            if (symbol.isGearRatio()) {
                final List<EngineSchematicNumber> closestNumbers = findCloseNumbers(symbol, numbers);
                if (closestNumbers.size() == 2) {
                    gearRatios.add(new GearRatio(symbol, closestNumbers));
                }
            }
        }
        return gearRatios;
    }

    public List<PartNumber> extractPartNumbers() {
        load();
        // Extract part numbers
        final List<PartNumber> partNumbers = new ArrayList<>();
        for (EngineSchematicSymbol symbol : symbols) {
            partNumbers.addAll(findCloseNumbers(symbol, numbers).stream().map(PartNumber::new).toList());
        }
        return partNumbers;
    }

    private void load() {
        if (numbers.isEmpty() || symbols.isEmpty()) {
            // Read in the values to maps
            for (int rowIndex = 0; rowIndex < lines.size(); rowIndex++) {
                numbers.addAll(findNumbersFromLine(rowIndex, lines.get(rowIndex)));
                symbols.addAll(findSymbolsFromLine(rowIndex, lines.get(rowIndex)));
            }
        }
    }

    protected static List<EngineSchematicNumber> findNumbersFromLine(int rowIndex, String line) {
        final List<EngineSchematicNumber> numbers = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        int numberStartIndx = 0;
        for (int colIndex = 0; colIndex < line.length(); colIndex++) {
            final char character = line.charAt(colIndex);
            if (isNumber(character)) {
                if (number.isEmpty()) {
                    numberStartIndx = colIndex;
                }
                number.append(character);
            }
            if (!isNumber(character) || colIndex + 1 == line.length()) {
                if (number.length() > 0) {
                    numbers.add(
                            new EngineSchematicNumber(
                                    rowIndex,
                                    getColIndexes(numberStartIndx, number.toString().length()),
                                    Integer.parseInt(number.toString())));
                    number.delete(0, number.length());
                    numberStartIndx = 0;
                }
            }
        }
        return numbers;
    }

    private static List<Integer> getColIndexes(int numberStartIndex, int numberLength) {
        final List<Integer> colIndexes = new ArrayList<>();
        for (int numberIter = 0; numberIter < numberLength; numberIter++) {
            colIndexes.add(numberStartIndex + numberIter);
        }
        return colIndexes;
    }

    private static boolean isNumber(char character) {
        return digits.contains(character);
    }

    protected static List<EngineSchematicSymbol> findSymbolsFromLine(int rowIndex, String line) {
        final List<EngineSchematicSymbol> symbols = new ArrayList<>();
        for (int colIndex = 0; colIndex < line.length(); colIndex++) {
            if (isSymbol(line.charAt(colIndex))) {
                symbols.add(new EngineSchematicSymbol(rowIndex, colIndex, line.charAt(colIndex)));
            }
        }
        return symbols;
    }

    private static boolean isSymbol(char character) {
        return !isNumber(character) && character != '.';
    }

    private List<EngineSchematicNumber> findCloseNumbers(EngineSchematicSymbol symbol, List<EngineSchematicNumber> numbers) {
        return numbers
                .stream()
                .filter(number -> isInRowRange(number, symbol.rowIndex()))
                .filter(number -> isInColRange(number, symbol.columnIndex()))
                .toList();
    }

    private boolean isInRowRange(EngineSchematicNumber number, int rowIndex) {
        return rowIndex - 1 <= number.rowIndex() && number.rowIndex() <= rowIndex + 1;
    }

    private boolean isInColRange(EngineSchematicNumber number, int colIndex) {
        return number.columnIndexes().contains(colIndex - 1)
                || number.columnIndexes().contains(colIndex)
                || number.columnIndexes().contains(colIndex + 1);
    }
}

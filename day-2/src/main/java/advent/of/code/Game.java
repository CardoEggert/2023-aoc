package advent.of.code;

public record Game(int id, int maxRed, int maxGreen, int maxBlue) {
    public boolean isPlayable(int countOfRed, int countOfGreen, int countOfBlue) {
        return maxRed <= countOfRed && maxGreen <= countOfGreen && maxBlue <= countOfBlue;
    }
}

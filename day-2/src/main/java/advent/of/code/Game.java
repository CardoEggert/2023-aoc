package advent.of.code;

public record Game(int id, int maxRed, int maxGreen, int maxBlue) {
    public boolean isPlayable(int countOfRed, int countOfGreen, int countOfBlue) {
        return maxRed <= countOfRed && maxGreen <= countOfGreen && maxBlue <= countOfBlue;
    }

    public int power() {
        if (maxRed == 0 && maxGreen == 0 && maxBlue == 0) {
            return 0;
        }
        return Math.max(1, maxRed) * Math.max(1, maxGreen) * Math.max(1, maxBlue);
    }
}

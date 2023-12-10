package advent.of.code;

public record RaceBoat(int speedIncreaseByMillisecond) {

    public int getTravelledDistance(int holdButtonForMilliseconds, int raceDuration) {
        if (holdButtonForMilliseconds >= raceDuration) {
            return 0;
        }
        final int speed = speedIncreaseByMillisecond * holdButtonForMilliseconds;
        return speed * (raceDuration - holdButtonForMilliseconds);
    }

    public int countNumberOfWaysToBeatRace(RaceDetail raceDetail) {
        final int raceDuration = raceDetail.raceTime();
        final int raceDistanceRecord = raceDetail.distanceRecord();
        int numberOfWaysToBeatRecord = 0;
        for (int i = 0; i < raceDuration; i++) {
            if (raceDistanceRecord < getTravelledDistance(i, raceDuration)) {
                numberOfWaysToBeatRecord++;
            }
        }
        return numberOfWaysToBeatRecord;
    }
}

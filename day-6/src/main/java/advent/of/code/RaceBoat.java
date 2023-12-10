package advent.of.code;

public record RaceBoat(long speedIncreaseByMillisecond) {

    public long getTravelledDistance(long holdButtonForMilliseconds, long raceDuration) {
        if (holdButtonForMilliseconds >= raceDuration) {
            return 0;
        }
        final long speed = speedIncreaseByMillisecond * holdButtonForMilliseconds;
        return speed * (raceDuration - holdButtonForMilliseconds);
    }

    public long countNumberOfWaysToBeatRace(RaceDetail raceDetail) {
        final long raceDuration = raceDetail.raceTime();
        final long raceDistanceRecord = raceDetail.distanceRecord();
        long numberOfWaysToBeatRecord = 0;
        for (int i = 0; i < raceDuration; i++) {
            if (raceDistanceRecord < getTravelledDistance(i, raceDuration)) {
                numberOfWaysToBeatRecord++;
            }
        }
        return numberOfWaysToBeatRecord;
    }
}

package advent.of.code;

public record RaceDetail(long raceTime, long distanceRecord) {
    public static RaceDetail read(String raceDetailText) {
        final String timeInfo = raceDetailText.split("\n")[0].replaceAll("\\D", "");
        final String distanceInfo = raceDetailText.split("\n")[1].replaceAll("\\D", "");
        return new RaceDetail(Long.parseLong(timeInfo), Long.parseLong(distanceInfo));
    }
}

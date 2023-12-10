package advent.of.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record RaceDetails(List<RaceDetail> raceDetails) {

    public static RaceDetails read(String raceDetailText) {
        final String timeInfo = raceDetailText.split("\n")[0].split("Time: ")[1];
        final List<Integer> times = Arrays.stream(timeInfo.split(" ")).filter(x -> !x.isBlank()).map(Integer::parseInt).toList();
        final String distanceInfo = raceDetailText.split("\n")[1].split("Distance: ")[1];
        final List<Integer> distances = Arrays.stream(distanceInfo.split(" ")).filter(x -> !x.isBlank()).map(Integer::parseInt).toList();
        final List<RaceDetail> raceDetailList = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            raceDetailList.add(new RaceDetail(times.get(i), distances.get(i)));
        }
        return new RaceDetails(raceDetailList);
    }

    public List<Integer> numberOfWaysToBeatRace(RaceBoat boat) {
        final List<Integer> waysToBeatRace = new ArrayList<>();
        for (RaceDetail raceDetail : raceDetails) {
            waysToBeatRace.add(boat.countNumberOfWaysToBeatRace(raceDetail));
        }
        return waysToBeatRace;
    }

}

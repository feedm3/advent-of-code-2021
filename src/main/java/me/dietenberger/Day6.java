package me.dietenberger;

import com.google.common.collect.Lists;

import java.util.List;

public class Day6 {

    public List<Integer> calculateFishAfterDays(final List<Integer> fishAgesInput, final Integer daysToAge) {
        final var fishAges = Lists.newArrayList(fishAgesInput);

        for (int day = 0; day < daysToAge; day++) {
            // we create a copy to ignore fishes that are added during the day
            final List<Integer> fishAgesAfterDay = Lists.newArrayList(fishAges);

            for (int fishIndex = 0; fishIndex < fishAges.size(); fishIndex++) {
                if (fishAgesAfterDay.get(fishIndex) == 0) {
                    fishAgesAfterDay.set(fishIndex, 7);
                    fishAgesAfterDay.add(8);
                }
                fishAgesAfterDay.set(fishIndex, fishAgesAfterDay.get(fishIndex) - 1);
            }

            fishAges.clear();
            fishAges.addAll(fishAgesAfterDay);
        }

        return fishAges;
    }
}

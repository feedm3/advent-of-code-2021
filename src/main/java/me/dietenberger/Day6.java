package me.dietenberger;

import com.google.common.collect.Lists;

import java.util.List;

public class Day6 {

    public Long calculateAmountOfFishAfterDays(final List<Integer> fishAgesInput, final Integer daysToAge) {
        final var fishPerAge = this.createEmptyList();

        // add the initial amount of fishes per age
        fishAgesInput.forEach(age -> fishPerAge.set(age, fishPerAge.get(age) + 1));

        for (int day = 0; day < daysToAge; day++) {
            // we create a copy to ignore fishes that are added during the day
            final var fishPerAgePerDay = this.createEmptyList();

            // we calc backwards to add the new fish at the end
            for (int nextAge = 7; nextAge >= 0; nextAge--) {
                if (nextAge == 0) {
                    // create new fishes
                    fishPerAgePerDay.set(8, fishPerAge.get(0));

                    // add the old fishes to the age 6 fishes
                    fishPerAgePerDay.set(6, fishPerAgePerDay.get(6) + fishPerAge.get(0));
                }

                fishPerAgePerDay.set(nextAge, fishPerAge.get(nextAge + 1));
            }

            // add the new fishes per age to our global variable
            fishPerAge.clear();
            fishPerAge.addAll(fishPerAgePerDay);
        }

        return fishPerAge.stream().mapToLong(number -> number).sum();
    }

    private List<Long> createEmptyList() {
        return Lists.newArrayList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
    }
}

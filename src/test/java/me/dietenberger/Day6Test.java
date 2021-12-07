package me.dietenberger;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class Day6Test {

    final List<Integer> INTRO_FISH_AGES = List.of(3, 4, 3, 1, 2);
    final List<Integer> INPUT_FILE_FISH_AGES = FileLoader.getCommaSeparatedNumbers("day-6-input.txt");

    @Test
    void partOne() {
        // given
        final Day6 day6 = new Day6();

        // when
        var amountOfFish = day6.calculateAmountOfFishAfterDays(INTRO_FISH_AGES, 18);

        // then
        assertThat(amountOfFish).isEqualTo(26);

        // when
        var largerAmountOfFish = day6.calculateAmountOfFishAfterDays(INTRO_FISH_AGES, 80);

        // then
        assertThat(largerAmountOfFish).isEqualTo(5934);

        // solution
        var fishAgesSolution = day6.calculateAmountOfFishAfterDays(INPUT_FILE_FISH_AGES, 80);
        System.out.println("Result Day 6 - Part 1: " + fishAgesSolution);
    }

    @Test
    void partTwo() {
        // given
        final Day6 day6 = new Day6();

        // when
        var amountOfFish = day6.calculateAmountOfFishAfterDays(INTRO_FISH_AGES, 256);

        // then
        assertThat(amountOfFish).isEqualTo(26984457539L);

        // solution
        var fishAgesSolution = day6.calculateAmountOfFishAfterDays(INPUT_FILE_FISH_AGES, 256);
        System.out.println("Result Day 6 - Part 2: " + fishAgesSolution);
    }
}

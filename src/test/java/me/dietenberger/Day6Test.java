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
        var fishAges = day6.calculateFishAfterDays(INTRO_FISH_AGES, 18);

        // then
        assertThat(fishAges.size()).isEqualTo(26);

        // when
        var fishAgesAfterEighty = day6.calculateFishAfterDays(INTRO_FISH_AGES, 80);

        // then
        assertThat(fishAgesAfterEighty.size()).isEqualTo(5934);

        // solution
        var fishAgesSolution = day6.calculateFishAfterDays(INPUT_FILE_FISH_AGES, 80);
        System.out.println("Result Day 6 - Part 1: " + fishAgesSolution.size());
    }
}

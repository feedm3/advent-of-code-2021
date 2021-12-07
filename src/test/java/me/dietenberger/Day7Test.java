package me.dietenberger;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class Day7Test {

    final List<Integer> INTRO_HORIZONTAL_POSITIONS = List.of(16,1,2,0,4,2,7,1,2,14);
    final List<Integer> INPUT_FILE_HORIZONTAL_POSITIONS = FileLoader.getCommaSeparatedNumbers("day-7-input.txt");

    @Test
    void partOne() {
        // given
        final Day7 day7 = new Day7();

        // when
        var distanceCost = day7.calculateDistanceCost(INTRO_HORIZONTAL_POSITIONS);

        // then
        assertThat(distanceCost).isEqualTo(37);

        // solution
        var distanceCostSolution = day7.calculateDistanceCost(INPUT_FILE_HORIZONTAL_POSITIONS);
        System.out.println("Result Day 7 - Part 1: " + distanceCostSolution);
    }

    @Test
    void partTwo() {
        // given
        final Day7 day7 = new Day7();

        // when
        var distanceCost = day7.calculateDistanceCostWithIncreasingCosts(INTRO_HORIZONTAL_POSITIONS);

        // then
        assertThat(distanceCost).isEqualTo(168);

        // solution
        var distanceCostSolution = day7.calculateDistanceCostWithIncreasingCosts(INPUT_FILE_HORIZONTAL_POSITIONS);
        System.out.println("Result Day 7 - Part 2: " + distanceCostSolution);
    }
}

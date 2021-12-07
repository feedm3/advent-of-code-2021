package me.dietenberger;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.*;

public class Day5Test {
    final List<String> INPUT_COORDINATES = List.of(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
    );
    final List<String> INPUT_FILE_COORDINATES = FileLoader.getLines("day-5-input.txt");

    @Test
    void partOne() {
        // given
        final Day5 day5 = new Day5();

        // when
        final var fromToCoordinates = day5.parseFromToCoordinates(INPUT_COORDINATES);

        // then
        assertThat(fromToCoordinates.size()).isEqualTo(10);
        assertThat(fromToCoordinates.get(0).left.x()).isEqualTo(0);
        assertThat(fromToCoordinates.get(0).left.y()).isEqualTo(9);
        assertThat(fromToCoordinates.get(2).right.x()).isEqualTo(3);
        assertThat(fromToCoordinates.get(2).right.y()).isEqualTo(4);

        // when
        final var overlaps = day5.calculateCoordinateOverlaps(fromToCoordinates, Day5.CalculationMode.HORIZONTAL_VERTICAL);

        // then
        assertThat(overlaps).isEqualTo(5);

        // solution
        final var fromToCoordinatesSolution = day5.parseFromToCoordinates(INPUT_FILE_COORDINATES);
        final var overlapsSolution = day5.calculateCoordinateOverlaps(fromToCoordinatesSolution, Day5.CalculationMode.HORIZONTAL_VERTICAL);

        System.out.println("Result Day 5 - Part 1: " + overlapsSolution);
    }

    @Test
    void partTwo() {
        // given
        final Day5 day5 = new Day5();

        // when
        final var fromToCoordinates = day5.parseFromToCoordinates(INPUT_COORDINATES);
        final var overlaps = day5.calculateCoordinateOverlaps(fromToCoordinates, Day5.CalculationMode.HORIZONTAL_VERTICAL_DIAGONAL);

        // then
        assertThat(overlaps).isEqualTo(12);


        // solution
        final var fromToCoordinatesSolution = day5.parseFromToCoordinates(INPUT_FILE_COORDINATES);
        final var overlapsSolution = day5.calculateCoordinateOverlaps(fromToCoordinatesSolution, Day5.CalculationMode.HORIZONTAL_VERTICAL_DIAGONAL);

        System.out.println("Result Day 5 - Part 2: " + overlapsSolution);
    }
}

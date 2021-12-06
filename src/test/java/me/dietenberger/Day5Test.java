package me.dietenberger;

import com.google.common.truth.Truth;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    final List<String> INPUT_FILE_COORDINATES = FileLoader.getLinesFromInput("day-5-input.txt");

    @Test
    void partOne() {
        // given
        final Day5 day5 = new Day5();

        // when
        final var fromToCoordinates = day5.parseFromToCoordinates(INPUT_COORDINATES);

        // then
        Truth.assertThat(fromToCoordinates.size()).isEqualTo(10);
        Truth.assertThat(fromToCoordinates.get(0).left.x()).isEqualTo(0);
        Truth.assertThat(fromToCoordinates.get(0).left.y()).isEqualTo(9);
        Truth.assertThat(fromToCoordinates.get(2).right.x()).isEqualTo(3);
        Truth.assertThat(fromToCoordinates.get(2).right.y()).isEqualTo(4);

        // when
        final var overlaps = day5.calculateCoordinateOverlaps(fromToCoordinates);

        // then
        Truth.assertThat(overlaps).isEqualTo(5);

        // solution
        final var fromToCoordinatesSolution = day5.parseFromToCoordinates(INPUT_FILE_COORDINATES);
        final var overlapsSolution = day5.calculateCoordinateOverlaps(fromToCoordinatesSolution);

        System.out.println("Result Day 5 - Part 1: " + overlapsSolution);
    }

    @Test
    void partTwo() {
        // given


        System.out.println("Result Day 5 - Part 2: ");
    }
}

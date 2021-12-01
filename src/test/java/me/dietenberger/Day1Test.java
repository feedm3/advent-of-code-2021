package me.dietenberger;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    final List<Integer> INTRO_NUMBERS = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
    final List<Integer> INPUT_FILE_NUMBERS = FileLoader.getNumbersFromInput("day-1-input.txt");

    @Test
    void partOne() {
        // given
        final Day1 day1 = new Day1();

        // when
        int introNumberIncreases = day1.calculateIncreases(INTRO_NUMBERS);
        int fileNumberIncreases = day1.calculateIncreases(INPUT_FILE_NUMBERS);

        // then
        assertEquals(introNumberIncreases, 7);
        assertEquals(fileNumberIncreases, 1451);
    }

    @Test
    void partTwo() {
        // given
        final Day1 day1 = new Day1();

        // when
        int introNumberIncreases = day1.calculateSlidingWindowIncreases(INTRO_NUMBERS);
        int fileNumberIncreases = day1.calculateSlidingWindowIncreases(INPUT_FILE_NUMBERS);

        // then
        assertEquals(introNumberIncreases, 5);
        assertEquals(fileNumberIncreases, 1395);
    }
}

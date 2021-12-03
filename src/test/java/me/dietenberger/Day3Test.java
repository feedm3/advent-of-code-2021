package me.dietenberger;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    final List<String> INPUT_BIANRY = List.of(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
    );
    final List<String> INPUT_FILE_BINARY = FileLoader.getLinesFromInput("day-3-input.txt");

    @Test
    void partOne() {
        // given
        final Day3 day3 = new Day3();

        // when
        final String mostCommonBinariesPerPosition = day3.getMostCommonBinaryPerPosition(INPUT_BIANRY);
        final int gammaRate = day3.parseBinary(mostCommonBinariesPerPosition);

        final String epsilonBinary = day3.invertBinary(mostCommonBinariesPerPosition);
        final int epsilonRate = day3.parseBinary(epsilonBinary);

        final int powerConsumption = gammaRate * epsilonRate;

        // then
        assertEquals("10110", mostCommonBinariesPerPosition);
        assertEquals(22, gammaRate);
        assertEquals(9, epsilonRate);
        assertEquals(198, powerConsumption);

        // print result
        final String mostCommonBinariesPerPositionSolution = day3.getMostCommonBinaryPerPosition(INPUT_FILE_BINARY);
        final int gammaRateSolution = day3.parseBinary(mostCommonBinariesPerPositionSolution);
        final String epsilonBinarySolution = day3.invertBinary(mostCommonBinariesPerPositionSolution);
        final int epsilonRateSolution = day3.parseBinary(epsilonBinarySolution);

        final int powerConsumptionSolution = gammaRateSolution * epsilonRateSolution;

        System.out.println("Result Day 3 - Part 1: " + powerConsumptionSolution);
    }

    @Test
    void partTwo() {
        // given
        final Day3 day3 = new Day3();

        // when
        final String mostCommonBinary = day3.getBinaryByQuantityValuePerPosition(INPUT_BIANRY, Day3.Quantity.MOST);
        final int oxygenGeneratorRating = day3.parseBinary(mostCommonBinary);

        final String leastCommonBinary = day3.getBinaryByQuantityValuePerPosition(INPUT_BIANRY, Day3.Quantity.LEAST);
        final int co2ScrubberRating = day3.parseBinary(leastCommonBinary);

        final int lifeSupportRating = oxygenGeneratorRating * co2ScrubberRating;

        // then
        assertEquals("10111", mostCommonBinary);
        assertEquals(23, oxygenGeneratorRating);
        assertEquals("01010", leastCommonBinary);
        assertEquals(10, co2ScrubberRating);
        assertEquals(230, lifeSupportRating);

        // solution
        final String mostCommonBinarySolution = day3.getBinaryByQuantityValuePerPosition(INPUT_FILE_BINARY, Day3.Quantity.MOST);
        final int oxygenGeneratorRatingSolution = day3.parseBinary(mostCommonBinarySolution);
        final String leastCommonBinarySolution = day3.getBinaryByQuantityValuePerPosition(INPUT_FILE_BINARY, Day3.Quantity.LEAST);
        final int co2ScrubberRatingSolution = day3.parseBinary(leastCommonBinarySolution);

        final int lifeSupportRatingSolution = oxygenGeneratorRatingSolution * co2ScrubberRatingSolution;
        System.out.println("Result Day 3 - Part 2: " + lifeSupportRatingSolution);
    }
}

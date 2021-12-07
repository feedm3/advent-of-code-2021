package me.dietenberger;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    final List<String> INPUT_COMMANDS = List.of(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
    );
    final List<String> INPUT_FILE_COMMANDS = FileLoader.getLines("day-2-input.txt");

    @Test
    void partOne() {
        // given
        final Day2 day2 = new Day2();
        final List<ImmutablePair<Day2.Command, Integer>> commands = day2.parseCommands(INPUT_COMMANDS);

        // when
        final Integer horizontal = day2.calculateHorizontal(commands);
        final Integer depth = day2.calculateDepth(commands);

        // then
        assertEquals(15, horizontal);
        assertEquals(10, depth);

        // solution
        final List<ImmutablePair<Day2.Command, Integer>> fileCommands = day2.parseCommands(INPUT_FILE_COMMANDS);

        final int solutionHorizontal =  day2.calculateHorizontal(fileCommands);
        final int solutionDepth =  day2.calculateDepth(fileCommands);
        final int solution = solutionDepth * solutionHorizontal;

        System.out.println("Result Day 2 - Part 1: " + solution);
    }

    @Test
    void partTwo() {
        // given
        final Day2 day2 = new Day2();
        final List<ImmutablePair<Day2.Command, Integer>> commands = day2.parseCommands(INPUT_COMMANDS);

        // when
        final Integer depth = day2.calculateDepthWithAim(commands);

        // then
        assertEquals(60, depth);

        // solution
        final List<ImmutablePair<Day2.Command, Integer>> fileCommands = day2.parseCommands(INPUT_FILE_COMMANDS);

        final int solutionHorizontal =  day2.calculateHorizontal(fileCommands);
        final int solutionDepth =  day2.calculateDepthWithAim(fileCommands);
        final int solution = solutionDepth * solutionHorizontal;

        System.out.println("Result Day 2 - Part 2: " + solution);
    }
}

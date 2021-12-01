package me.dietenberger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    final List<Integer> testNumbers = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    private static void partOne() {
        final List<Integer> numbers = getNumbersFromInput();

        int increases = 0;

        // we can start at index 1, as we always start to compare the previous one
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i - 1) < numbers.get(i)) {
                increases++;
            }
        }
        System.out.println("Number of increases: " + increases);
    }

    private static void partTwo() {
        final List<Integer> numbers = getNumbersFromInput();

        int increases = 0;
        // we start at 3, as the first comparison is with the first 4 entries:
        //   - sliding window 1 = index 0 - 2
        //   - sliding window 2 = index 1 - 3
        //   - in total = index 0 - 4
        for (int i = 3; i < numbers.size(); i++) {
            final int firstSlidingWindow = numbers.get(i - 3) + numbers.get(i - 2) + numbers.get(i - 1);
            final int secondSlidingWindow = numbers.get(i - 2) + numbers.get(i - 1) + numbers.get(i);
            if (firstSlidingWindow < secondSlidingWindow) {
                increases++;
            }
        }
        System.out.println("Number of increases: " + increases);
    }

    private static List<Integer> getNumbersFromInput() {
        try {
            final List<String> numbersAsStrings = Files.readAllLines(Paths.get("src/main/java/me/dietenberger/day-1-input.txt"));
            return numbersAsStrings.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found! " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

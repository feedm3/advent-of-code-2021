package me.dietenberger;

import java.util.List;

public class Day1 {
    public Integer calculateIncreases(final List<Integer> numbers) {
        int increases = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i - 1) < numbers.get(i)) {
                increases++;
            }
        }
        return increases;
    }

    public Integer calculateSlidingWindowIncreases(final List<Integer> numbers) {
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
        return increases;
    }

}

package me.dietenberger;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {

    public String getMostCommonBinaryPerPosition(final List<String> binaries) {
        final int positions = binaries.get(0).length();

        final StringBuilder mostCommonBinary = new StringBuilder();

        // iterate over each position
        IntStream.range(0, positions)
                .forEach(position -> {
                    final long sum = binaries.stream()
                            // extract the char of the current position
                            .map(line -> {
                                final String characterAtPosition = line.substring(position, position + 1);
                                return Integer.parseInt(characterAtPosition);
                            })
                            // map the 0 to -1 to be able to sum them up
                            .mapToInt(number -> number == 1 ? 1 : -1)
                            .sum();

                    // if the sum is 0, the fallback is 1 (for Part 2)
                    // if there are more 1's then 0's, the sum is positive
                    final String binary = sum == 0 ? "1" :
                            sum > 0 ? "1" : "0";
                    mostCommonBinary.append(binary);
                });

        return mostCommonBinary.toString();
    }

    final String getBinaryByMostCommonValuePerPosition(final List<String> binaries) {
        final int positions = binaries.get(0).length();

        final List<String> binariesStore = new ArrayList<>(binaries);
        IntStream.range(0, positions).forEach(position -> {
            if (binariesStore.size() == 1) {
                return;
            }

            final String mostCommonBinaries = getMostCommonBinaryPerPosition(binariesStore);
            final List<String> mostCommonBinariesForPosition = binariesStore.stream()
                    .filter(line -> line.charAt(position) == mostCommonBinaries.charAt(position))
                    .collect(Collectors.toList());
            binariesStore.clear();
            binariesStore.addAll(mostCommonBinariesForPosition);
        });
        return binariesStore.get(0);
    }

    final String getBinaryByLeastCommonValuePerPosition(final List<String> binaries) {
        final int positions = binaries.get(0).length();

        final List<String> binariesStore = new ArrayList<>(binaries);
        IntStream.range(0, positions).forEach(position -> {
            if (binariesStore.size() == 1) {
                return;
            }

            final String mostCommonBinaries = getMostCommonBinaryPerPosition(binariesStore);
            final String leastCommonBinaries = invertBinary(mostCommonBinaries);
            final List<String> leastCommonBinariesForPosition = binariesStore.stream()
                    .filter(line -> line.charAt(position) == leastCommonBinaries.charAt(position))
                    .collect(Collectors.toList());
            binariesStore.clear();
            binariesStore.addAll(leastCommonBinariesForPosition);
        });
        return binariesStore.get(0);
    }

    public int parseBinary(final String binary) {
        return Integer.parseInt(binary, 2);
    }

    /**
     * Invert binary. 001101 gets to 110010
     * <p>
     * https://stackoverflow.com/questions/22628724/inverting-bits-of-binary-string-java
     */
    public String invertBinary(final String binary) {
        return StringUtils.replaceChars(binary, "01", "10");
    }
}

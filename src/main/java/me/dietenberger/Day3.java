package me.dietenberger;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {

    enum Quantity {MOST, LEAST,}

    public String getMostCommonBinaryPerPosition(final List<String> binaries) {
        // all binaries have the same length, so we just grab the first one
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

    final String getBinaryByQuantityValuePerPosition(final List<String> binaries, final Quantity quantity) {
        // all binaries have the same length, so we just grab the first one
        final int positions = binaries.get(0).length();

        // we start with all binaries
        final List<String> binariesStore = new ArrayList<>(binaries);

        // max amount of iterations is the amount of positions
        IntStream.range(0, positions)
                .forEach(position -> {
                    // as soon as there is only 1 entry, we finish iterating
                    if (binariesStore.size() == 1) {
                        return;
                    }

                    // we need to know which is the most common binary
                    final String mostCommonBinaries = getMostCommonBinaryPerPosition(binariesStore);

                    // if we want to search for the least common one, we invert the binary
                    final String commonBinaries = quantity == Quantity.MOST ? mostCommonBinaries : invertBinary(mostCommonBinaries);

                    // now we filter for the most/least common binary
                    final List<String> binariesForPosition = binariesStore.stream()
                            .filter(line -> line.charAt(position) == commonBinaries.charAt(position))
                            .collect(Collectors.toList());

                    // and save it within our store to use it for the next iteration
                    binariesStore.clear();
                    binariesStore.addAll(binariesForPosition);
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

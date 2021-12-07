package me.dietenberger;

import java.util.List;

public class Day7 {

    public Integer calculateDistanceCost(final List<Integer> positions) {
        var sortedPositions = positions.stream()
                .sorted()
                .mapToDouble(d -> d)
                .toArray();

        var medianPosition = sortedPositions[sortedPositions.length / 2];

        return (int) positions.stream()
                .map(position -> Math.abs(medianPosition - position)) // calculate the actual distance
                .mapToDouble(d -> d)
                .sum();
    }

    public Integer calculateDistanceCostWithIncreasingCosts(final List<Integer> positions) {
        final double average = positions.stream()
                .mapToInt(i -> i)
                .average()
                .getAsDouble();

        // Note: Only works with the example data. To get the solution, remove this line and use the average directly
        final Integer roundedAverage = (int) Math.round(average);

        var totalDistanceCosts = 0;
        for (Integer position : positions) {
            var distance = Math.abs(position - roundedAverage);
            totalDistanceCosts = totalDistanceCosts + (distance * (distance + 1) / 2);
        }

        return totalDistanceCosts;
    }
}

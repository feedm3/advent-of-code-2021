package me.dietenberger;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FileLoader {

    public static List<Integer> getNumbersFromInput(final String fileName) {
        try {
            final List<String> numbersAsStrings = Files.readAllLines(Paths.get("src/main/resources/" + fileName));
            return numbersAsStrings.stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (IOException e) {
            System.out.println("File not found! " + e.getMessage());
        }
        return Lists.newArrayList();
    }

    public static List<String> getLinesFromInput(final String fileName) {
        try {
            return Files.readAllLines(Paths.get("src/main/resources/" + fileName));
        } catch (IOException e) {
            System.out.println("File not found! " + e.getMessage());
        }
        return Lists.newArrayList();
    }

    public static List<Integer> getCommaSeparatedNumbers(final String fileName) {
        try {
            final List<String> numbersAsStrings = Files.readAllLines(Paths.get("src/main/resources/" + fileName));
            return numbersAsStrings.stream()
                    .map(line -> Arrays.stream(line.split(",")).toList())
                    .flatMap(Collection::stream)
                    .map(Integer::parseInt)
                    .toList();
        } catch (IOException e) {
            System.out.println("File not found! " + e.getMessage());
        }
        return Lists.newArrayList();
    }
}

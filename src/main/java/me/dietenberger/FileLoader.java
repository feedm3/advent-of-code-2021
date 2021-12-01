package me.dietenberger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoader {

    public static List<Integer> getNumbersFromInput(final String fileName) {
        try {
            final List<String> numbersAsStrings = Files.readAllLines(Paths.get("src/main/resources/" + fileName));
            return numbersAsStrings.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found! " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

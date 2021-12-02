package me.dietenberger;

import com.google.common.base.Enums;

import java.util.AbstractMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class Day2 {
    enum Command {FORWARD, UP, DOWN,}

    public int calculateHorizontal(final List<AbstractMap.SimpleImmutableEntry<Command, Integer>> commands) {
        return commands.stream().mapToInt(entry -> {
            if (entry.getKey() == Command.FORWARD) {
                return entry.getValue();
            }
            return 0;
        }).sum();
    }

    public int calculateDepth(final List<AbstractMap.SimpleImmutableEntry<Command, Integer>> commands) {
        return commands.stream().mapToInt(entry -> {
            if (entry.getKey() == Command.UP) {
                return entry.getValue() * -1;
            }
            if (entry.getKey() == Command.DOWN) {
                return entry.getValue();
            }
            return 0;
        }).sum();
    }

    public int calculateDepthWithAim(final List<AbstractMap.SimpleImmutableEntry<Command, Integer>> commands) {
        int aim = 0;
        int depth = 0;
        for (AbstractMap.SimpleImmutableEntry<Command, Integer> command : commands) {
            if (command.getKey() == Command.UP) {
                aim = aim - command.getValue();
                continue;
            }
            if (command.getKey() == Command.DOWN) {
                aim = aim + command.getValue();
                continue;
            }
            depth = depth + (aim * command.getValue());
        }

        return depth;
    }

    public List<AbstractMap.SimpleImmutableEntry<Command, Integer>> parseCommands(final List<String> commandLines) {
        return commandLines.stream()
                .map(line -> line.split(" "))
                .map(splitLine -> new AbstractMap.SimpleImmutableEntry<>(Enums.getIfPresent(Command.class, splitLine[0].toUpperCase(Locale.ROOT)).get(), Integer.parseInt(splitLine[1])))
                .collect(Collectors.toList());
    }
}

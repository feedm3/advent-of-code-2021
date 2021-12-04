package me.dietenberger;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BingoField {

    final List<List<Pair<Integer, Boolean>>> entries = Lists.newArrayList();

    private BingoField() {
    }

    /**
     * Create a new Bingo Field by parsing the String representation of it.
     */
    public static BingoField of(final List<String> lines) {
        final BingoField bingoField = new BingoField();

        final AtomicInteger rowPosition = new AtomicInteger(0);
        final AtomicInteger columnPosition = new AtomicInteger(0);

        lines.forEach(line -> {
            // each row we start at column one
            columnPosition.set(0);

            Arrays.stream(line.split(" "))
                    .filter(StringUtils::isNoneEmpty)
                    .map(Integer::parseInt)
                    .forEach(number -> {
                        // first access to the row creates the list for the column
                        if (columnPosition.getAndIncrement() == 0) {
                            bingoField.entries.add(Lists.newArrayList());
                        }

                        final List<Pair<Integer, Boolean>> row = bingoField.entries.get(rowPosition.get());

                        // we add one entry after another for each row
                        row.add(MutablePair.of(number, false));
                    });

            // go to next row
            rowPosition.incrementAndGet();
        });

        return bingoField;
    }

    public void setNumber(final Integer number) {
        this.entries.forEach(row -> {
            row.forEach(entry -> {
                if (entry.getKey().equals(number)) {
                    entry.setValue(true);
                }
            });
        });
    }

    public Optional<List<Integer>> hasWinningRow() {
        return hasWinningRow(this.entries);
    }

    public Optional<List<Integer>> hasWinningColumn() {
        // we transpose the list to be able to check them the same way as the row
        final List<List<Pair<Integer, Boolean>>> transposedEntries = IntStream.range(0, this.entries.get(0).size())
                .mapToObj(i -> this.entries.stream()
                        .map(l -> l.get(i))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        return hasWinningRow(transposedEntries);
    }

    public int getSumOfUnmarkedFields() {
        return entries.stream()
                .flatMap(Collection::stream)
                .filter(pair -> !pair.getValue())
                .map(Pair::getKey).mapToInt(i -> i).sum();
    }

    private Optional<List<Integer>> hasWinningRow(final List<List<Pair<Integer, Boolean>>> entries) {
        return entries.stream()
                // get all numbers of a row that are checked
                .map(row -> row.stream()
                        .filter(Pair::getValue)
                        .map(Pair::getKey)
                        .collect(Collectors.toList()))
                .filter(correctNumbersPerRow -> correctNumbersPerRow.size() == 5)
                .findFirst();
    }
}

public class Day4 {

    public List<Integer> createBingoNumbers(final String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<BingoField> createBingoFields(final List<String> numberLines) {
        // we skip the draw numbers
        final List<String> allBingoFieldLines = numberLines.stream()
                .skip(1)
                .collect(Collectors.toList());

        final List<BingoField> bingoFields = Lists.newArrayList();

        final List<String> oneBingoFieldLines = Lists.newArrayList();
        allBingoFieldLines.forEach(line -> {
            if (StringUtils.isEmpty(line)) {
                return;
            }

            oneBingoFieldLines.add(line);

            // at 5 lines, the bingo field is complete
            if (oneBingoFieldLines.size() == 5) {
                final BingoField bingoField = BingoField.of(oneBingoFieldLines);
                bingoFields.add(bingoField);

                oneBingoFieldLines.clear();
            }
        });

        return bingoFields;
    }

    public Pair<BingoField, Integer> getFirstWinningBingoFiledWithWinningNumber(final List<BingoField> bingoFields, final List<Integer> numbers) {
        return numbers.stream()
                .map(number -> {
                    setNumberOnBingoFields(number, bingoFields);

                    return Pair.of(getWinningBingoField(bingoFields), number);
                })
                .filter(bingoNumberPair -> bingoNumberPair.getKey().isPresent())
                .map(bingoNumberPair -> Pair.of(bingoNumberPair.getKey().get(), bingoNumberPair.getRight()))
                .findFirst()
                .get();
    }

    public Pair<BingoField, Integer> getLastWinningBingoFiledWithWinningNumber(final List<BingoField> bingoFields, final List<Integer> numbers) {
        final List<BingoField> runningBingoFields = Lists.newArrayList();
        final List<Pair<BingoField, Integer>> winningBingoFields = Lists.newArrayList();

        runningBingoFields.addAll(bingoFields);

        for (Integer number : numbers) {
            if (winningBingoFields.size() == bingoFields.size()) {
                continue;
            }

            setNumberOnBingoFields(number, runningBingoFields);

            final List<BingoField> newWinningBingoField = getWinningBingoFields(runningBingoFields);

            if (newWinningBingoField.size() > 0) {
                newWinningBingoField.forEach(field -> {
                    winningBingoFields.add(Pair.of(field, number));
                    runningBingoFields.remove(field);
                });
            }
        }

        return winningBingoFields.get(winningBingoFields.size() - 1);
    }


    private void setNumberOnBingoFields(final Integer number, final List<BingoField> bingoFields) {
        bingoFields.forEach(bingoField -> {
            bingoField.setNumber(number);
        });
    }

    private Optional<BingoField> getWinningBingoField(final List<BingoField> bingoFields) {
        return bingoFields.stream()
                .filter(bingoField -> bingoField.hasWinningColumn().isPresent() || bingoField.hasWinningRow().isPresent())
                .findFirst();
    }

    private List<BingoField> getWinningBingoFields(final List<BingoField> bingoFields) {
        return bingoFields.stream()
                .filter(bingoField -> bingoField.hasWinningColumn().isPresent() || bingoField.hasWinningRow().isPresent())
                .collect(Collectors.toList());
    }
}

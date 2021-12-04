package me.dietenberger;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class Day4Test {
    final List<String> INPUT_BINGO = List.of(
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
            "",
            "22 13 17 11  0",
            "8  2 23  4 24",
            "21  9 14 16  7",
            "6 10  3 18  5",
            "1 12 20 15 19",
            "",
            "3 15  0  2 22",
            "9 18 13 17  5",
            "19  8  7 25 23",
            "20 11 10 24  4",
            "14 21 16 12  6",
            "",
            "14 21 17 24  4",
            "10 16 15  9 19",
            "18  8 23 26 20",
            "22 11 13  6  5",
            "2  0 12  3  7"
    );
    final List<String> INPUT_FILE_BINGO = FileLoader.getLinesFromInput("day-4-input.txt");

    @Test
    void bingoField() {
        // given
        final BingoField bingoField = BingoField.of(Lists.newArrayList(
                "1  2 3 4 5",
                "3 4 5 6 7",
                "1 2 30 40 60",
                "1 2 31 4 7",
                "6 2 32 4 8"
        ));

        assertThat(bingoField.hasWinningColumn().isPresent()).isFalse();
        assertThat(bingoField.hasWinningRow().isPresent()).isFalse();

        // when setting only numbers from a row
        bingoField.setNumber(3);
        bingoField.setNumber(4);
        bingoField.setNumber(5);
        bingoField.setNumber(6);
        bingoField.setNumber(7);

        // then only the row is winning
        assertThat(bingoField.hasWinningRow().isPresent()).isTrue();
        assertThat(bingoField.hasWinningRow().get()).containsExactly(3, 4, 5, 6, 7);
        assertThat(bingoField.hasWinningColumn().isPresent()).isFalse();

        // when also setting the missing number of a column
        bingoField.setNumber(1);

        // the column is also winning
        assertThat(bingoField.hasWinningRow().isPresent()).isTrue();
        assertThat(bingoField.hasWinningRow().get()).containsExactly(3, 4, 5, 6, 7);
        assertThat(bingoField.hasWinningColumn().isPresent()).isTrue();
        assertThat(bingoField.hasWinningColumn().get()).containsExactly(1, 3, 1, 1, 6);
    }

    @Test
    void partOne() {
        // given
        final Day4 day4 = new Day4();
        final List<Integer> bingoNumbers = day4.createBingoNumbers(INPUT_BINGO.get(0));
        final List<BingoField> bingoFields = day4.createBingoFields(INPUT_BINGO);

        // then
        assertThat(bingoFields.size()).isEqualTo(3);

        // when
        final Pair<BingoField, Integer> winningBingoFiledWithWinningNumber = day4.getFirstWinningBingoFiledWithWinningNumber(bingoFields, bingoNumbers);
        final BingoField bingoField = winningBingoFiledWithWinningNumber.getKey();
        final int winningNumber = winningBingoFiledWithWinningNumber.getValue();
        final int sumOfUnmarkedFields = bingoField.getSumOfUnmarkedFields();

        // then
        assertThat(bingoField.hasWinningRow().isPresent()).isTrue();
        assertThat(bingoField.hasWinningRow().get()).containsExactly(14, 21, 17, 24, 4);
        assertThat(winningNumber).isEqualTo(24);
        assertThat(sumOfUnmarkedFields).isEqualTo(188);

        // solution
        final List<Integer> bingoNumbersSolution = day4.createBingoNumbers(INPUT_FILE_BINGO.get(0));
        final List<BingoField> bingoFieldsSolution = day4.createBingoFields(INPUT_FILE_BINGO);

        final Pair<BingoField, Integer> winningBingoFiledWithWinningNumberSolution = day4.getFirstWinningBingoFiledWithWinningNumber(bingoFieldsSolution, bingoNumbersSolution);
        final BingoField bingoFieldSolution = winningBingoFiledWithWinningNumberSolution.getKey();
        final Integer winningNumberSolution = winningBingoFiledWithWinningNumberSolution.getValue();
        final int sumOfUnmarkedFieldsSolution = bingoFieldSolution.getSumOfUnmarkedFields();

        final int solution = sumOfUnmarkedFieldsSolution * winningNumberSolution;

        System.out.println("Result Day 4 - Part 1: " + solution);
    }

    @Test
    void partTwo() {
        // given
        final Day4 day4 = new Day4();
        final List<Integer> bingoNumbers = day4.createBingoNumbers(INPUT_BINGO.get(0));
        final List<BingoField> bingoFields = day4.createBingoFields(INPUT_BINGO);

        // then
        assertThat(bingoFields.size()).isEqualTo(3);

        // when
        final Pair<BingoField, Integer> winningBingoFiledWithWinningNumber = day4.getLastWinningBingoFiledWithWinningNumber(bingoFields, bingoNumbers);
        final BingoField bingoField = winningBingoFiledWithWinningNumber.getKey();
        final int winningNumber = winningBingoFiledWithWinningNumber.getValue();
        final int sumOfUnmarkedFields = bingoField.getSumOfUnmarkedFields();

        // then
        assertThat(winningNumber).isEqualTo(13);
        assertThat(sumOfUnmarkedFields).isEqualTo(148);

        // solution
        final List<Integer> bingoNumbersSolution = day4.createBingoNumbers(INPUT_FILE_BINGO.get(0));
        final List<BingoField> bingoFieldsSolution = day4.createBingoFields(INPUT_FILE_BINGO);

        final Pair<BingoField, Integer> winningBingoFiledWithWinningNumberSolution = day4.getLastWinningBingoFiledWithWinningNumber(bingoFieldsSolution, bingoNumbersSolution);
        final BingoField bingoFieldSolution = winningBingoFiledWithWinningNumberSolution.getKey();
        final Integer winningNumberSolution = winningBingoFiledWithWinningNumberSolution.getValue();
        final int sumOfUnmarkedFieldsSolution = bingoFieldSolution.getSumOfUnmarkedFields();

        final int solution = sumOfUnmarkedFieldsSolution * winningNumberSolution;

        System.out.println("Result Day 4 - Part 2: " + solution);
    }
}

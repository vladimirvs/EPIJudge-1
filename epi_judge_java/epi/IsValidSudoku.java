package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;

public class IsValidSudoku {
    @EpiTest(testDataFile = "is_valid_sudoku.tsv")

    // Check if a partially filled matrix has any conflicts.
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {

        System.out.println();
        for (List<Integer> row : partialAssignment) {
            System.out.println(row);
        }

        //Check that all rows are valid
        for (int i = 0; i < partialAssignment.size(); i++) {
            boolean valid = isValid(partialAssignment.get(i));
            if (!valid) {
                return false;
            }
        }

        //Get and check each col
        for (int j = 0; j < partialAssignment.get(0).size(); j++) {
            List<Integer> col = new ArrayList<>();
            for (int k = 0; k < partialAssignment.size(); k++) {
                col.add(partialAssignment.get(k).get(j));
            }
            boolean valid = isValid(col);
            if (!valid) {
                return false;
            }
        }

        int startRow = 0;
        int startCol = 0;

        for (int i = 0; i <= partialAssignment.size() - 3; i += 3) {
            startRow = i;
            for (int j = 0; j <= partialAssignment.size() - 3; j += 3) {

                startCol = j;


                List<Integer> subgrid = getSubgrid(partialAssignment, startRow, startCol, startRow + 3, startCol + 3);
                boolean valid = isValid(subgrid);
                if (!valid) {
                    return false;
                }
            }
        }


        return true;
    }

    private static List<Integer> getSubgrid(List<List<Integer>> partialAssignment, int startRow, int startCol, int endRow, int endCol) {
        System.out.println("Getting grid: startRow = "+startRow+" startCol = "+startCol+ " endRow = "+endRow+" endCol = "+endCol);
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> row = partialAssignment.subList(startRow, endRow);
        for (List<Integer> r : row) {
            res.addAll(r.subList(startCol, endCol));
        }
        return res;
    }

    private static boolean isValid(List<Integer> integers) {
        Map<Integer, Long> counting = integers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return counting.entrySet().stream().noneMatch(integerLongEntry -> (integerLongEntry.getKey() != 0 && integerLongEntry.getValue() > 1));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

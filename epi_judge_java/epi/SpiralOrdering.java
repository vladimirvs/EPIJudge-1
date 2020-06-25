package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpiralOrdering {
    @EpiTest(testDataFile = "spiral_ordering.tsv")

    public static List<Integer>
    matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        if (squareMatrix.isEmpty()) {
            return Collections.emptyList();
        }

        int size = squareMatrix.get(0).size();

        if (size == 1) {
            return Collections.singletonList(squareMatrix.get(0).get(0));
        }

        int[][] arr = new int[size][size];

        int r = 0;
        for (List<Integer> ro : squareMatrix) {
            arr[r++] = ro.stream().mapToInt(i -> i).toArray();
        }

        int col = 0;
        int row = 0;

        int rightLimit = size - 1;
        int lowerLimit = size - 1;
        int leftLimit = 0;
        int upperLimit = 1;

        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < size * 2) {
            //Right
            while (col < rightLimit) {
                result.add(arr[row][col]);
                col++;
            }
            rightLimit--;
            //Down
            while (row < lowerLimit) {
                result.add(arr[row][col]);
                row++;
            }
            lowerLimit--;
            // Left
            while (col >= leftLimit) {
                result.add(arr[row][col]);
                col--;
            }
            col++;
            leftLimit++;

            //Up
            row--; //Correction before up
            while (row > upperLimit) {
                result.add(arr[row][col]);
                row--;
            }
            upperLimit++;
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

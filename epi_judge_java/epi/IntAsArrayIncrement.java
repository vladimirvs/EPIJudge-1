package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> A) {
        //1. A. last element + 1
        int n = A.size() - 1;
        A.set(n, A.get(n) + 1);
        //2. While the current element is 10, we set it to 0 and increment the previous one
        for (int i = n; i > 0 && A.get(i) == 10; i--) {
            A.set(i, 0);
            A.set(i - 1, A.get(i - 1) + 1);
        }


        //3. If the 0th element is 10, then we need to add another element before
        if (A.get(0) == 10) {

            //A genius way is to just add a 0 to the end:
            // 9,9,9,9 -> 10,0,0,0 -> 1,0,0,0,[0]
            A.set(0, 1);
            A.add(0);
        }

        return A;
    }

    /**
     * Checks and increments only if not overflow.
     *
     * @param A
     * @param lastElementIndex
     * @return false if incremented and no overflow
     */
    private static boolean checkAndIncrement(List<Integer> A, int lastElementIndex) {
        if (A.get(lastElementIndex) < 9) {
            A.set(lastElementIndex, A.get(lastElementIndex) + 1);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

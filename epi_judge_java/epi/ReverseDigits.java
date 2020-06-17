package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {
    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {


        double sgn = Math.signum(x);
        if (x < 0) {
            x *= -1;
        }

        long res = 0;

        while (x > 0) {
            int lastDigit = x % 10;
            res = res * 10 + lastDigit;
            x /= 10;
        }

        return (long) (res * sgn);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {
    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        double res = 1.0d;

        if (y < 0) {
            while (y < 0) {
                res /= x;
                y++;
            }
        } else {

            while (y > 0) {
                res *= x;
                y--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //  double res = power(-1.0006612108596369,-2217);
        //double res = power(5, -3);
       // System.out.println(res);
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
    }
}

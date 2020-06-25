package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(long x) {
        System.out.println(x);
        boolean neg = x<0;


        if (x == 0) {
            return "0";
        }
        if (neg) {
            x= Math.abs(x);
        }
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            sb.append(x % 10);
            x /= 10;
        }
         System.out.println("IntToString: "+sb.toString());
        return neg ? "-" + sb.reverse().toString() : sb.reverse().toString();
        //return sb.reverse().toString();
    }

    public static long stringToInt(String s) {
        if (s==null || s.isEmpty()) {
            return 0;
        }
        int sgn = s.startsWith("-") ? -1 : 1;
        int i = 0;
        if (s.startsWith("+") || s.startsWith("-")) {
            i = 1;
        }
        char[] arr = s.toCharArray();
        long res = 0;
        while (i < arr.length) {
            res += (arr[i] - 48) * Math.pow(10, arr.length - 1 - i);
            i++;
        }
        System.out.println("Stringtoint: "+res);
        return res*sgn;
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (Integer.parseInt(intToString(x)) != x) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class ConvertBase {

    static Map<Integer, String> alpha = new HashMap<>();

    static {
        for (int k = 0; k < 10; k++) {
            alpha.put(k, String.valueOf(k));
        }
        alpha.put(10, "A");
        alpha.put(11, "B");
        alpha.put(12, "C");
        alpha.put(13, "D");
        alpha.put(14, "E");
        alpha.put(15, "F");
    }


    @EpiTest(testDataFile = "convert_base.tsv")


    public static String convertBase(String numAsString, int b1, int b2) {
        boolean neg = false;

        if (numAsString.equals("0")) {
            return "0";
        }

        //First convert to base 10
        long base10 = 0L;
        for (int i = 0; i < numAsString.length(); i++) {
            if (numAsString.charAt(i) == '-') {
                neg = true;
                continue;
            }

            int digit = Character.isDigit(numAsString.charAt(i)) ? numAsString.charAt(i) - '0' : numAsString.charAt(i) - 'A' + 10;
            base10 += digit * Math.pow(b1, numAsString.length() - 1 - i);
        }

        System.out.println(base10);

        //Then convert to b2
        StringBuilder sb = new StringBuilder();


        while (base10 > 0) {
            int digit = (int) (base10 % b2);
            sb.append(alpha.get(digit));
            base10 /= b2;
        }

        //System.out.println(sb.reverse());
        if (neg) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

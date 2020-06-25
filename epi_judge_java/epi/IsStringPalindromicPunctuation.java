package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromicPunctuation {
    @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

    public static boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {

            while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) {
                i++;

            }

            while (!Character.isLetterOrDigit(s.charAt(j)) && i < j) {
                j--;

            }

            char a = Character.toLowerCase(s.charAt(i++));
            char b = Character.toLowerCase(s.charAt(j--));

            if (a != b) {
                return false;
            }


        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeSieve {
    @EpiTest(testDataFile = "prime_sieve.tsv")
    // Given n, return all primes up to and including n.
    public static List<Integer> generatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();

        while (n > 1) {
            boolean prime = true;
            for (int i = n - 1; i > 1; i--) {
                if (n % i == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primes.add(n);
            }
            n--;
        }


        Collections.sort(primes);
        return primes;
    }

    public static void main(String[] args) {
        //  generatePrimes(3);
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());

    }
}

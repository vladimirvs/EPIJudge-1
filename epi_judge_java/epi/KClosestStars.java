package epi;

import epi.test_framework.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KClosestStars {
    @EpiTestExpectedType
    public static List<Double> expectedType;

    public static List<Star> findClosestKStars(Iterator<Star> stars, int k) {
        PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        while (stars.hasNext()) {
            Star star = stars.next();
            maxHeap.add(star);
            if (maxHeap.size() == k + 1) {
                maxHeap.remove();
            }
        }
        return Stream.generate(maxHeap::remove).limit(maxHeap.size()).collect(Collectors.toList());
    }

    @EpiTest(testDataFile = "k_closest_stars.tsv")
    public static List<Star> findClosestKStarsWrapper(List<Star> stars, int k) {
        return findClosestKStars(stars.iterator(), k);
    }

    @EpiTestComparator
    public static boolean comp(List<Double> expected, List<Star> result) {
        if (expected.size() != result.size()) {
            return false;
        }
        Collections.sort(result);
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).distance() != expected.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KClosestStars.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    @EpiUserType(ctorParams = {double.class, double.class, double.class})

    public static class Star implements Comparable<Star> {
        private final double x;
      private final double y;
      private final double z;

        public Star(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double distance() {
            return Math.sqrt(x * x + y * y + z * z);
        }

        @Override
        public int compareTo(Star that) {
            return Double.compare(this.distance(), that.distance());
        }

        @Override
        public String toString() {
            return String.valueOf(distance());
        }
    }
}

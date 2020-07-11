package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {
    public static List<Double> onlineMedian(Iterator<Integer> sequence) {

        List<Double> result = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(16);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(16, Collections.reverseOrder());

        while (sequence.hasNext()) {
            Integer next = sequence.next();

            minHeap.add(next);
            maxHeap.add(minHeap.remove());

            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.remove());
            }

            result.add((minHeap.size() == maxHeap.size()) ? 0.5 * (minHeap.peek() + maxHeap.peek()) : (double) minHeap.peek());

        }

        return result;
    }

    @EpiTest(testDataFile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
        return onlineMedian(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

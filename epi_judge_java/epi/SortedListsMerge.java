package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {
    @EpiTest(testDataFile = "sorted_lists_merge.tsv")
    //@include
    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                        ListNode<Integer> L2) {
        ListNode<Integer> dummyHead = new ListNode<>(0, null);
        ListNode<Integer> curent = dummyHead;

        while (L1 != null && L2 != null) {
            if (L1.data <= L2.data) {
                curent.next = L1;
                L1 = L1.next;
            } else {
                curent.next = L2;
                L2 = L2.next;
            }
            curent = curent.next;
        }

        curent.next = L1 != null ? L1 : L2;
        return dummyHead.next;

    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

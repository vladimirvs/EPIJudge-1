package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {
    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

    // Assumes L has at least k nodes, deletes the k-th last node in L.
    public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
        // TODO - you fill in here.

        ListNode<Integer> summyHead = new ListNode<>(0,L);

        ListNode<Integer> fast = summyHead.next;
        int i = 1;
        while (i <= k) {
            fast = fast.next;
            i++;
        }

        ListNode<Integer> slow = summyHead;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;


        return summyHead.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

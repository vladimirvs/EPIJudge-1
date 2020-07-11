package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")
    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        return tree == null || checkSymmetric(tree.left, tree.right);


    }

    private static boolean checkSymmetric(BinaryTreeNode<Integer> sub1, BinaryTreeNode<Integer> sub2) {
        if (sub1 == null && sub2 == null) {
            return true;
        } else if (sub1 != null && sub2 != null) {
            return sub1.data.equals(sub2.data) && checkSymmetric(sub1.left, sub2.right) && checkSymmetric(sub1.right, sub2.left);
        }
        return false;

    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

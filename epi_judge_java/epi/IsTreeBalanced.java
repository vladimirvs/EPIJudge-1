package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;


public class IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return checkBalanced(tree).balanced;

    }

    private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return new BalanceStatusWithHeight(true, -1);
        }

        BalanceStatusWithHeight leftRes = checkBalanced(tree.left);
        if (!leftRes.balanced) {
            return leftRes;
        }
        BalanceStatusWithHeight rightRes = checkBalanced(tree.right);
        if (!rightRes.balanced) {
            return rightRes;
        }
        boolean isBalanced = Math.abs(leftRes.height - rightRes.height) <= 1;
        int height = Math.max(leftRes.height, rightRes.height) + 1;
        return new BalanceStatusWithHeight(isBalanced, height);
    }

    private static int getHeight(BinaryTreeNode<Integer> node) {
        if (node == null || node.left == null && node.right == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    private static class BalanceStatusWithHeight {
        boolean balanced;
        int height;

        public BalanceStatusWithHeight(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }
}

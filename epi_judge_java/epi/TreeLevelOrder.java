package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TreeLevelOrder {
    @EpiTest(testDataFile = "tree_level_order.tsv")

    public static List<List<Integer>>
    binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        List<List<Integer>> res = new ArrayList<>();
        if (tree == null) {
          return Collections.emptyList();
        }


        List<BinaryTreeNode<Integer>> currentDepthNodes = Arrays.asList(tree);
        while (!currentDepthNodes.isEmpty()) {
            res.add(currentDepthNodes.stream().map(curr -> curr.data).collect(Collectors.toList()));
            currentDepthNodes = currentDepthNodes.stream().map(curr -> Arrays.asList(curr.left, curr.right)).flatMap(List::stream).filter(child -> child != null).collect(Collectors.toList());
        }


        return res;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

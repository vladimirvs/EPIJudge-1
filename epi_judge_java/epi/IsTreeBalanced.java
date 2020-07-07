package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {
  static boolean isBalanced = false;
  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
      getHeight(tree.left);
    int leftH = height;
     getHeight(tree.right);
    int rightH = height;
      return Math.abs(leftH - rightH) <= 1;

  }

  static int height = 0;
  private static int getHeight(BinaryTreeNode<Integer> node) {
      if (node.left==null && node.right == null) {
        return 0;
      }

      if (node.left!=null) {
        height++;
        return getHeight(node.left);
      }
      if (node.right!=null) {
        height++;
        return getHeight(node.right);
      }


    return height;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

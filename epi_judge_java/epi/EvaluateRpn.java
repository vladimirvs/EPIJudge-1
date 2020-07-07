package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntBiFunction;

public class EvaluateRpn {
    @EpiTest(testDataFile = "evaluate_rpn.tsv")

    public static int eval(String expression) {
        Deque<Integer> stack = new ArrayDeque<>();
        final String DELIMITER = ",";
        final Map<String, ToIntBiFunction<Integer, Integer>> OPERATORS = new HashMap<>() {
            {
                put("+", (a, b) -> b + a);
                put("-", (a, b) -> b - a);
                put("*", (a, b) -> b * a);
                put("/", (a, b) -> b / a);
            }
        };






        for (String token : expression.split(DELIMITER)) {
            if (OPERATORS.get(token) != null) {
                stack.addFirst(OPERATORS.get(token).applyAsInt(stack.removeFirst(), stack.removeFirst()));
            } else {
                stack.addFirst(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

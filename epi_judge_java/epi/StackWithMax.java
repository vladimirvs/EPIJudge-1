package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class StackWithMax {

    @EpiTest(testDataFile = "stack_with_max.tsv")
    public static void stackTester(List<StackOp> ops) throws TestFailure {
        try {
            Stack s = new Stack();
            int result;
            for (StackOp op : ops) {
                switch (op.op) {
                    case "Stack":
                        s = new Stack();
                        break;
                    case "push":
                        s.push(op.arg);
                        break;
                    case "pop":
                        result = s.pop();
                        if (result != op.arg) {
                            throw new TestFailure("Pop: expected " + op.arg +
                                    ", got " + result);
                        }
                        break;
                    case "max":
                        result = s.max();
                        if (result != op.arg) {
                            throw new TestFailure("Max: expected " + op.arg +
                                    ", got " + result);
                        }
                        break;
                    case "empty":
                        result = s.empty() ? 1 : 0;
                        if (result != op.arg) {
                            throw new TestFailure("Empty: expected " + op.arg +
                                    ", got " + s);
                        }
                        break;
                    default:
                        throw new RuntimeException("Unsupported stack operation: " + op.op);
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailure("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    private static class ElementWithMax {
        Integer element;
        Integer max;

        public ElementWithMax(Integer element, Integer max) {
            this.element = element;
            this.max = max;
        }
    }

    public static class Stack {

        Deque<ElementWithMax> elements = new ArrayDeque<>();

        public boolean empty() {
            return elements.isEmpty();
        }

        public Integer max() {
            if (empty()) {
                throw new IllegalStateException("Empty stack");
            }
            return elements.peek().max;
        }

        public Integer pop() {
            if (empty()) {
                throw new IllegalStateException("Empty stack");
            }

            return elements.pop().element;
        }

        public void push(Integer x) {
            elements.push(new ElementWithMax(x, Math.max(x, empty() ? x : max())));

            return;
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class StackOp {
        public String op;
        public int arg;

        public StackOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }
}

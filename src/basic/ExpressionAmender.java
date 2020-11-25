package basic;


import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author muhossain
 * @since 2020-07-09
 */

// Given an expression (A+B-C)-D)-E), remove minimum number of expression to make it a valid expression

public class ExpressionAmender {
    public static void main(String[] args) {
        String expression = "((A+B-C)-D)-E(M+K)N";

        Stack<Parenthesis> parenthesisStack = new Stack<>();
        List<Integer> removedIndex = new ArrayList();

        for (int i = 0; i < expression.length(); i++) {
            if ((expression.charAt(i) == '(')) {
                parenthesisStack.add(new Parenthesis(expression.charAt(i), i));
            }

            if (expression.charAt(i) == ')') {
                Parenthesis p = null;

                if (!parenthesisStack.isEmpty()) {
                    p = parenthesisStack.peek();
                }

                if (p != null && p.parenthesis == '(') {
                    parenthesisStack.pop();
                } else {
                    removedIndex.add(i);
                }
            }
        }

        List<Integer> indexesToRemove = new ArrayList<>();

        while (!parenthesisStack.isEmpty()) {
            Parenthesis parenthesis = parenthesisStack.pop();
            indexesToRemove.add(parenthesis.idx);
        }

        indexesToRemove.addAll(removedIndex);

        Collections.sort(indexesToRemove);

        StringBuilder result = new StringBuilder();

        int j = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (j < indexesToRemove.size() && indexesToRemove.get(j) == i) {
                j++;
                continue;
            }

            result.append(expression.charAt(i));
        }

        System.out.println(result.toString());
    }

    private static class Parenthesis {
        char parenthesis;
        int idx;

        public Parenthesis(char parenthesis, int idx) {
            this.parenthesis = parenthesis;
            this.idx = idx;
        }
    }
}

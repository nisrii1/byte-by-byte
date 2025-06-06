import java.util.*;

class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] suffixMin = new int[n + 1];
        suffixMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffixMin[i] = Math.min(s.charAt(i), suffixMin[i + 1]);
        }

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            stack.push(s.charAt(i));
            while (!stack.isEmpty() && stack.peek() <= (char) suffixMin[i + 1]) {
                result.append(stack.pop());
            }
        }

        return result.toString();
    }
}

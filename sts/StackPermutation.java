import java.util.Stack;

public class StackPermutation {

    static boolean isStackPermutation(int[] input, int[] output) {

        Stack<Integer> stack = new Stack<>();
        int j = 0;

        for (int i = 0; i < input.length; i++) {

            stack.push(input[i]);

            while (!stack.isEmpty() && stack.peek() == output[j]) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        int[] input = {1, 2, 3};
        int[] output = {2, 1, 3};

        if (isStackPermutation(input, output)) {
            System.out.println("Valid Stack Permutation");
        } else {
            System.out.println("Invalid Stack Permutation");
        }
    }
}

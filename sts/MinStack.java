import java.util.Stack;

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }

    }

    public void pop() {
        if (!minStack.isEmpty() && minStack.peek() == stack.pop()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {

        return minStack.peek();
    }

    public static void main(String[] args) {

        MinStack ms = new MinStack();
        ms.push(5);
        ms.push(3);
        ms.push(3);
        ms.push(7);

        System.out.println(ms.getMin()); // 3
        ms.pop();
        ms.pop();
        System.out.println(ms.getMin()); // 3

    }
}

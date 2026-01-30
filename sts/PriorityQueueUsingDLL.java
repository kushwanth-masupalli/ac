class PriorityQueueUsingDLL {

    static class Node {
        int data;
        int priority;
        Node prev, next;

        Node(int data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    static Node front = null;
    static Node rear = null;

    // push / enqueue
    static void push(int data, int priority) {

        Node newNode = new Node(data, priority);

        // Case 1: empty list
        if (front == null) {
            front = rear = newNode;
        }

        // Case 2: insert at front (higher priority = smaller number)
        else if (priority <= front.priority) {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }

        // Case 3: insert at rear
        else if (priority > rear.priority) {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }

        // Case 4: insert in middle
        else {
            Node temp = front;

            while (temp.next != null && temp.next.priority <= priority) {
                temp = temp.next;
            }

            newNode.next = temp.next;
            newNode.prev = temp;
            temp.next.prev = newNode;
            temp.next = newNode;
        }
    }

    // pop / dequeue (remove highest priority)
    static int pop() {
        if (front == null) {
            return -1;
        }

        int val = front.data;
        front = front.next;

        if (front != null)
            front.prev = null;
        else
            rear = null;

        return val;
    }

    // peek
    static int peek() {
        if (front == null)
            return -1;

        return front.data;
    }

    // main method
    public static void main(String[] args) {

        push(10, 3);
        push(20, 1);
        push(30, 2);

        System.out.println(peek()); // 20
        System.out.println(pop());  // 20
        System.out.println(pop());  // 30
    }
}

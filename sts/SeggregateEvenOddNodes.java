
public class SeggregateEvenOddNodes {

   static class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}


    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head = segregateEvenOdd(head);
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
           
        }
    }

    private static Node segregateEvenOdd(Node head) {

        if (head == null)
            return null;

        Node evenDummy = new Node(0);
        Node oddDummy = new Node(0);

        Node ep = evenDummy;
        Node op = oddDummy;

        Node temp = head;

        while (temp != null) {
            if (temp.data % 2 == 0) {
                ep.next = temp;
                ep = ep.next;
            } else {
                op.next = temp;
                op = op.next;
            }
            temp = temp.next;
        }

        op.next = null;

        ep.next = oddDummy.next;

        return evenDummy.next;
    }

}

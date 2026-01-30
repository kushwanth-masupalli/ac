

public class LoopDetection {
  static   class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}
    public static void main(String[] args) {

        // Create nodes
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);

        // Link nodes
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;

        // Create cycle (10 → 4)
        n10.next = n4;

        Node head = n1;

        System.out.println(findCycle(head));
    }

    private static boolean findCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
        
    }

}
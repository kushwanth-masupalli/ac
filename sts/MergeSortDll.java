public class MergeSortDll {
    static class Node{
        int data;
        Node prev,next;

        Node(int data){
            this.data = data;
            prev = next = null;
        }
    }


    static Node mergeSort(Node head){
        if(head==null || head.next==null) return head;

        Node second = split(head);

        head = mergeSort(head);
        second = mergeSort(second);

        return merge(head,second);
    }


    static Node split(Node head){
        Node slow = head,fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = slow.next;
        slow.next = null;

        if(second!=null){
            second.prev = null;
        }
        return second;
    }

    static Node merge(Node first,Node second){
        if(first==null) return second;
        if(second==null) return first;

        if(first.data<=second.data){
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        }
        else{
            second.next = merge(first,second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }

    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {

        // Creating DLL: 4 ⇄ 2 ⇄ 5 ⇄ 1 ⇄ 3
        Node head = new Node(4);
        head.next = new Node(2);
        head.next.prev = head;

        head.next.next = new Node(5);
        head.next.next.prev = head.next;

        head.next.next.next = new Node(1);
        head.next.next.next.prev = head.next.next;

        head.next.next.next.next = new Node(3);
        head.next.next.next.next.prev = head.next.next.next;

        System.out.println("Before Sorting:");
        printList(head);

        head = mergeSort(head);

        System.out.println("After Sorting:");
        printList(head);
    }
}
 
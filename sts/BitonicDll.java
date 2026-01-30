public class BitonicDll{
     static class Node{
        int data;
        Node prev,next;

        Node(int data){
            this.data = data;
            prev = next = null;
        }
     }

     static Node sortBitonic(Node head){
         if(head==null || head.next == null){
             return head;
         }

         Node curr = head;
         while(curr.next!=null && curr.data<=curr.next.data){
            curr = curr.next;
         }

         Node second = curr.next;
         curr.next = null;

         if(second!=null){
            second.prev = null;
         }

         second = reverse(second);

         return merge(head,second);
     }

     static Node reverse(Node head){
         Node curr =head,temp =null;

         while(curr!=null){
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
         }
        if(temp!=null){
            head = temp.prev;
        }
        return head;
     }


     static Node merge(Node first,Node second){
        if(first==null) return second;
        if(second ==null) return first;

        if(first.data <=second.data){
            first.next = merge(first.next, second);
            if(first.next!=null){
                first.next.prev = first;
            }
            first.prev = null;
            return first;
        }
        else{
            second.next = merge(first, second.next);
            if(second.next!=null)
                second.next.prev = second;
            second.prev = null;
            return second;
        }

     }static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {

        // Bitonic DLL: 1 ⇄ 3 ⇄ 6 ⇄ 9 ⇄ 8 ⇄ 4 ⇄ 2
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.prev = head;

        head.next.next = new Node(6);
        head.next.next.prev = head.next;

        head.next.next.next = new Node(9);
        head.next.next.next.prev = head.next.next;

        head.next.next.next.next = new Node(8);
        head.next.next.next.next.prev = head.next.next.next;

        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.prev = head.next.next.next.next;

        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.prev =
                head.next.next.next.next.next;

        System.out.println("Before Sorting:");
        printList(head);

        head = sortBitonic(head);

        System.out.println("After Sorting:");
        printList(head);
    }
}
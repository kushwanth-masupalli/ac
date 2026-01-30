import java.util.*;
public class TakingInputDll {

    static class Node{
        int data;
        Node prev,next;
        Node(int data){
            this.data = data;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node head = new Node(sc.nextInt());
        Node curr = head;
        Node tail = head;
        for(int i=1;i<n;i++){
            tail.next = new Node(sc.nextInt());
            tail.next.prev = tail;
            tail = tail.next;
        }

        while(curr!=null){
            System.out.println(curr.data);
            curr = curr.next;
        }
        sc.close();
    }
}

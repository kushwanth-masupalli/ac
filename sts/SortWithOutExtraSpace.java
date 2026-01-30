import java.util.*;

class SortWithoutExtraSpace {

    static void sortQueue(Queue<Integer> q) {
        int n = q.size();
        for(int i =0;i<n;i++){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int j=0;j<n;j++){
                int curr = q.poll();
                if(curr<min && j<n-i){
                    min = curr;
                    minIndex = j;
                }
                q.add(curr);
            }

            for(int j=0;j<n;j++){
                int curr = q.poll();
                if(j!=minIndex){
                    q.add(curr);
                }
            }
            q.add(min);


        }
    }

    public static void main(String[] args) {

        Queue<Integer> q = new LinkedList<>();

        q.add(10);
        q.add(7);
        q.add(2);
        q.add(8);
        q.add(6);

        sortQueue(q);

        System.out.println(q);
    }
}

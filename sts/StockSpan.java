import java.util.*;
public class StockSpan {

    static int[] calculateSpan(int[] price, int n) {
         
        int [] span = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i =0;i<n;i++){
            while(!st.isEmpty() && price[st.peek()]<=price[i]){
                st.pop();
            }

            span[i] = st.isEmpty() ?(i+1) : i-st.peek();
            st.push(i);

        }
        return span;

    }

    public static void main(String[] args) {

        int[] price = {100, 80, 60, 70, 60, 75, 85};
        int n = price.length;

        int[] span = calculateSpan(price, n);

        for (int i = 0; i < n; i++) {
            System.out.print(span[i] + " ");
        }
    }
}

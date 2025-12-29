import java.util.*;
public class MinStack{
    public static void main(){
        Stack<Integer> st  = new Stack<>();
        st.push(-1);
        Stack<Integer> mst = new Stack<>();
        mst.push(-1);
        
        System.out.println(st.peek() == mst.peek());
        


    }


}
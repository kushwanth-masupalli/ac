public class Celebrity {

    static int findCelebrity(int[][] knowsMatrix, int n) {
        for(int i =0;i<n;i++){
             
            boolean flag = true;
            
            for(int j=0;j<n;j++){
                if(i!=j && knowsMatrix[i][j]==1){
                    flag = false;
                    break;
                }
            }

            if(!flag) continue;

            for(int j =0;j<n;j++){
                if(i!=j && knowsMatrix[j][i]==0){
                    flag = false;
                    break;
                }
            }
            if(flag) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] knowsMatrix = {
                { 0, 1, 0 },
                { 0, 0, 0 },
                { 0, 1, 0 }
        };

        int n = knowsMatrix.length;

        int celebrity = findCelebrity(knowsMatrix, n);

        if (celebrity == -1) {
            System.out.println("No Celebrity");
        } else {
            System.out.println("Celebrity is person: " + celebrity);
        }
    }
}

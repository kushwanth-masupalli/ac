public class TowerOfHanoi {

    static void solveHanoi(int n, char s, char a, char d) {
        if (n == 1) {
            System.out.println("move the disk from " + s + " to " + d);
            return;
        }

        solveHanoi(n - 1, s, d, a);
        System.out.println("move the disk from " + s + " to " + d);
        solveHanoi(n - 1, a, s, d);
    }

    public static void main(String[] args) {

        int n = 3;

        solveHanoi(n, 'A', 'B', 'C');
    }
}

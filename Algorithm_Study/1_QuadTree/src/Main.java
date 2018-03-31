import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        boolean[][] matrix = new boolean[n][n];

        MatrixManager matrixManager = new MatrixManager();
        StringBuilder sb = new StringBuilder();

        matrixManager.initMatrix(matrix,n);
        matrixManager.compress(matrix,sb);

        System.out.println(sb.toString());
 
    }


}

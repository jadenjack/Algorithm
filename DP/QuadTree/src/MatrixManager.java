import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MatrixManager {

    void initMatrix(boolean[][] matrix, int order){

        Scanner sc = new Scanner(System.in);
        String nextInput;
        for(int i=0;i<order;i++){
            nextInput = sc.nextLine();
            for(int j=0;j<order;j++){

                if(nextInput.charAt(j) =='0') {
                    matrix[i][j] = FALSE;
                } else
                    matrix[i][j] = TRUE;
            }
        }

    }

    void compress(boolean[][] matrix,StringBuilder sb){

        boolean result = matrix[0][0];
        boolean XOR = false;
//        sb.append("(");

        for(int i=0;i<matrix.length-1;i++){
            for(int j=0;j<matrix.length;j++){
                if(i==0&&j==0)
                    continue;

                 XOR = result^matrix[i][j];

                //different
                if(XOR==TRUE){
                    int middlePoint = matrix.length/2;
                    sb.append("(");
                    compress(subMatrix(matrix,0,0,middlePoint-1,middlePoint-1),sb);
                    compress(subMatrix(matrix,0,middlePoint,middlePoint-1,matrix.length-1),sb);
                    compress(subMatrix(matrix,middlePoint,0,matrix.length-1,middlePoint-1),sb);
                    compress(subMatrix(matrix,middlePoint,middlePoint,matrix.length-1,matrix.length-1),sb);
                    if(i==0)
                        i=4;
                    else
                        i*=4;
                    sb.append(")");
                    return;
                }else{

                }
            }

        }

        result = XOR;

        if(matrix[0][0]==true)
            sb.append("1");
        else
            sb.append("0");


//        sb.append(")");
        return;

    }

    boolean[][] subMatrix(boolean[][] matrix, int startX, int startY, int endX, int endY){

        boolean[][] sub = new boolean[matrix.length/2][matrix.length/2];
        for(int i=startX;i<=endX;i++){
            for(int j=startY;j<=endY;j++){
                sub[i-startX][j-startY] = matrix[i][j];
            }
        }

        return sub;
    }

    void printMatrix(boolean[][] matrix){

        int order = matrix.length;
        for(int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

    }
}

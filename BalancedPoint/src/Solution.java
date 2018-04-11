import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {

        int test_case;
        int test;
        int n;
        double[] arr_x;
        double[] arr_m;
        double result;
        Solution solution = new Solution();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String rawString = "";

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            test = Integer.parseInt(br.readLine());

            for (test_case = 1; test_case <= test; test_case++) {

                n = Integer.parseInt(br.readLine());
                rawString = br.readLine();
                arr_x = new double[n];
                arr_m = new double[n];
                st = new StringTokenizer(rawString, " ");

                for (int i = 0; i < n; i++)
                    arr_x[i] = Double.parseDouble(st.nextToken());
                for (int i = 0; i < n; i++)
                    arr_m[i] = Double.parseDouble(st.nextToken());

                sb.append("#");
                sb.append(test_case);
                sb.append(" ");
                for(int i=0;i<n-1;i++){
                    double midX = (arr_x[i] + arr_x[i+1])/2;

                    result = solution.binarySearch(midX,arr_x[i],arr_x[i+1],arr_x[i],arr_x[i+1],arr_m[i],arr_m[i+1]);
                    sb.append(String.format("%.10f",result));
                    sb.append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public double binarySearch(double x, double left, double right, double x1, double x2, double mess1, double mess2){


        double leftPower = formular(x1,x,mess1,1);
        double rightPower = formular(x,x2,1,mess2);
        if(Math.abs(leftPower-rightPower)<Math.pow(10,-9))
        //if(leftPower == rightPower)
            return x;
        else if(leftPower > rightPower){

            left = x;
            x = (right - left)/2 + left;
            return binarySearch(x,left,right, x1, x2, mess1, mess2);
        }else {
            right = x;
            x = (right-left)/2 + left;
            return binarySearch(x,left,right, x1, x2, mess1, mess2);
        }

    }

    public double formular(double x1, double x2, double mess1, double mess2) {
        double distance = x2-x1;
        return (double) mess1 * mess2 / (distance * distance);
    }


}


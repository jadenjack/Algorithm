import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//https://swexpertacademy.com/main/solvingProblem/solvingProblem.do

public class Main {

    static int[][] distance;
    static LinkedList<Integer> path = new LinkedList<>();
    static int minimum;

    public static void main(String[] args) {


        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int[] x;
        int[] y;
        StringBuilder sb = new StringBuilder();
        String rawString;
        StringTokenizer st;
        Main main = new Main();
        int result = 0;

        boolean[] isTraveled;
        try {
            int test_case = Integer.parseInt(br.readLine());


            for(int test= 1;test<=test_case;test++){
                minimum = 10000000;
                int n = Integer.parseInt(br.readLine());
                rawString = br.readLine();
                st = new StringTokenizer(rawString," ");

                x = new int[n+2];
                y = new int[n+2];
                distance = new int[n+2][n+2];
                isTraveled = new boolean[n+2];

                for(int i=0;i<n;i++){
                    isTraveled[i]=false;
                }

                for(int i=0;i<n+2;i++){
                    x[i] = Integer.parseInt(st.nextToken());
                    y[i] = Integer.parseInt(st.nextToken());
                }
                for(int i=0;i<n+2;i++){
                    for(int j=i;j<n+2;j++) {
                        distance[i][j] = distance[j][i] = main.getDistance(x[i],y[i],x[j],y[j]);
                    }
                }

                main.spread(n,isTraveled,result);
                sb.append("#");
                sb.append(test);
                sb.append(" ");
                sb.append(minimum);
                if(test!=test_case)
                    sb.append("\n");

            }
            System.out.println(sb.toString());


        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public int getDistance (int x1, int y1, int x2, int y2){
        return Math.abs(x2-x1) + Math.abs(y2-y1);
    }

    public void spread(int n, boolean[] isTraveled, int result){

        for(int i=2;i<n+2;i++){

            if(isTraveled[i]==false){
                isTraveled[i]=true;
                path.addLast(i);
                spread(n,isTraveled,result);
                isTraveled[i]=false;
                if(path.size()==n){
                    //company to first place
                    int d = distance[0][path.get(0)];

                    //first place to last place
                    for(int j=0;j<path.size()-1;j++){
                        //System.out.print(path.get(j));
                        d += distance[path.get(j)][path.get(j+1)];
                    }
                    //last place to home
                    d+=distance[1][path.getLast()];
                    if(d<minimum)
                        minimum=d;
                }
                path.removeLast();

            }
        }

    }

}

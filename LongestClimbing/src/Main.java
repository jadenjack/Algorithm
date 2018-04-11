import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int longestTravelTime = -1;
    public static int[][] map;

    //lef,t right, top, bottom;
    public static int[] dx = new int[]{-1,1,0,0};
    public static int[] dy = new int[]{0,0,-1,1};
    private static int n;
    private static int k;

    public static void main(String[] args) {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String rawString;
        StringTokenizer st;
        int[][] map;
        Main main = new Main();
        int test_case;

        try {
            //init
            test_case = Integer.parseInt(br.readLine());
            for(int test=1;test<=test_case;test++) {
                longestTravelTime=-1;
                rawString = br.readLine();
                st = new StringTokenizer(rawString, " ");
                n = Integer.parseInt(st.nextToken());
                k = Integer.parseInt(st.nextToken());
                map = new int[n][n];
                boolean[][] isChecked;
                isChecked = new boolean[n][n];
                for(int i=0;i<isChecked.length;i++)
                    Arrays.fill(isChecked[i],false);

                //make map
                for (int i = 0; i < n; i++) {
                    rawString = br.readLine();
                    st = new StringTokenizer(rawString, " ");
                    for (int j = 0; j < n; j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                int max = main.getMax(map);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] == max) {
                            int length = 0;
                            isChecked[i][j] = true;
                            main.climb(map,max,i,j,isChecked,length,false);
                            isChecked[i][j] = false;
                        }
                    }
                }
                System.out.println("#" + test +" " + (longestTravelTime+1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void climb(int[][] map, int h, int row, int col, boolean[][] isChecked, int length, boolean cut){

        for(int i=0;i<4;i++){//left,right,top,bottom
            int rrow = row + dy[i];
            int rcol = col + dx[i];
            if(rrow<0 || rcol<0 || rrow>=n || rcol>=n)// out of map
                continue;
            if(isChecked[rrow][rcol]==true)// already traveled
                continue;

            int rh = map[rrow][rcol];
            if(rh<h){
                isChecked[rrow][rcol] = true;
                climb(map,rh,rrow,rcol,isChecked,length+1,cut);
                isChecked[rrow][rcol] = false;
            } else{
                if(cut==true)
                    continue;
                if(rh-h<k){
                    isChecked[rrow][rcol] = true;
                    climb(map, h-1, rrow, rcol, isChecked, length + 1, true);
                    isChecked[rrow][rcol] = false;
                    if(rh-k<=0)
                        break;
                }
            }

        }
        longestTravelTime = Math.max(longestTravelTime,length);

    }

    public int getMax(int[][] map) {
        int max = -1;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] > max) {
                    max = map[row][col];
                }
            }
        }
        return max;
    }
}

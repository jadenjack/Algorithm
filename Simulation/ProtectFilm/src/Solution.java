import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    //https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu

    public static boolean[][] map;
    public static int[][] record;
    public static int rn;// d
    public static int cn;// w
    public static int k; // k
    public static int answer;
    public static final int SAME = 2;
    public static final int BEFORE_1 = 1;
    public static final int BEFORE_0 = 0;

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int tn = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int t = 1; t <= tn; t++) {
                init(br);
                answer = k+1;
                solve(map,record,k);
                makeResultString(sb,t,answer);
                if(t!=tn)
                    sb.append("\n");
            }
            System.out.println(sb.toString());
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void makeResultString(StringBuilder sb, int t, int answer){
        sb.append("#");
        sb.append(t);
        sb.append(" ");
        sb.append(answer);
    }

    public static void solve(boolean[][] map, int[][] record, int testK){



        for(int i=0;i<=testK;i++){//chance to take medicine
            takeRecurse(map,record,i,0,0);
            if(answer<=k)
                return;
        }

    }

    public static void takeRecurse(boolean[][] map, int[][] record, int testK, int recurseTime, int startIndex){

        if(isSuccess(map)) {
            answer = testK;
            return ;
        }

        if(answer<=k)
            return;

        for(int i=startIndex;i<rn;i++){
            if(testK<=recurseTime)
                return;

            for(int t=0;t<2;t++) {
                takeMedicine(map,i,t);
                takeRecurse(map, record, testK, recurseTime + 1, i + 1);
                rollbackMap(map,record,i);
            }
        }
    }

    public static void printMap(){
        for(int i=0;i<rn;i++){
            for(int j=0;j<cn;j++){
                if(map[i][j] == false)
                    System.out.print(0);
                else
                    System.out.print(1);
            }
            System.out.println("");
        }
    }

    public static void takeMedicine(boolean[][] map, int row, int medicine){
        if(medicine==0)
            takeMedicine(map,row,false);
        else
            takeMedicine(map,row,true);
    }
    public static void rollbackMap(boolean[][] map, int[][] record, int row){
        for(int j=0;j<cn;j++){
            if(record[row][j]==BEFORE_0)
                map[row][j] = false;
            else if(record[row][j] == BEFORE_1)
                map[row][j] = true;

            record[row][j] = SAME;
        }
    }
    public static void takeMedicine(boolean[][] map , int row, boolean medicine){
        for(int i=0;i<cn;i++) {
            if(map[row][i] == medicine)
                record[row][i] = SAME;
            else{
                if(medicine==true&&map[row][i]==false)
                    record[row][i] = BEFORE_0;
                else if(medicine==false&&map[row][i]==true)
                    record[row][i] = BEFORE_1;
                else
                    record[row][i] = SAME;
            }

            map[row][i] = medicine;
        }
    }

    public static boolean isSuccess(boolean[][] map){
        int current_m;
        int count;

        for(int i=0;i<cn;i++){
            current_m = -1;
            count = 0;
            for(int j=0;j<rn;j++){
                int b = booleanToInt(map[j][i]);
                if(b!=current_m){//other number
                    current_m = b;
                    count = 1;
                }else{//same number
                    count++;
                    if(count>=k)
                        break;
                }
            }
            //end check column
            if(count<k)
                return false;
        }
        return true;
    }

    public static int booleanToInt(boolean b){
        if(b==true)
            return 1;
        return 0;
    }

    public static void init(BufferedReader br) throws  IOException{
        String rawString;
        StringTokenizer st;

        rawString = br.readLine();
        st = new StringTokenizer(rawString," ");
        rn = Integer.parseInt(st.nextToken());
        cn = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean[rn][cn];
        record = new int[rn][cn];

        for(int i=0;i<rn;i++){
            rawString = br.readLine();
            st = new StringTokenizer(rawString, " ");
            for(int j=0;j<cn;j++){
                if(Integer.parseInt(st.nextToken())==0)
                    map[i][j] = false;
                else
                    map[i][j] = true;

                record[i][j] = 0;
            }
        }



    }
}

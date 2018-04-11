import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq

public class Solution {

    public static boolean[][] type = new boolean[][]{
            {false, false, false, false},
            {true, true, true, true},
            {true, false, true, false},
            {false, true, false, true},
            {true, true, false, false},
            {false, true, true, false},
            {false, false, true, true},
            {true, false, false, true}
    };

    public static final int MAX_DIRECTION = 4;

    public static int[][] map;
    public static boolean[][] checked;
    public static StringTokenizer st;
    public static int MAX_ROW;
    public static int MAX_COL;
    public static int START_ROW;
    public static int START_COL;
    public static int TIMES;

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int tn = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int t = 1; t <= tn; t++) {

                st = getStringTokens(br);
                createMap(br, st);
                solve(START_ROW, START_COL, 1, Integer.MAX_VALUE);
                printCount(sb, t, countChekced());
                //printMap();
                if (t != tn)
                    sb.append("\n");

            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printMap() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (checked[i][j] == true)
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }

    public static void printCount(StringBuilder sb, int t, int count) {
        sb.append("#");
        sb.append(t);
        sb.append(" ");
        sb.append(count);
    }

    public static int countChekced() {
        int count = 0;
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (checked[i][j] == true)
                    count++;
            }
        }
        return count;
    }

    public static void solve(int row, int col, int time, int beforeDirection) {
        checked[row][col] = true;

        if (time >= TIMES) {
            return;
        }
        int structType = map[row][col];
        boolean[] direction = type[structType];
        for (int i = 0; i < MAX_DIRECTION; i++) {
            if (direction[i] == false)
                continue;

            int drow;
            int dcol;
            int d = -1;

            switch (i) {
                case 0:
                    drow = row - 1;
                    dcol = col;
                    d = 2;
                    break;//go up
                case 1:
                    drow = row;
                    dcol = col + 1;
                    d = 3;
                    break;//go right
                case 2:
                    drow = row + 1;
                    dcol = col;
                    d = 0;
                    break;//go down
                case 3:
                    drow = row;
                    dcol = col - 1;
                    d = 1;
                    break;//go left
                default:
                    return;
            }

            //checking that next direction same as before direction
            if (beforeDirection == 0 && d == 2) continue;
            if (beforeDirection == 1 && d == 3) continue;
            if (beforeDirection == 2 && d == 0) continue;
            if (beforeDirection == 3 && d == 1) continue;


            if (drow < 0 || dcol < 0 || drow >= MAX_ROW || dcol >= MAX_COL)
                continue;

            int nextType = map[drow][dcol];
            if (nextType == 0)
                continue;
            if (type[nextType][d] == false)
                continue;
            solve(drow, dcol, time + 1, d);
        }

    }

    public static StringTokenizer getStringTokens(BufferedReader br) throws IOException {
        String rawString = br.readLine();
        return new StringTokenizer(rawString, " ");
    }


    public static void createMap(BufferedReader br, StringTokenizer st) throws IOException {
        MAX_ROW = Integer.parseInt(st.nextToken());//get row num
        MAX_COL = Integer.parseInt(st.nextToken());//get col num
        START_ROW = Integer.parseInt(st.nextToken());
        START_COL = Integer.parseInt(st.nextToken());
        TIMES = Integer.parseInt(st.nextToken());
        map = new int[MAX_ROW][MAX_COL];
        checked = new boolean[MAX_ROW][MAX_COL];

        for (int i = 0; i < MAX_ROW; i++) {
            st = getStringTokens(br);
            for (int j = 0; j < MAX_COL; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                checked[i][j] = false;
            }
        }
    }
}

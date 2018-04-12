import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static final int MAX_DIRECTION = 4;
    public static final int MAX_EAT = 101;
    //                       ↗  ↘  ↙  ↖
    public static int[] dr = {-1, 1, 1, -1};
    public static int[] dc = {1, 1, -1, -1};
    public static boolean[] direction_check;
    public static boolean[] eat;
    public static int[][] map;
    public static int n;
    public static StringTokenizer st;
    public static int answer;


    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int tn = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            direction_check = new boolean[MAX_DIRECTION];
            for (int t = 1; t <= tn; t++) {
                answer = -1;
                n = Integer.parseInt(br.readLine());
                initMap(br);

                solve();
                printAnswer(sb, t, answer);
                if (t != tn)
                    sb.append("\n");

            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printAnswer(StringBuilder sb, int t, int answer) {
        sb.append("#");
        sb.append(t);
        sb.append(" ");
        sb.append(answer);
    }

    public static int solve() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (isEdge(i, j))//if position is on edge, then can travel nowhere
                    continue;
                initDirectionCheck();
                travel(i, j, i, j, 0, -1, 0);

            }
        }
        return 0;
    }

    public static void travel(int startR, int startC, int r, int c, int depth, int currentDirection, int directionDegree) {

        if (isEdge(r, c))
            return;
        if (!canGo(r, c))
            return;
        int food = map[r][c];
        if (depth > 2 && startR == r && startC == c) {
            answer = Math.max(depth, answer);
            return;
        }
        if (eat[food] == true)
            return;

        eat[food] = true;

        int nextR;
        int nextC;

        if (directionDegree == 0) {
            if (currentDirection == -1) {
                for (int i = 0; i < MAX_DIRECTION; i++) {
                    nextR = r + dr[i];
                    nextC = c + dc[i];

                    if (!canGo(nextR, nextC))
                        continue;

                    travel(startR, startC, nextR, nextC, depth + 1, i, 0);
                }
            } else {

                nextR = r + dr[currentDirection];
                nextC = c + dc[currentDirection];
                if (canGo(nextR, nextC)) {
                    travel(startR, startC, nextR, nextC, depth + 1, currentDirection, 0);
                }

                int nextDirection = getNextDirection(currentDirection, 1);
                nextR = r + dr[nextDirection];
                nextC = c + dc[nextDirection];
                if (canGo(nextR, nextC) && direction_check[nextDirection]==false) {
                    direction_check[nextDirection]=true;
                    travel(startR, startC, nextR, nextC, depth + 1, nextDirection, 1);
                    direction_check[nextDirection]=false;
                }

                nextDirection = getNextDirection(currentDirection, -1);
                nextR = r + dr[nextDirection];
                nextC = c + dc[nextDirection];
                if (canGo(nextR, nextC)&&direction_check[nextDirection]==false) {
                    direction_check[nextDirection]=true;
                    travel(startR, startC, nextR, nextC, depth + 1, nextDirection, -1);
                    direction_check[nextDirection]=false;
                }
            }
        } else {
            nextR = r +dr[currentDirection];
            nextC = c +dc[currentDirection];
            if(canGo(nextR,nextC))
                travel(startR, startC, nextR, nextC, depth + 1, currentDirection, 0);

            int nextDirection = getNextDirection(currentDirection, directionDegree);
            nextR = r + dr[nextDirection];
            nextC = c + dc[nextDirection];
            if(canGo(nextR,nextC)&&direction_check[nextDirection]==false) {
                direction_check[nextDirection]=true;
                travel(startR, startC, nextR, nextC, depth + 1, nextDirection, directionDegree);
                direction_check[nextDirection]=false;
            }
        }
        eat[food] = false;

        return;
    }

    public static int getNumOfDirection() {
        int count = 0;
        for (int i = 0; i < MAX_DIRECTION; i++)
            if (direction_check[i])
                count++;
        return count;
    }

    public static int getNextDirection(int currentDirection, int directionDegree) {
        int next = currentDirection + directionDegree;
        if (next == 4)
            next = 0;
        if (next == -1)
            next = 3;
        return next;
    }

    public static int getDegree(int currentDirection, int nextDirection) {
        if (currentDirection == 3 && nextDirection == 0)
            return 1;
        if (currentDirection == 0 && nextDirection == 3)
            return -1;
        if (nextDirection > currentDirection)
            return 1;
        else if (nextDirection == currentDirection)
            return 0;
        else
            return -1;

    }

    public static boolean canGo(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n)
            return false;
        return true;
    }

    public static boolean isEdge(int i, int j) {
        if (i == 0) {
            if (j == 0)
                return true;
            if (j == n - 1)
                return true;
        }
        if (i == n - 1) {
            if (j == 0)
                return true;
            if (j == n - 1)
                return true;
        }
        return false;
    }

    public static void initMap(BufferedReader br) throws IOException {
        map = new int[n][n];
        eat = new boolean[MAX_EAT];
        String rawString;

        //map create
        for (int i = 0; i < n; i++) {
            rawString = br.readLine();
            st = new StringTokenizer(rawString, " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


    }

    public static void initEatTable() {
        //init eat table
        for (int i = 0; i < MAX_EAT; i++)
            eat[i] = false;
    }

    public static void initDirectionCheck() {
        for (int i = 0; i < MAX_DIRECTION; i++)
            direction_check[i] = false;
    }
}

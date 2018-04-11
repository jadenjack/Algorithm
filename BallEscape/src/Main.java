import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {


    public static boolean[][] isVisited;
    public static int row;
    public static int col;
    public static int[] HolePos;

    public static final int MAX = 10;

    public static void main(String[] args) {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;
        Main main = new Main();

        try {
            String rawString = br.readLine();
            st = new StringTokenizer(rawString, " ");
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            char[][] map = new char[row][col];
            isVisited = new boolean[row][col];

            for (int i = 0; i < row; i++) {
                rawString = br.readLine();
                for (int j = 0; j < col; j++) {
                    map[i][j] = rawString.charAt(j);
                }
            }
            int[] redPos = Main.getCurrentPos(map, 'R');
            int[] bluePos = Main.getCurrentPos(map, 'B');
            HolePos = Main.getCurrentPos(map, 'O');

            System.out.println(HolePos[0] + " " + HolePos[1]);

            int depth = 0;
            ArrayList<String> path = new ArrayList<String>();
            main.move(map, depth, path, redPos, bluePos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int move(char[][] map, int depth, ArrayList<String> path, int[] redPos, int[] bluePos) {

        if (depth < MAX) {

            if (redPos[0] == HolePos[0] && redPos[1] == HolePos[1])
                System.out.println(redPos[0] + " " + redPos[1]);

            char[][] copiedMap = copyMap(map);
            depth += 1;
            String beforeMove = getBeforeMove(path);

            //left
            if (copiedMap[redPos[0]][redPos[1] - 1] != '#') {

            }

            //right
            if (copiedMap[redPos[0]][redPos[1] + 1] != '#') {

            }

            //up
            if (copiedMap[redPos[0] - 1][redPos[1]] != '#') {
                printMap(map);
                moveUp(map,redPos,bluePos,path,depth);
                printMap(map);
            }

            //down
            if (copiedMap[redPos[0] + 1][redPos[1]] != '#') {

            }

        }
        return -1;
    }

    public String getBeforeMove(ArrayList<String> path) {
        if (path.size() == 0)
            return null;
        return path.get(path.size() - 1);
    }

    public char[][] copyMap(char[][] map) {
        char[][] copied = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                copied[i][j] = map[i][j];
            }
        }
        return copied;
    }



    public boolean isSameRow(int[] redPos, int[] bluePos){
        if(redPos[0] == bluePos[0])
            return true;
        return false;
    }

    public boolean isSameCol(int[] redPos, int[] bluePos){
        if(redPos[1] == bluePos[1])
            return true;
        return false;
    }

    public boolean isExistRowWallBetween(char[][] map,int[] redPos,int[] bluePos){

        int startIndex = Math.min(redPos[0],bluePos[0]);
        int endIndex = Math.max(redPos[0],bluePos[0]);

        for(int i=startIndex+1;i<endIndex;i++){
            if(map[i][redPos[1]]=='#')
                return true;
        }
        return false;
    }

    public boolean isExistColWallBetween(char[][] map,int[] redPos,int[] bluePos){

        int startIndex = Math.min(redPos[1],bluePos[1]);
        int endIndex = Math.max(redPos[1],bluePos[1]);

        for(int i=startIndex+1;i<endIndex;i++){
            if(map[redPos[0]][i]=='#')
                return true;
        }
        return false;
    }

    public int[] getNextPos(char[][] map, int[] pos, String flag){
        switch(flag){
            case "Up" :
                for(int i=pos[0]-1;i>=0;i--){
                    if(map[i][pos[1]]=='#')
                        return new int[]{i+1,pos[1]};
                }
                break;
            case "Down" :
                for(int i=pos[0]+1;i<map.length;i++){
                    if(map[i][pos[1]]=='#')
                        return new int[]{i-1,pos[1]};
                }
                break;
            case "Left" :
                for(int i=pos[0]-1;i>=0;i--){
                    if(map[pos[0]][i]=='#')
                        return new int[]{pos[0],i+1};
                }
            case "Right" :
                for(int i=pos[0]+1;i<map[pos[0]].length;i++){
                    if(map[pos[0]][i]=='#')
                        return new int[]{pos[0],i-1};
                }
        }
        return null;
    }

    public void setMap(char[][] map, int[] pos, char c){
        map[pos[0]][pos[1]] = c;
    }

    public void syncMap(char[][] map, int[] redPos, int[] bluePos, int[] nextRedPos, int[] nextBluePos){
        setMap(map,redPos,'.');
        setMap(map,bluePos,'.');
        setMap(map,nextRedPos,'R');
        setMap(map,nextBluePos,'B');
    }

    public void moveUp(char[][] map, int[] redPos, int[] bluePos, ArrayList<String> path, int depth) {
        String beforeStep = getBeforeMove(path);
        if (beforeStep != null) {
            if(beforeStep.equals("↓"))
                return;
        }

        int[] nextRedPos = getNextPos(map,redPos,"Up");
        int[] nextBluePos = getNextPos(map,bluePos,"Up");
        char[][] copiedMap = copyMap(map);

        if(isSameCol(redPos,bluePos)){
            if(!isExistRowWallBetween(map,redPos,bluePos)) {
                if (redPos[0] < bluePos[0]) {
                    nextBluePos[0] = nextRedPos[0] + 1;
                }else{
                    nextRedPos[0] = nextBluePos[0] +1;
                }
            }
        }
        path.add("↑");
        syncMap(copiedMap,redPos,bluePos,nextRedPos,nextBluePos);
        printMap(copiedMap);
        move(copiedMap,depth+1,path,nextRedPos,nextBluePos);
        path.remove(path.size()-1);
    }

    public void moveDown(char[][] map, int[] redPos, int[] bluePos, ArrayList<String> path, int depth) {
        String beforeStep = getBeforeMove(path);
        if (beforeStep != null) {
            if(beforeStep.equals("↑"))
                return;
        }

        int[] nextRedPos = getNextPos(map,redPos,"Down");
        int[] nextBluePos = getNextPos(map,bluePos,"Down");
        char[][] copiedMap = copyMap(map);

        if(isSameCol(redPos,bluePos)){
            if(!isExistRowWallBetween(map,redPos,bluePos)) {
                if (redPos[0] < bluePos[0]) {
                    nextBluePos[0] = nextRedPos[0] + 1;
                }else{
                    nextRedPos[0] = nextBluePos[0] +1;
                }
            }
        }
        path.add("↓");
        syncMap(copiedMap,redPos,bluePos,nextRedPos,nextBluePos);
        printMap(copiedMap);
        move(copiedMap,depth+1,path,nextRedPos,nextBluePos);
        path.remove(path.size()-1);
    }

    public void moveLeft(char[][] map, int[] redPos, int[] bluePos) {
        for (int x = redPos[1]; x > 0; x--) {
            if (map[redPos[0]][x - 1] == '#') {
                map[redPos[0]][redPos[1]] = '.';
                redPos[1] = x;
                map[redPos[0]][x] = 'R';
                break;
            }
        }
        for (int x = bluePos[1]; x > 0; x--) {
            if (map[bluePos[0]][x - 1] == '#') {
                map[bluePos[0]][bluePos[1]] = '.';
                bluePos[1] = x;
                map[bluePos[0]][x] = 'B';
                break;
            }
        }
    }

    public void moveRight(char[][] map, int[] redPos, int[] bluePos) {
        for (int x = redPos[1]; x < col - 1; x++) {
            if (map[redPos[0]][x + 1] == '#') {
                map[redPos[0]][redPos[1]] = '.';
                redPos[1] = x;
                map[redPos[0]][x] = 'R';
                break;
            }
        }
        for (int x = bluePos[1]; x > 0; x--) {
            if (map[bluePos[0]][x - 1] == '#') {
                map[bluePos[0]][bluePos[1]] = '.';
                bluePos[1] = x;
                map[bluePos[1]][x] = 'B';
                break;
            }
        }
    }


    public static int[] getCurrentPos(char[][] map, char target) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void printMap(char[][] map) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}

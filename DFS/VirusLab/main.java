import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	public static int answer=0;
	public static int count0 = 0;
	public static int countSpreadVirus = 0;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmStr = br.readLine().split(" ");
		int n = Integer.parseInt(nmStr[0]);
		int m = Integer.parseInt(nmStr[1]);
		
		int[][] map = new int[n][m];
		init(br,map,n,m);
		
		solve(map,3);
		System.out.println(answer);
	}
	
	public static void solve(int[][] map, int remainWalls) {
		if(remainWalls == 0) {
			//spread and check
			countSpreadVirus = 0;
			spread(map);
			int result = count0 - countSpreadVirus;
			if(answer< result)
				answer = result;
		}else {
			// 다음 0 에 벽세우기
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[0].length;j++) {
					if(map[i][j]==0) {
						map[i][j] = 1;
						solve(map,remainWalls-1);
						map[i][j] = 0;
					}
					
				}
			}

		}
	}
	
	public static void spread(int[][] map) {
		ArrayList<Point> virusList = new ArrayList<Point>();
		int[][] copyMap = new int[map.length][map[0].length];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				copyMap[i][j] = map[i][j];
				if(copyMap[i][j] == 2)
					virusList.add(new Point(i,j));
			}
		}
		
		Iterator<Point> iterator = virusList.iterator();
		while(iterator.hasNext()) {
			Point next = iterator.next();
			spreadVirus(copyMap, next);
		}
	}
	
	public static void spreadVirus(int[][] map, Point next) {
		spreadVirus(map,next.y,next.x);
	}
	public static void spreadVirus(int[][] map, int y, int x) {
		if(x>=1 && map[y][x-1]==0) {
			map[y][x-1] = 2;
			countSpreadVirus++;
			spreadVirus(map,y,x-1);
		}
		if(x<map[0].length-1 && map[y][x+1] == 0) {
			map[y][x+1] = 2;
			countSpreadVirus++;
			spreadVirus(map,y,x+1);
		}
		if(y>=1 && map[y-1][x]==0) {
			map[y-1][x] = 2;
			countSpreadVirus++;
			spreadVirus(map,y-1,x);
		}
		if(y<map.length-1&& map[y+1][x] == 0) {
			map[y+1][x] = 2;
			countSpreadVirus++;
			spreadVirus(map,y+1,x);
		}
	}
	
	public static void init(BufferedReader br, int[][] map,int n, int m) throws IOException {
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0)
					count0++;
			}
		}
		count0-=3;
	}
	public static class Point{
		public int y;
		public int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}

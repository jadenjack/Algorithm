import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

//https://www.acmicpc.net/problem/14442
public class Main {
	public static int answer = Integer.MAX_VALUE;
	public static int n;
	public static int m;
	public static int k;
	public static int[][] map;
	public static int[][][] visit;
	public static LinkedList<Point> list;
	
	public static int[] dy = {1,-1,0,0};
	public static int[] dx = {0,0,1,-1};
	
	public static class Point{
		int y,x,c,b;
		public Point(int y,int x,int c,int b) {
			this.y = y;
			this.x = x;
			this.c= c;
			this.b = b;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		n = Integer.parseInt(inputs[0]);
		m = Integer.parseInt(inputs[1]);
		//k = Integer.parseInt(inputs[2]);
		k = 1;

		map = new int[n][m];
		visit = new int[n][m][k+1];
		for(int i=0;i<n;i++) {
			String inputMap = br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j] = inputMap.charAt(j) - '0';
			}
		}
		visit[0][0][0] = 1;
		
		list = new LinkedList<Point>();
		list.add(new Point(0,0,0,1));
		while(!list.isEmpty()) {
			Point next = list.get(0);
			list.remove(next);
			solve(next);
		}
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);
		
	}	
	public static void solve(Point p) {
		
		int y = p.y;
		int x = p.x;
		int breakCount = p.c;
		int b = p.b;
		//∞Ò¿œ∂ß
		if(y==n-1 && x==m-1) {
			if(b<answer)
				answer = b;
			return ;
		}
		
		for(int i=0;i<4;i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			if(ny<0||nx<0||ny>=n||nx>=m)
				continue;
			
			if(map[ny][nx]==0&&visit[ny][nx][breakCount]==0) {
				visit[ny][nx][breakCount]=visit[y][x][breakCount]+1;
				list.add(new Point(ny,nx,breakCount,b+1));
			}else if(map[ny][nx]==1&&breakCount<k&&visit[ny][nx][breakCount+1]==0) {
				visit[ny][nx][breakCount+1]=visit[y][x][breakCount]+1;
				list.add(new Point(ny,nx,breakCount+1,b+1));
			}
			
		}
		
	}

}

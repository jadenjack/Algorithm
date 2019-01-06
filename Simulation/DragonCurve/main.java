import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static BufferedReader br;
	public static int n;
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,-1,0,1};
	public static boolean[][] map;
	public static int answer = 0;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		 map = new boolean[101][101];
		for(int i=0;i<101;i++) {
			map[i] = new boolean[101];
		}
			
		draw(map);
//		for(int i=0;i<101;i++) {
//			for(int j=0;j<101;j++) {
//				if(map[i][j] == false)
//					System.out.print(0);
//				else
//					System.out.print(1);
//			}
//			System.out.println("");
//		}
		calc(map);
		System.out.println(answer);
	}
	public static void calc(boolean[][] map) {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(has0(i,j)) {
					continue;
				}else {
					answer++;
				}
			}
		}
	}
	public static boolean has0(int n,int m) {
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				if(map[n+i][m+j]==false)
					return true;
			}
		}
		return false;
	}
	public static void draw(boolean[][] map) throws IOException {
		for(int i=0;i<n;i++) {
			String[] curveStr = br.readLine().split(" ");
			int x = Integer.parseInt(curveStr[0]);
			int y = Integer.parseInt(curveStr[1]);
			int d = Integer.parseInt(curveStr[2]);
			int g = Integer.parseInt(curveStr[3]);
			
			ArrayList<Line> lineList = new ArrayList<Line>();
			
			drawCurve(map,new MyCurve(x,y,d,g),0,lineList);
		}
	}
	public static void drawCurve(boolean[][] map, MyCurve c, int currentGen,ArrayList<Line> lineList) {
		
		if(currentGen==0) {
			Line gen0Line = new Line(c);
			lineList.add(gen0Line);
			drawLine(map,gen0Line);
		}
		else {
			int drawSize = 1<<(currentGen-1);
			int listSize = lineList.size();
			Line lastLine = lineList.get(listSize-1);
			int startY = lastLine.y+dy[lastLine.d];
			int startX = lastLine.x+dx[lastLine.d];
			for(int i=listSize-1;i>=0;i--) {
				Line next = lineList.get(i);
				int nd = next.d + 1;
				if(nd==4)
					nd = 0;
				Line newLine = new Line(startX,startY,nd);
				drawLine(map,newLine);
				lineList.add(newLine);
				startY = startY+dy[nd];
				startX = startX+dx[nd];
			}
		
		}
		
		if(currentGen<c.g) {
			drawCurve(map,c,currentGen+1,lineList);
		}
	}
	public static void drawLine(boolean[][] map, int x, int y, int d) {
		drawLine(map, new Line(x,y,d));
	}
	public static void drawLine(boolean[][] map, Line line) {
		if(inBoundary(line.x,line.y)) {
			map[line.y][line.x]= true; 
		}
		int ny = line.y + dy[line.d];
		int nx = line.x + dx[line.d];
		if(inBoundary(nx,ny)) {
			map[ny][nx] = true;
		}
	}
	public static boolean inBoundary(int x, int y) {
		return y>=0 && x>=0 && y<=100 & x<=100;
	}
	
	public static class MyCurve{
		int x;
		int y;
		int d;
		int g;
		public MyCurve(int x,int y,int d,int g) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.g= g;
		}
	}
	public static class Line{
		int x;
		int y;
		int d;
		public Line(int x,int y,int d) {
			this.x=x;
			this.y=y;
			this.d=d;
		}
		public Line(MyCurve c) {
			this.x = c.x;
			this.y = c.y;
			this.d = c.d;
		}
	}
}

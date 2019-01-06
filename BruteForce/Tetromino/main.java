import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/14500
public class Main {

	public static int result = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		int[][] map = new int[n][m];
		//init
		for(int i=0;i<n;i++) {
			String[] inputs = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		
		solve(map);
		System.out.println(result);
		
	}

	public static void solve(int[][] map) {
		
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				travel(map,i,j,i,j,3,0);
				travelTriangle(map,i,j);
			}
		}
		
	}
	
	public static void travelTriangle(int[][] map, int y, int x) {
		int smallest = Integer.MAX_VALUE;
		int sum=0;
		int direction = 0;
		
		sum+=map[y][x];
		if(y>0) {
			sum+=map[y-1][x];
			if(map[y-1][x]<smallest)
				smallest = map[y-1][x];
			direction++;
		}
		if(y<map.length-1) {
			sum+=map[y+1][x];
			if(map[y+1][x]<smallest)
				smallest = map[y+1][x];
			direction++;
		}
		if(x>0) {
			sum+=map[y][x-1];
			if(map[y][x-1]<smallest)
				smallest = map[y][x-1];
			direction++;
		}
		if(x<map[0].length-1) {
			sum+=map[y][x+1];
			if(map[y][x+1]<smallest)
				smallest = map[y][x+1];
			direction++;
		}
		
		if(direction==3) {
			if(sum>result)
				result = sum;
		}else if(direction==4) {
			sum-=smallest;
			if(sum>result)
				result = sum;
		}
			
	}
	
	public static void travel(int[][] map,int startY, int startX, int y, int x, int remains, int sum) {
		
		int currentSum = sum+ map[y][x];
		if(remains<=0) {
			if(currentSum>result) {
				result = currentSum;
			}
			return;
		}
		
		if(y>0 && (startY!=y-1 ||startX!=x)) {
			travel(map,y,x,y-1,x,remains-1,currentSum);
		}
		if(y<map.length-1 && (startY!=y+1 || startX!=x)) {
			travel(map,y,x,y+1,x,remains-1,currentSum);
		}
		if(x>0 && (startY!=y||startX!=x-1)) {
			travel(map,y,x,y,x-1,remains-1,currentSum);
		}
		if(x<map[0].length-1 &&(startY!=y || startX!=x+1)) {
			travel(map,y,x,y,x+1,remains-1,currentSum);
		}
		
	}
}

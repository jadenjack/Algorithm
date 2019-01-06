import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringBuilder sb = new StringBuilder();
			int lines = Integer.parseInt(br.readLine());
			int[][] map = new int[lines][lines];
			int[][] answer = new int[lines][lines];
			for(int i=0;i<lines;i++) {
				String[] inputs = br.readLine().split(" ");
				for(int j=0;j<inputs.length;j++) {
					int inputNum = Integer.parseInt(inputs[j]); 
					map[i][j] = inputNum;
					answer[i][j] = inputNum;
				}
			}
			
			solve(map,answer);
			for(int i=0;i<answer.length;i++) {
				for(int j=0;j<answer.length;j++) {
					sb.append(answer[i][j]);
					if(j<answer.length-1)
						sb.append(" ");
				}
				if(i<answer.length-1)
					sb.append("\n");
			}
			System.out.println(sb.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void solve(int[][] map,int[][] answer) {
		int lines = map.length;
		for(int i=0;i<lines;i++) {
			for(int j=0;j<lines;j++) {
				if(map[i][j]==1) {
					connect(map,answer,i,j);
				}
			}
		}
	}
	
	public static void connect(int[][] map, int[][] answer, int startLine, int connectLine) {
		for(int i=0;i<map.length;i++) {
			if(map[connectLine][i]==1 && answer[startLine][i]!=1) {
				answer[startLine][i]=1;
				connect(map,answer,startLine,i);
			}
		}
	}
}

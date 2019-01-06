import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//시작점을 기준으로 Distance를 정해야함.
public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] inputs = br.readLine().split(" ");
			int n = Integer.parseInt(inputs[0]);
			int m = Integer.parseInt(inputs[1]);
			int v = Integer.parseInt(inputs[2]);

			Point[] points = new Point[n+1];
			for (int i = 1; i <= n; i++) {
				points[i] = new Point();
				points[i].value = i;
			}

			for (int i = 0; i < m; i++) {
				String[] eachLine = br.readLine().split(" ");
				int startNum;
				int endNum;
				if(Integer.parseInt(eachLine[0])<Integer.parseInt(eachLine[1])) {
					startNum = Integer.parseInt(eachLine[0]);
					endNum = Integer.parseInt(eachLine[1]);
				}else {
					startNum = Integer.parseInt(eachLine[1]);
					endNum =Integer.parseInt(eachLine[0]);
				}
				points[startNum].line.add(points[endNum]);
				points[endNum].line.add(points[startNum]);
			}

			// DFS
			ArrayList<Integer> printList = new ArrayList<Integer>();
			boolean[] check = new boolean[n+1];
			initCheck(check);
			DFS(points, check, v, printList);
			print(printList);
			//bfs
			ArrayList<Point> pointQueue = new ArrayList<Point>();
			pointQueue.add(points[v]);
			printList.clear();
			initCheck(check);
			BFS(points, check, printList,pointQueue);
			
			print(printList);

		} catch (IOException e) {

		}
	}

	public static class Point implements Comparable<Point>{
		public int value;
		public ArrayList<Point> line;

		public Point() {
			line = new ArrayList<Point>();
		}

		@Override
		public int compareTo(Point o) {
			return value-o.value;
		}
	}

	public static void BFS(Point[] points, boolean[] check, ArrayList<Integer> printList, ArrayList<Point> pointQueue) {
		while(!pointQueue.isEmpty()) {
			Point next = pointQueue.get(0);
			pointQueue.remove(0);
			if(check[next.value])
				continue;
			else {
				check[next.value]=true;
				printList.add(next.value);
				Collections.sort(next.line);
				pointQueue.addAll(next.line);
			}
		}
	}
	
	public static void DFS(Point[] points, boolean[] check, int index, ArrayList<Integer> printList) {
		if (check[index] == false) {
			check[index] = true;
			printList.add(points[index].value);
			
			Collections.sort(points[index].line);
			Iterator<Point> iterator = points[index].line.iterator();
			while (iterator.hasNext()) {
				Point next = iterator.next();
				DFS(points, check, next.value, printList);
			}
		}
	}

	public static void print(ArrayList<Integer> printList) {
		Iterator<Integer> iterator = printList.iterator();
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext()) {
			int next = iterator.next();
			sb.append(next);
			if (iterator.hasNext())
				sb.append(" ");
		}
		System.out.println(sb.toString());
	}

	public static void initCheck(boolean[] check) {
		
		for(int i=0;i<check.length;i++)
			check[i] = false;
	}
}

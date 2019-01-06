
import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static int n,m,h;
	public static int answer = -1;
	public static int[] goalSet;
	public static Queue<ArrayList<Line>> listQueue = new LinkedList<ArrayList<Line>>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		n = Integer.parseInt(inputs[0]);
		m = Integer.parseInt(inputs[1]);
		h = Integer.parseInt(inputs[2]);
		goalSet = new int[n];
		for(int i=0;i<n;i++)
			goalSet[i] = i+1;
		ArrayList<Line> lines = new ArrayList<Line>();
		for(int i=0;i<m;i++) {
			String[] points = br.readLine().split(" ");
			lines.add(new Line(points[0],points[1]));
		}
		listQueue.add(lines);
		while(!listQueue.isEmpty()&&answer==-1) {
			solve();
		}
		System.out.println(answer);
	}
	public static void solve() {
		
		if(listQueue.isEmpty()) {
			answer = -1;
			return;
		}
		ArrayList<Line> next = listQueue.poll();
		if(goal(next)) {
			answer = next.size()-m;
			//for(int i=0;i<next.size();i++) {
			//	System.out.println(next.get(i).y + " , " + next.get(i).x);
			//}
			return;
		}else {
			//expend
			if(next.size()-m>=3)
				return;
			
			for(int i=1;i<=h;i++) {
				for(int j=1;j<=n-1;j++) {
					
					if(!canMakeLine(next,i,j))
						continue;
					else{
						Line newLine = new Line(i,j);
						next.add(newLine);
						if(canMakeAnswer(next)) {
							listQueue.add(new ArrayList<Line>(next));
						}else if(goal(next)){
							answer = next.size()-m;
							return;
						}
						next.remove(newLine);
					}
					
				}
			}
		}
	}
	
	public static boolean canMakeAnswer(ArrayList<Line> list) {
		int[] lineNumbers = new int[n+1];
		Iterator<Line> iter = list.iterator();
		while(iter.hasNext()) {
			Line next = iter.next();
			lineNumbers[next.x]++;
		}
		
		int remainLine = 3-(list.size()-m);
		int oddlines=0;
		for(int i=0;i<lineNumbers.length;i++) {
			if(lineNumbers[i]%2==1)
				oddlines++;
		}
		
		if(oddlines>remainLine)
			return false;
		return true;
	}
	
	public static boolean canMakeLine(ArrayList<Line> list, int y, int x) {
		Iterator<Line> iterator = list.iterator();
		while(iterator.hasNext()) {
			Line next = iterator.next();
			if(next.y == y && Math.abs(next.x-x)<=1)
				return false;
		}
		return true;
	}
	
	public static boolean goal(ArrayList<Line> lines) {
		
		Collections.sort(lines);
		int[] copyArray = copyArray(goalSet);
		Iterator<Line> iterator = lines.iterator();
		while(iterator.hasNext()) {
			Line next = iterator.next();
			swap(copyArray, next.x-1);
		}
		return sameArray(goalSet,copyArray);
	}
	
	public static void swap(int[] copyArray,int x) {
		int temp = copyArray[x];
		copyArray[x] = copyArray[x+1];
		copyArray[x+1] = temp;
	}
	
	public static int[] copyArray(int[] a) {
		int[] copy = new int[a.length];
		for(int i=0;i<a.length;i++)
			copy[i] = a[i];
		return copy;
	}
	
	public static boolean sameArray(int[] a, int[] b) {
		for(int i=0;i<a.length;i++) {
			if(a[i]!=b[i])
				return false;
		}
		return true;
	}
	
	public static boolean canMakeNewLine(ArrayList<Line> list, int y, int x) {
		Iterator<Line> iterator = list.iterator();
		while(iterator.hasNext()) {
			Line next = iterator.next();
			if(y==next.y && Math.abs(next.x-x)<=1)
				return false;
		}
		return true;
	}
	
	public static class Line implements Comparable<Line> {
		int y;
		int x;
		public Line(String points, String points2) {
			this.y = Integer.parseInt(points);
			this.x = Integer.parseInt(points2);
		}
		public Line(int poiints, int points2) {
			this.y = poiints;
			this.x = points2;
		}
		@Override
		public int compareTo(Line o) {
			return this.y-o.y;
		}
	}
	
}

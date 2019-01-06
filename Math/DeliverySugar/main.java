import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	public static int answer = Integer.MAX_VALUE;
	
    public static void main(String[] args){
    	try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int kg = Integer.parseInt(br.readLine());
        int[] kgs = new int[]{5,3};
        
        solve(kg,kgs,0,0);
        if(answer==Integer.MAX_VALUE)
        	System.out.println(-1);
        else
        	System.out.println(answer);
    	}catch(IOException e){
    		
    	}
    }
    
    public static void solve(int kg, int kgs[],int count,int index) {
    	if(index>=kgs.length)
    		return;
    	int max = kg/kgs[index];
    	for(int i=max;i>=0;i--) {
    		int remains = kg - kgs[index]*i;
    		if(remains==0&&answer>count+i) {
    			answer = count+i;
    		}
    		else
    			solve(remains,kgs,i,index+1);
    	}
    }
}


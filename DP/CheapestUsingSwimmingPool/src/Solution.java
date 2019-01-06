import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {


    public static int[] monthPeriod = new int[]{12,3,1,1};
    public static int[] price;
    public static final int NUM_MONTHS = 12;
    public static final int NUM_PRICE = 4;
    public static int answer;

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        price = new int[NUM_PRICE];
        StringTokenizer st;

        try{
            int tn = Integer.parseInt(br.readLine());
            for(int t = 1; t<=tn;t++){
                answer = Integer.MAX_VALUE;
                st = getStringToken(br);
                //input price
                for(int p = NUM_PRICE-1;p>=0;p--)
                    price[p] = Integer.parseInt(st.nextToken());

                st = getStringToken(br);
                //input using plan
                int[] plan = new int[NUM_MONTHS];
                boolean[] ischecked = new boolean[NUM_MONTHS];
                for(int p = 0;p<NUM_MONTHS;p++){
                    plan[p] = Integer.parseInt(st.nextToken());
                }
                initCheckedList(plan,ischecked);
                int charge = 0;
                solve(charge,plan,ischecked);

                appendAnswer(sb,t,answer);
                if(t<tn)
                    sb.append("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(sb.toString());

    }

    public static void appendAnswer(StringBuilder sb, int testNumber, int answer){
        sb.append("#");
        sb.append(testNumber);
        sb.append(" ");
        sb.append(answer);
    }

    public static StringTokenizer getStringToken(BufferedReader br) throws IOException{
        String rawString = br.readLine();
        return new StringTokenizer(rawString," ");
    }
    public static void solve(int charge,int[] plan,boolean[] isChecked){

        int startIndex = getStartMonth(isChecked);
        if(startIndex>=NUM_MONTHS){
            if(charge<answer)
                answer = charge;
            return;
        };

        for(int i=0;i<NUM_PRICE;i++) {

            //update checkedList
            //boolean[] copiedChecked = Arrays.copyOf(isChecked,isChecked.length);
            boolean[] copiedChecked = copy(isChecked);
            for(int j=0;j<monthPeriod[i];j++){
                if(j+startIndex>=NUM_MONTHS||copiedChecked[j+startIndex]==true)
                    break;
                copiedChecked[j+startIndex] = true;
            }
            int fee;
            if(i==NUM_PRICE-1)
                fee = plan[startIndex] * price[i];
            else
                fee = price[i];

            solve(charge+fee,plan,copiedChecked);
        }


    }

    public static boolean[] copy(boolean[] list){
        boolean[] copied = new boolean[list.length];
        for(int i=0;i< list.length;i++)
            copied[i] = list[i];
        return copied;
    }

    public static void initCheckedList(int[] plan,boolean[] isChecked){
        for(int i=0;i<NUM_MONTHS;i++){
            if(plan[i] == 0){
                isChecked[i] = true;
            }else
                isChecked[i] = false;
        }
    }

    public static int getStartMonth(boolean[] isChecked){

        for(int i=0;i<NUM_MONTHS;i++){
            if(isChecked[i]==false){
                return i;
            }
        }
        return NUM_MONTHS;
    }
}

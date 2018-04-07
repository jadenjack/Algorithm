import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {

        int testcase = 0;
        int n = 0;
        int nextInput = 0;
        int result = 0;
        StringTokenizer st;

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            testcase = Integer.parseInt(br.readLine());

            for (int j = 0; j < testcase; j++) {
                result = 0;

                inputList.clear();
                n = Integer.parseInt(br.readLine());

                st = new StringTokenizer(br.readLine()," ");
                for(int i=0;i<n;i++){
                    nextInput = Integer.parseInt(st.nextToken());

                    inputList.add(nextInput);

                }
                Collections.sort(inputList, Collections.reverseOrder());

                for (int i = 0; i < inputList.size(); i += 3) {
                    if (i + 1 < inputList.size())
                        result = result + inputList.get(i) + inputList.get(i + 1);
                    else if (i < inputList.size())
                        result = result + inputList.get(i);
                }
                sb.append("#");
                sb.append(testcase);
                sb.append(" ");
                sb.append(result);
                sb.append("\n");
            }

            System.out.println(sb.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

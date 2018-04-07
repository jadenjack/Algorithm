import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
Question from this website :
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD&categoryId=AV15Khn6AN0CFAYD&categoryType=CODE

Get biggest Reward within given chance to change

testcast input - output
10

123 1 -#1 321
2737 1 -#2 7732
757148 1 -#3 857147
78466 2 -#4 87664
32888 2 -#5 88832
777770 5 -#6 777770
436659 2	 -#7 966354
431159 7	 -#8 954311
112233 3 -#9 332211
456789 10 -#10 987645


1. make idealArray(descending sorted array)

2. sort
    2.1 if(subject different with ideal condition)
        2.1.1 find position that doesn't match with ideal array
                and swap with the largest number of other numbers(placed on right side of the array)
                while changeCount < chance to change || subject same with ideal array
    2.2 else(means subject is already biggest reward)
        2.2.1 if(there is same number on the array)
                then swap them(anyway same result due to swap same number)
        2.2.2 else
                repaet meaningless job
                swap smallest numbers while consume all chance to change
                in the situation, smallest numbers' position are length-1,length-2


 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int testcase = Integer.parseInt(sc.nextLine());
        int subject;
        int chanceToChange;
        int changeCount;
        int[] subjectArray;

        int temp;
        StringBuilder sb = new StringBuilder();


        for(int testCount=1;testCount<=testcase;testCount++){

            subject = Integer.parseInt(sc.next());
            subjectArray = getSubjectArray(subject);
            chanceToChange = Integer.parseInt(sc.next());
            changeCount = 0;
            sb.setLength(0);

            int samePositionLeft=0;
            int samePositionRight=subjectArray.length-1;
            int changedPositionLeft;
            int changedPositionRight;
            int biggestNumPosition;

            int[] idealSortedArray = getIdealSortedArray(subjectArray);

            while(changeCount<chanceToChange) {
                if (!Arrays.equals(subjectArray, idealSortedArray)) {


                    for(changedPositionLeft=0;changedPositionLeft<subjectArray.length-1;changedPositionLeft++){
                        if(subjectArray[changedPositionLeft]!=idealSortedArray[changedPositionLeft])
                            break;
                    }
                    //find biggest number in compared numbers
                    biggestNumPosition = subjectArray.length-1;
                    for(changedPositionRight=subjectArray.length-1;changedPositionRight>changedPositionLeft;changedPositionRight--){
                        if(subjectArray[changedPositionRight]>subjectArray[biggestNumPosition])
                            biggestNumPosition = changedPositionRight;
                    }

                    if(subjectArray[biggestNumPosition]==subjectArray[biggestNumPosition-1]
                            &&chanceToChange-changeCount>1)
                        biggestNumPosition--;


                    temp = subjectArray[changedPositionLeft];
                    subjectArray[changedPositionLeft] = subjectArray[biggestNumPosition];
                    subjectArray[biggestNumPosition] = temp;
                    changeCount++;

                } else {//already biggest reward
                    if (isThereSameNumber(subjectArray,samePositionLeft,samePositionRight)) {//when there is any same numbers
                        changeCount=chanceToChange;
                    } else {
                        temp = subjectArray[subjectArray.length - 2];
                        subjectArray[subjectArray.length - 2] = subjectArray[subjectArray.length -1];
                        subjectArray[subjectArray.length -1] = temp;
                        changeCount++;
                    }



                }


            }
            sb.append("#");
            sb.append(testCount);
            sb.append(" ");
            for(int z = 0;z<subjectArray.length;z++){
                sb.append(subjectArray[z]);
            }
            System.out.println(sb.toString());
        }

    }

    public static int[] getSubjectArray(int subject){

        return Integer.toString(subject).chars().map(c -> c-='0').toArray();

    }

    public static int[] getIdealSortedArray(int[] subjectArray){
        Integer[] idealArray = new Integer[subjectArray.length];

        for(int i=0;i<idealArray.length;i++){
            idealArray[i] = subjectArray[i];
        }

        Arrays.sort(idealArray,Collections.reverseOrder());

        int[] idealArrayInt = new int[idealArray.length];
        for(int i=0;i<idealArray.length;i++){
            idealArrayInt[i] = idealArray[i];
        }

        return idealArrayInt;
    }

    public static Boolean isThereSameNumber(int[] arr,int samePositionLeft,int samePositionRight){

        for(samePositionLeft = 0; samePositionLeft<arr.length-1;samePositionLeft++){
            for(samePositionRight=samePositionLeft+1;samePositionRight<arr.length;samePositionRight++)
                if(arr[samePositionLeft]==arr[samePositionRight])
                    return true;
        }
        return false;
    }

}

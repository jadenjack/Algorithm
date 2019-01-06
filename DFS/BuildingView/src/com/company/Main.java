package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String input = "";
        Scanner scanner = new Scanner(System.in);
        final int RANGEVIEW = 2;
        int highestPositionLeft = 0;
        int highestPositionRight = 0;
        int leftValue = 0;
        int rightValue = 0;
        int answer = 0;
        int comparedFloor = 0;

        List<Integer> floorList = new ArrayList<Integer>();

        for(int testcase = 0;testcase<10;testcase++) {

            input = scanner.nextLine();
            highestPositionLeft = 0;
            highestPositionRight = 0;
            leftValue = 0;
            rightValue = 0;
            answer = 0;
            comparedFloor = 0;
            floorList.clear();

            Integer numberOfFloor = Integer.parseInt(input);
            for(int i=0;i<numberOfFloor;i++){

                try {
                    input = scanner.next();
                    Integer inputFloor = Integer.parseInt(input);
                    floorList.add(inputFloor);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please input only number");
                    return;
                }

            }

            for (int i = RANGEVIEW; i < floorList.size() - RANGEVIEW; i++) {

                highestPositionLeft = (floorList.get(i - 1) > floorList.get(i - 2)) ? i - 1 : i - 2;
                highestPositionRight = (floorList.get(i + 1) > floorList.get(i + 2)) ? i + 1 : i + 2;


                leftValue = floorList.get(highestPositionLeft);
                rightValue = floorList.get(highestPositionRight);

                if (floorList.get(i) > floorList.get(highestPositionLeft) &&
                        floorList.get(i) > floorList.get(highestPositionRight)) {

                    comparedFloor = leftValue > rightValue ? leftValue : rightValue;
                    answer += floorList.get(i) - comparedFloor;

                }


            }
            System.out.println("#" + testcase + " " + answer);
            if(scanner.hasNext())
                scanner.nextLine();
        }
    }
}

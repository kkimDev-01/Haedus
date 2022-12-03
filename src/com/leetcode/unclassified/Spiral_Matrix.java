package com.leetcode.unclassified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


//TODO : 좀더 단순화할 방향 생각해보기 (재귀)

public class Spiral_Matrix {

    public static void main(String[] args) {

//        matrix = [[1,2,3],[4,5,6],[7,8,9]]



        //piralOrder(matrix);

    }


    public static boolean end;
    public static int X;
    public static int Y;
    public static HashMap<String, Boolean> map;
    public static List<Integer> list;

    public static String heading;

    public static List<Integer> spiralOrder(int[][] matrix) {
        end = false;
        X = 0;
        Y = 0;
        map = new HashMap<>();
        list = new ArrayList<>();
        heading = "r"; //right


        list.add(matrix[X][Y]);

        while(!end) {
            proceed(matrix);
        }

        return list;

    }

    private static void proceed(int[][] matrix) {




        boolean firstTry = tryHeading(heading);

        if (!firstTry) {
            tryGeneral(matrix);
        }


        if (!end) {
            list.add(matrix[Y][X]);
        }


    }

    private static boolean tryHeading(String heading) {

        switch(heading) {
            case "L":
                break;
            case "U":


                break;
            case "D":


                break;
            default :

                break;
        }

        return true;
    }

    private static void tryGeneral(int[][] matrix) {

        int rightX = X+1;
        int leftX = X-1;
        int upY = Y-1;
        int downY = Y+1;

        //try right
        if (rightX < matrix[0].length && !map.containsKey(rightX + "-" + Y)) {
            map.put(rightX + "-" + Y, true);
            X = rightX;
        }
        //try down
        else if (downY < matrix.length && !map.containsKey(X+"-"+downY)){
            map.put(X+"-"+downY, true);
            Y = downY;
        }
        //try left
        else if (leftX >= 0 && !map.containsKey(leftX+"-"+Y)){
            map.put(leftX+"-"+Y, true);
            X = leftX;
        }
        //try up
        else if (upY >=0 && !map.containsKey(X+"-"+upY)){
            map.put(X+"-"+upY, true);
            Y = upY;
        }
        else {
            end = true;
        }

    }

}

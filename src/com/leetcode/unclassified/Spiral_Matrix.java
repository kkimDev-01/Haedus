package com.leetcode.unclassified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


//TODO : 좀더 단순화할 방향 생각해보기 (재귀)

public class Spiral_Matrix {

    public static void main(String[] args) {

// matrix = [[1,2,3],[4,5,6],[7,8,9]]
// [1,2,3,6,9,8,7,4,5]

        //piralOrder(matrix);

    }


    public static enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    public static int X;
    public static int Y;

    public static HashMap<String, Boolean> map;

    public static boolean end;

    public static int[][] mat;

    public static Direction heading;

    public static List<Integer> spiralOrder(int[][] matrix) {
        X = 0;
        Y = 0;
        map = new HashMap<>();
        end = false;
        mat = matrix;
        heading = Direction.RIGHT;

        List<Integer> list = new ArrayList<>();

        map.put(X + "-" + Y, true);
        list.add(mat[0][0]);

        while(!(map.size() == matrix.length * matrix[0].length)) {
            boolean headSucc = false;
            boolean rightSucc = false;
            headSucc = head();
            if (headSucc){
                map.put(X + "-" + Y, true);
                list.add(mat[Y][X]);
            }else {
                right();
            }

        }

        return list;
    }

    private static void right() {
        if (heading == Direction.RIGHT){
            heading = Direction.DOWN;
        } else if (heading == Direction.LEFT) {
            heading = Direction.UP;

        } else if (heading == Direction.UP) {
            heading = Direction.RIGHT;

        } else if (heading == Direction.DOWN) {
            heading = Direction.LEFT;
        }
    }


    private static boolean head() {
        boolean flag = false;

        if (heading == Direction.RIGHT){
            if (X+1 < mat[0].length && !map.containsKey((X+1)+"-"+Y)){
                X++;
                return true;
            }
        } else if (heading == Direction.LEFT && !map.containsKey((X-1)+"-"+Y)) {
            if (X-1 >= 0){
                X--;
                return true;
            }
        } else if (heading == Direction.UP && !map.containsKey((X)+"-"+(Y-1))) {
            if (Y-1 >= 0){
                Y--;
                return true;
            }
        } else if (heading == Direction.DOWN && !map.containsKey((X)+"-"+(Y+1))) {
            if (Y+1 < mat.length){
                Y++;
                return true;
            }
        }
        return false;

    }


}

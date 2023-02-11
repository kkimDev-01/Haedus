package com.practice;

import java.util.Stack;

public class Backtracking {
    public static void main(String[] args) {

    }

    public static boolean wordSearch(char[][] grid, String word) {
        // Write your code here

        Stack<Point> stack = new Stack<>();

        String[] wordArr = word.split("");

        int startingI = 0;
        int startingJ = 0;

        Point deleted = null;

        Point next = null;

        while(stack.size() == word.length()) {

            if (stack.size() == 0) {
                addFirstElement(startingI, startingJ, stack, grid, wordArr);
                startingI = stack.peek().getX();
                startingJ = stack.peek().getY();
                continue;
            }

            next = findNextPoint(deleted, );

            if (findNextPoint() == null) {
                Point deleted = stack.pop();
            }

        }

        return  false;
    }


    private static Point findNextPoint()

    private static void addFirstElement(int startingI, int startingJ, Stack<Point> stack, char[][] grid, String[] wordArr) {

        outerLoop:
        for (int i = startingI; i < grid.length; i++) {
            if (i > startingI) {
                startingJ = 0;
            }
            for (int j = startingJ + 1; j < grid[0].length; j++) {
                if (String.valueOf(grid[i][j]).equals(wordArr[0])) {
                    stack.add(new Point(i,j, wordArr[0]));
                    break outerLoop;
                }
            }
        }

    }

    static class Point {
        private int x;
        private int y;
        private String alphabet;

        public Point(int x, int y, String alphabet) {
            this.x = x;
            this.y = y;
            this.alphabet = alphabet;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public String getAlphabet() {
            return alphabet;
        }

        public void setAlphabet(String alphabet) {
            this.alphabet = alphabet;
        }
    }




}

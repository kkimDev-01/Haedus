package com.leetcode.dynamic_programming;

public class Climbing_Stars {

    public static void main(String[] args) {

    }

    public static int[] dp;
    public int climbStairs(int n) {
        dp = new int[n];
        dp[0] = 1;

        if (n == 1){
            return dp[0];
        }

        dp[1] = 2;

        if (n == 2){
            return dp[1];
        }

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n-1];

    }
}

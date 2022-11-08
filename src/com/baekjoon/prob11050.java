package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/11050
해설 : https://st-lab.tistory.com/159
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob11050 {
}

/*
결과
    - 메모리 : 14308
    - 시간 : 124
    - 시간복잡도 : O(N)
*/
//재귀 + 동적계획법 (메모이제이션)
class prob11050_1 {

    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputArr = br.readLine().split(" ");

        int N = Integer.parseInt(inputArr[0]);
        int K = Integer.parseInt(inputArr[1]);

        dp = new int[N + 1][K + 1];

        System.out.println(BC(N, K));

    }

    private static int BC(int n, int k) {

        if (dp[n][k] > 0){
            return dp[n][k];
        }

        if (k == 0 || n == k){
            return dp[n][k] = 1;
        }

        return dp[n][k] = BC(n - 1, k - 1) + BC(n - 1, k);

    }
}



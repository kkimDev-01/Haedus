package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1463 {
}

/*
결과 
    - 메모리 : 75136
    - 시간 : 260
    - 시간복잡도 : 
*/
class prob1463_1 {

    static Integer[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];
        dp[0] = dp[1] = 0;

        System.out.println(recur(N));



    }

    private static int recur(int N) {

        if (dp[N] == null){

            //6으로 나눠지는 경우
            if (N % 6 == 0) {
                dp[N] = Math.min(recur(N-1),
                                Math.min(recur(N/3), recur(N/2))) + 1; //그냥 셋 중 가장 작은 것 고르는 것임
            }
            else if (N % 3 == 0) {
                dp[N] = Math.min(recur(N/3),
                                 recur(N-1)) + 1;
            }

            else if (N % 2 == 0) {
                dp[N] = Math.min(recur(N/2), recur(N-1)) + 1;
            }

            else {
                dp[N] = recur(N-1) + 1;

            }
        }

        return dp[N];
    }
}


package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/1904
해설 : https://st-lab.tistory.com/125
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob1904 {
}


/*
결과 
    - 메모리 : 52064
    - 시간 : 320
    - 시간복잡도 : 
*/
class prob1904_1 {

    public static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {
            dp[i] = -1;
        }

        System.out.println(Tile(N));

    }

    private static int Tile(int N) {

        if (dp[N] == -1){
            dp[N] = (Tile(N-1) + Tile((N-2))) % 15746;
        }

        return dp[N];

    }
}

/*
결과 
    - 메모리 : 14292
    - 시간 : 132
    - 시간복잡도 : 
*/
class prob1904_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(Tile(N));

    }

    public static int Tile(int a) {

        if (a == 1) {
            return 1;
        }

        if (a == 2) {
            return 2;
        }

        // 초기 값
        int val1 = 1;
        int val2 = 2;
        int sum = 0;

        for (int i = 2; i < a; i++) {
            sum = (val2 + val1) % 15746;	// 이전 값과 이전의 이전 값의 합
            val1 = val2;	// 이전의 이전 값은 이전 값으로 변경
            val2 = sum;		// 이전 값은 현재 합 값으로 변경
        }

        return sum;

    }

}
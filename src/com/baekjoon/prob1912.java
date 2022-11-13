package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob1912 {
}

/*
결과 
    - 메모리 : 32356
    - 시간 : 688
    - 시간복잡도 : 
*/
//Bufferd
class prob1912_1 {
    static int[] arr; //입력 숫자들을 저장하는 배열
    static Integer[] dp; //메모이제이션 할 dp : dp[i]는 [i] 까지의 연속합 최대값을 의미
    static int max; // 최대값 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        max = arr[0];

        recur(N - 1);
        System.out.println(max);

    }

    private static int recur(int N) {
        if (dp[N] == null){
            dp[N] = Math.max(recur(N-1) + arr[N], arr[N]);

            max = Math.max(dp[N], max);

        }

        return dp[N];
    }
}

/*
결과 
    - 메모리 : 24152
    - 시간 : 280
    - 시간복잡도 : 
*/
class prob1912_2 {
    static int[] arr; //입력 숫자들을 저장하는 배열
    static int[] dp; //메모이제이션 할 dp : dp[i]는 [i] 까지의 연속합 최대값을 의미
    static int max; // 최대값 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];


        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        max = arr[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);

            max = Math.max(max, dp[i]);
        }

        System.out.println(max);


    }
}
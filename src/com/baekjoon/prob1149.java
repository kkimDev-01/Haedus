package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob1149 {
}


/*
결과 
    - 메모리 : 14600
    - 시간 : 136
    - 시간복잡도 : 
*/
class prob1149_1 {

    final static int RED = 0;
    final static int GREEN = 1;
    final static int BLUE = 2;

    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3];

        StringTokenizer st;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            cost[i][RED] = Integer.parseInt(st.nextToken());
            cost[i][GREEN] = Integer.parseInt(st.nextToken());
            cost[i][BLUE] = Integer.parseInt(st.nextToken());
            
        }

        dp[0][RED] = cost[0][RED];
        dp[0][GREEN] = cost[0][GREEN];
        dp[0][BLUE] = cost[0][BLUE];

        System.out.println(Math.min(paintCost(N- 1, RED), 
                            Math.min(paintCost(N - 1, GREEN), paintCost(N - 1, BLUE))
                            )
                            );
        

    }

    private static int paintCost(int N, int color) {

        if (dp[N][color] == 0){
            switch (color) {
                case RED :
                    dp[N][RED] = Math.min(paintCost(N - 1, GREEN), paintCost(N - 1, BLUE)) + cost[N][RED];
                    break;
                case GREEN :
                    dp[N][GREEN] = Math.min(paintCost(N - 1, RED), paintCost(N - 1, BLUE)) + cost[N][GREEN];
                    break;
                case BLUE :
                    dp[N][BLUE] = Math.min(paintCost(N - 1, GREEN), paintCost(N - 1, RED)) + cost[N][BLUE];
                    break;
            }

        }

        return dp[N][color];
    }
}


/*
결과 
    - 메모리 : 14496
    - 시간 : 140
    - 시간복잡도 : 
*/

class prob1149_2 {

    final static int RED = 0;
    final static int GREEN = 1;
    final static int BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] cost;
        cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            cost[i][RED] = Integer.parseInt(st.nextToken());
            cost[i][GREEN] = Integer.parseInt(st.nextToken());
            cost[i][BLUE] = Integer.parseInt(st.nextToken());

        }

        for (int i = 1; i < N; i++) {
            cost[i][RED] += Math.min(cost[i - 1][GREEN], cost[i - 1][BLUE]);
            cost[i][GREEN] += Math.min(cost[i - 1][RED], cost[i - 1][BLUE]);
            cost[i][BLUE] += Math.min(cost[i - 1][RED], cost[i - 1][GREEN]);
        }

        System.out.println(Math.min(Math.min(cost[N - 1][RED],
                                            cost[N - 1][GREEN]),
                                            cost[N - 1][BLUE]
                                    )
                            );


    }

}
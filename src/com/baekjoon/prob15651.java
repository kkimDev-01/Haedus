package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/15651
해설 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob15651 {
}

/*
결과 
    - 메모리 : 68480
    - 시간 : 468
    - 시간복잡도 : 
*/
class prob15651_1 {

    public static int N;
    public static int M;
    public static int[] arr;
    public static boolean[] visit;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmpArr = br.readLine().split(" ");

        N = Integer.parseInt(tmpArr[0]);
        M = Integer.parseInt(tmpArr[1]);

        arr = new int[M];
        visit = new boolean[N];

        dfs(0);
        System.out.println(sb);

    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++){
            arr[depth] = i;
            dfs( depth + 1);
        }

    }


}
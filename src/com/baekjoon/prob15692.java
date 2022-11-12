package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
문제 : https://www.acmicpc.net/status?user_id=kkheentech&problem_id=15652&from_mine=1
해설 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob15692 {
}

/*
결과 
    - 메모리 : 16596
    - 시간 : 152
    - 시간복잡도 : 
*/
class prob15692_1 {

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

        dfs(1,0);
        System.out.println(sb);

    }

    private static void dfs(int at, int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = at; i <= N; i++){
            arr[depth] = i;
            dfs(i, depth + 1);
        }

    }


}
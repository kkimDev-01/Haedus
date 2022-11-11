package com.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/9184
해설 : https://st-lab.tistory.com/190
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob9184 {
}


/*
결과 
    - 메모리 : 26884
    - 시간 : 292
    - 시간복잡도 : 
*/
class prob9184_1 {

    static int[][][] dp = new int[21][21][21];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while(true) {
            String[] strArr = br.readLine().split(" ");

            int a = Integer.parseInt(strArr[0]);
            int b = Integer.parseInt(strArr[1]);
            int c = Integer.parseInt(strArr[2]);

            if (a == -1 && b == -1 && c == -1){
                break;
            }

            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w(a, b, c)).append("\n");

        }
        System.out.println(sb);

    }

    private static int w(int a, int b, int c) {

        if(inRange(a,b,c) && dp[a][b][c] != 0){
            return dp[a][b][c];
        }

        if (a<= 0 || b <= 0 || c <= 0){
            return 1;
        }

        if (a > 20 || b > 20 || c > 20){
            return dp[20][20][20] = w(20, 20, 20);
        }

        if (a < b && b < c) {
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }


        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    private static boolean inRange(int a, int b, int c) {

        return 0 <= a && a <= 20 && 0 <= b && b <= 20 && 0 <= c && c <= 20;
    }
}

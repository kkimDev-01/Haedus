package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/9663
해설 : https://st-lab.tistory.com/118
정리내용(블로그)
    -  :
    -  :
    -  :
*/
public class prob9663 {
}

/*
결과
    - 메모리 : 14464
    - 시간 : 5304
    - 시간복잡도 :
*/
class prob9663_1 {
    
    public static int[] arr;
    public static int N;
    public static int count = 0;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);

        System.out.println(count);
        
    }

    private static void nQueen(int depth) {
        
        if (depth == N) {
            count++;
            return;
        }
        
        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (possibility(depth)) {
                nQueen(depth+1);
            }
        }
    }

    private static boolean possibility(int col) {
        for (int i = 0; i < col; i++) {
            if (arr[col] == arr[i]){
                return false;
            } else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }

        }
        return true;
    }
}

package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/14888
해설 : https://st-lab.tistory.com/121
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob14888 {
}

/*
결과 
    - 메모리 : 15548
    - 시간 : 132
    - 시간복잡도 : 
*/
class prob14888_1 {
    public static int MIN = Integer.MAX_VALUE;
    public static int MAX = Integer.MIN_VALUE;
    public static int[] operator = new int[4];
    public static int[] number;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        number = new int[N];

        int i = 0;
        for (String item : br.readLine().split(" ")){
            number[i] = Integer.parseInt(item);
            i++;
        }
        i = 0;
        for (String item : br.readLine().split(" ")){
            operator[i] = Integer.parseInt(item);
            i++;
        }

        dfs(number[0], 1);
        System.out.println(MAX);
        System.out.println(MIN);

    }

    private static void dfs(int num, int idx) {

        if (idx == N){
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for (int i = 0; i < 4; i++){
            if (operator[i] > 0){
                operator[i]--;

                switch (i) {
                    case 0 :
                        dfs(num + number[idx], idx + 1);
                        break;
                    case 1 :
                        dfs(num - number[idx], idx + 1);
                        break;
                    case 2 :
                        dfs(num * number[idx], idx + 1);
                        break;
                    case 3 :
                        dfs(num / number[idx], idx + 1);
                        break;
                }

                operator[i]++;
            }

        }

    }

}
package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/10872
해설 : https://st-lab.tistory.com/93
정리내용(블로그)
    - 재귀
    -  : 
    -  : 
*/
public class prob10872 {
}

/*
결과
    - 메모리 : 14180
    - 시간 : 120
    - 시간복잡도 : 
*/
// 재귀 + BufferedReader
class prob10872_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int sum = factorial(N);

        System.out.println(sum);



    }

    private static int factorial(int N) {
        if (N <= 1) return 1;   // 재귀 종료조건
        return N * factorial(N - 1);

    }
}

/*
결과 
    - 메모리 : 14124
    - 시간 : 124
    - 시간복잡도 :
*/
class prob10872_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int sum = 1;
        
        while ( N != 0){
            sum = sum * N;
            N--;
        }

        System.out.println(sum);

    }
}
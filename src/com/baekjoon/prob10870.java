package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/10870
해설 : https://st-lab.tistory.com/94, https://st-lab.tistory.com/123
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob10870 {
}

/*
결과
    - 메모리 : 14200
    - 시간 : 124
    - 시간복잡도 :
*/
// 재귀 + BufferedReader
class prob10870_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(fibonacci(N));


    }

    private static int fibonacci(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;

        return fibonacci(N - 1) + fibonacci(N - 2);
    }
}

/*
결과
    - 메모리 :
    - 시간 :
    - 시간복잡도 :
*/
// 배열 + BufferedReader (메모이제이션)
class prob10870_2 {

    static long[] arr;   //main과 main 내부의 fib 메서드가 공유할 수 있게, 밖에서 선언언

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new long[N + 1];   // F(0)부터 시작하므로 N + 1 크기 생성

        for (int i = 0; i < N+1; i++) {
            arr[i] = -1;
        }                               // -1로 초기화 (0번째가 0이라서)

        arr[0] = 0;     //0번째 (베이직 케이스)
        arr[1] = 1;     //1번째 (베이직 케이스)

       System.out.println(fibMemoization(N));

    }

    private static long fibMemoization(int N) {
       if (arr[N] == -1) {
           arr[N] = fibMemoization(N - 1) + fibMemoization(N - 2);      //메모이제이션 : 기존에 없던 것들만 등록작업
       }
        return arr[N];
    }

}
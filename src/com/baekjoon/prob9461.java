package com.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/9461
해설 : https://st-lab.tistory.com/127
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob9461 {
}

/*
결과 
    - 메모리 : 14176
    - 시간 : 124
    - 시간복잡도 : 
*/
class prob9461_1 {

    public static Long[] seq = new Long[101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        seq[0] = 0L;
        seq[1] = 1L;
        seq[2] = 1L;
        seq[3] = 1L;

        int T = Integer.parseInt(br.readLine());

        while(T > 0) {
            sb.append(padovan(Integer.parseInt(br.readLine()))).append("\n");

            T--;
        }
        System.out.println(sb);


    }

    private static Long padovan(int N) {
        if (seq[N] == null) {
            seq[N] = padovan(N - 2) + padovan(N - 3);
        }
        return seq[N];
    }
}

class prob9461_2 {
    public static Long[] seq = new Long[101];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        padovan();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i<T; i++) {
            sb.append(seq[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.println(sb);
    }

    private static void padovan() {
        seq[1] = 1L;
        seq[2] = 1L;
        seq[3] = 1L;

        for (int i = 4; i < 101; i++){
            seq[i] = seq[i - 2] + seq[i - 3];
        }
    }
}

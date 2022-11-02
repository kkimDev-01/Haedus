package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
문제 : https://www.acmicpc.net/problem/2447
해설 : https://st-lab.tistory.com/95
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob2447 {
}

class prob2447_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        sb = drawStar(N);


    }

    private static StringBuilder drawStar(int N) {


        if (N == 3){
            StringBuilder sb = new StringBuilder();
            return  sb.append("***").append("\n").append("* *").append("\n").append("***");
        }

        StringBuilder s = drawStar(N/3);
        StringBuilder ss = new StringBuilder();

        return ss.append(s).append(s).append(s).append("\n").append(s).append(" ").append("***");
    }
}

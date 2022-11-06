package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/1085
해설 : https://st-lab.tistory.com/86
정리내용(블로그)
    -  :
    -  :
    -  :
*/
public class prob1085 {
}

class prob1085_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);
        int w = Integer.parseInt(arr[2]);
        int h = Integer.parseInt(arr[3]);

        int x_min = Math.min(x, w - x);
        int y_min = Math.min(y, h - y);

        System.out.println(Math.min(x_min, y_min));

    }
}
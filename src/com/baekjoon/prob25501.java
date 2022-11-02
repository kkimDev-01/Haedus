package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/25501
해설 : 오리지널
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob25501 {
}

/*
결과 
    - 메모리 : 19736
    - 시간 : 240
    - 시간복잡도 : 
*/

//BufferedReader + StringBuilder
class prob25501_1 {

    static int callNum = 0;
    public static int recursion(String s, int l, int r){
        callNum++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            callNum = 0;
            sb.append(isPalindrome(br.readLine())).append(" ").append(callNum).append("\n");
        }

        System.out.println(sb);
    }
}
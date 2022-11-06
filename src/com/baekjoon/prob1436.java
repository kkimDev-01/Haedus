package com.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/1436
해설 : https://st-lab.tistory.com/103
정리내용(블로그)
    -  :
    -  :
    -  : 
*/
public class prob1436 {
}

/*
결과
    - 메모리 : 86428
    - 시간 : 300
    - 시간복잡도 :
*/
//brute force로 풀자
//해설의 아이디어는 '실용적'이지 못함
class prob1436_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int num = 666;

        int count = 1;

        while (count != N) {
            num ++;
            if (String.valueOf(num).contains("666")){
                count++;
            }
        }

        System.out.println(num);




    }

}

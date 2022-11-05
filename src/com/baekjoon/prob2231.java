package com.baekjoon;



/*
문제 : https://www.acmicpc.net/problem/2231
해설 : https://st-lab.tistory.com/98
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 생성자 <-> 분해합
// 분해합이 주어질 때, 가장 작은 생성자를 구해라
public class prob2231 {
}


/*
결과 
    - 메모리 : 14348
    - 시간 : 148	
    - 시간복잡도 : 
*/
class prob2231_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int result = 0;

        for (int i = 0; i < N; i++){
            int number = i;
            int sum = 0;

            while (number != 0){
                sum += number % 10;
                number /= 10;
            }

            if (sum + i == N) {
                result = i;
                break;
            }


        }

        System.out.println(result);


    }
}


/*
결과 
    - 메모리 : 14160
    - 시간 : 128
    - 시간복잡도 : 
*/
//brute force(무식하게 하나씩 조회하며 찾기)는 피할 수 없다
//그러나 조회 범위를 줄일 수는 있다! == 똑똑
class prob2231_2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str_N = br.readLine();

        int N_len = str_N.length();

        int N = Integer.parseInt(str_N);

        int result = 0;

        for (int i = (N - (N_len * 9)); i < N; i++) {
            int number = i;
            int sum = 0;

          while (number != 0) {
            sum += number % 10;
            number /= 10;
        }

        if (sum + i == N) {
            result = i;
            break;
        }

    }
        System.out.println(result);

    }
}

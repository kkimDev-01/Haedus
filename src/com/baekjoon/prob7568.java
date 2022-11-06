package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/7568
해설 : https://st-lab.tistory.com/99
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob7568 {
}

/*
결과 
    - 메모리 : 14200
    - 시간 : 128
    - 시간복잡도 : 
*/
//BufferedReader + StringBuilder
class prob7568_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        String[] str;

        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");

            arr[i][0] = Integer.parseInt(str[0]);
            arr[i][1] = Integer.parseInt(str[1]);

        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++){
            int rank = 1;

            for (int j = 0; j < N; j++){

                if (i == j) continue;

                if (arr[i][0] < arr[j][0]
                    && arr[i][1] < arr[j][1]){
                    rank++;
                }

            }

            sb.append(rank).append(" ");
            
        }

        System.out.println(sb);

    }
}



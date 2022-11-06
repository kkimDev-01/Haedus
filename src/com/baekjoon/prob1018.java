package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/1018
해설 : https://st-lab.tistory.com/101
정리내용(블로그)
    -  사전 브레인스토밍
        - 숲
            - 8 * 8로 잘라서 생기는 경우들을 저장
            - 각 경우별 칠해야 하는 사각형 횟수 세기 : O(64), 1행1열이 B인지 W인지에 따라 기준이 됨
*/

/*
결과
    - 메모리 : 14392
    - 시간 : 124
    - 시간복잡도 :
    - 특징
        - static 변수를 class 스코프에서 사용해서 전역으로 접근해서 update되는 min으로 설정
        - 첫번째 칸 색깔 기준, 뒤집은 경우를 생각해야 하는, 문제의 포인트
        - TF를 다음 반복문(행 또는 열) 이전, 해당 반복문의 마지막에 바꿔서 다음 반복문에서 반대값으로 비교할 수 있게 함
*/
public class prob1018 {
}

class  prob1018_1 {

    public static boolean[][] arr;
    public static int min = 64;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        arr = new boolean[N][M];

        for (int i = 0; i < N; i++){
            String strr = br.readLine();

            for (int j = 0; j < M; j++){
                if (strr.charAt(j) == 'W'){
                    arr[i][j] = true;
                } else {
                    arr[i][j] = false;
                }
            }
        }


        int N_row = N - 7;
        int M_col = M - 7;

        for (int i = 0; i < N_row; i++){
            for (int j=0; j <M_col; j++) {
                find(i, j);
            }
        }

        System.out.println(min);

    }

    private static void find(int x, int y) {
        int end_x = x + 8;
        int end_y = y + 8;
        int count = 0;

        boolean TF = arr[x][y];

        for (int i = x; i < end_x; i++){
            for (int j = y; j < end_y; j++){

                if (arr[i][j] != TF) {
                    count++;

                }

                TF = (!TF);

            }

            TF = (!TF);
        }

        count = Math.min(count, 64 - count);

        min = Math.min(min, count);

    }
}

package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/2477
해설 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob4153 {
}


/*
결과 
    - 메모리 : 14224
    - 시간 : 128
    - 시간복잡도 : 
*/
//다이얼처럼 생각. 내가 원하는 위치에 다이얼이 오도록 다이얼을 돌린다.
class prob4153_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int multiplier = Integer.parseInt(br.readLine());

        int x_max = 0;
        int x_idx = 0;
        int y_max = 0;
        int y_idx = 0;
        int[] valArr = new int[6];

        for (int i = 0; i < 6; i++){
            String[] arr = br.readLine().split(" ");
            int value = Integer.parseInt(arr[1]);
            valArr[i] = value;

            if (arr[0].equals("1") || arr[0].equals("2")){
                if (x_max < value){
                    x_max = value;
                    x_idx = i;
                }
            } else {
                if (y_max < value){
                    y_max = value;
                    y_idx = i;
                }
            }

        }

        while (x_idx >1 || y_idx > 1) {
            int first = valArr[0];

            for (int i = 1; i < 6; i++){
                valArr[i - 1] = valArr[i];
            }
            valArr[5] = first;

            x_idx = (x_idx == 0) ? 5 : x_idx -1;
            y_idx = (y_idx == 0) ? 5 : y_idx -1;


        }

        int square = valArr[0] * valArr[1] - valArr[3] * valArr[4];

        System.out.println(multiplier*(square));

    }

}

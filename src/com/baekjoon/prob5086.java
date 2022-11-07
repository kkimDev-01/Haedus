package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
문제 : https://www.acmicpc.net/problem/5086
해설 : https://st-lab.tistory.com/149
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob5086 {
}

/*
결과 
    - 메모리 : 14080
    - 시간 : 124
    - 시간복잡도 : 
*/
class prob5086_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {

            String[] arr = br.readLine().split(" ");

            if (arr[0].equals("0")){
                break;
            }

            int first = Integer.parseInt(arr[0]);
            int second = Integer.parseInt(arr[1]);


            //나누기 : 예외처리 필수
            if (first == 0 || second == 0){
                sb.append("neither").append("\n");
                continue;
            }

            if (second % first == 0){
                sb.append("factor").append("\n");
            } else if (first % second == 0) {
                sb.append("multiple").append("\n");
            }else{
                sb.append("neither").append("\n");

            }


        }

        System.out.println(sb);

    }
}
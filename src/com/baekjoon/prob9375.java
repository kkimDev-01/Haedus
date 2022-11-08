package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
문제 : https://www.acmicpc.net/problem/9375
해설 : https://st-lab.tistory.com/164
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob9375 {
}

/*
결과 
    - 메모리 : 14340
    - 시간 : 132
    - 시간복잡도 : 
*/
//BufferedReader
class prob9375_1 {
    public static void main(String[] args) throws IOException {



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i<N; i++){

            HashMap<String, Integer> map = new HashMap<>();

            int M = Integer.parseInt(br.readLine());

            for (int j = 0; j < M; j++){
                String[] strArr = br.readLine().split(" ");

                if (!map.containsKey(strArr[1])){
                    map.put(strArr[1], 1);
                } else {
                    map.put(strArr[1], map.get(strArr[1]) + 1);
                }

            }

            int res = 1;

            for (String key : map.keySet()){
                res *= (map.get(key) + 1);
            }

            sb.append(res - 1).append("\n");

        }

        System.out.println(sb);
    }
}

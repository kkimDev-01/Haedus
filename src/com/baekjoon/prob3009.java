package com.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
문제 : https://www.acmicpc.net/problem/3009
해설 :
정리내용(블로그)
    -  :
    -  :
    -  :
*/
public class prob3009 {
}

/*
결과 
    - 메모리 : 14192
    - 시간 : 120
    - 시간복잡도 : 
*/
class prob3009_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> x_map = new HashMap<>();
        Map<String, Integer> y_map = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            String[] arr = br.readLine().split(" ");

            if (x_map.containsKey(arr[0])){
                x_map.put(arr[0], x_map.get(arr[0]) + 1);
            }else {
                x_map.put(arr[0], 1);
            }

            if (y_map.containsKey(arr[1])){
                y_map.put(arr[1], y_map.get(arr[1]) + 1);
            }else {
                y_map.put(arr[1], 1);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String key : x_map.keySet()){
            if (x_map.get(key) == 1){
                sb.append(key).append(" ");
                break;
            }
        }
        for (String key : y_map.keySet()){
            if (y_map.get(key) == 1){
                sb.append(key);
                break;
            }
        }

        System.out.println(sb);
    }
}

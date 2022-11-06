package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
문제 : https://www.acmicpc.net/problem/10816
해설 : https://st-lab.tistory.com/267
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob10816 {
}

/*
결과 
    - 메모리 : 165208
    - 시간 : 1156
    - 시간복잡도 : 
*/
class prob10816_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        br.readLine();

        Map<String, Integer> map = new HashMap<>();

        for (String item : br.readLine().split(" ")){
            if (map.containsKey(item)){
                map.put(item, map.get(item) + 1);
            }else{
                map.put(item, 1);
            }

        }

        br.readLine();

        for (String item : br.readLine().split(" ")){
            if (map.containsKey(item)){
                sb.append(map.get(item)).append(" ");
            }else {
                sb.append(0).append(" ");
            }

        }

        System.out.println(sb);

    }
}

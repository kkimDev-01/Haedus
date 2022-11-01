package com.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
문제 : https://www.acmicpc.net/problem/18870
해설 : https://st-lab.tistory.com/279
결과 
    - 메모리 : 
    - 시간 : 
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob18870 {
}

/*
문제 : 
해설 : 
결과 
    - 메모리 : 376968
    - 시간 : 3176
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
// 1. Scanner + StringBuilder
class prob18870_1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[] origin = new int[N];
        int[] sorted = new int[N];

        HashMap<Integer, Integer> rankingMap = new HashMap<>(); //rank를 매길 HashMap

        for (int i = 0; i < N; i++) {
            //정렬할 배열과 원본 배열에 값을 넣어준다.
            sorted[i] = origin[i] = in.nextInt();
        }

        //정렬
        Arrays.sort(sorted);

        //정렬된 배열을 순회하면서 map에 넣어준다.
        int rank = 0;
        for (int v : sorted) {
            /*
            만약 이미 해당 원소의 순위가 매겨졌다면 이것은 중복이 되는 경우이기 떄문에 넘어가야 한다.
             */
            if (!rankingMap.containsKey(v)){
                rankingMap.put(v, rank);
                rank++;
            }

        }

        StringBuilder sb = new StringBuilder();

        for (int key : origin){
            int ranking = rankingMap.get(key);
            sb.append(ranking).append(" ");
        }

        System.out.println(sb);

    }
}

/*
문제 : 
해설 : 
결과 
    - 메모리 : 255440
    - 시간 : 1876
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
//방법 2 : [BufferedReader + StringBuilder]
class prob18870_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] origin = new int[N];
        int[] sorted = new int[N];
        HashMap<Integer, Integer> rankingMap = new HashMap<>(); //rank를 매길 HashMap


        String[] st = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            //정렬할 배열과 원본 배열에 값을 넣어준다.
            sorted[i] = origin[i] = Integer.parseInt(st[i]);
        }

        // 정렬 할 배열에 대해 정렬을 수행해준다.
        Arrays.sort(sorted);

        //정렬된 배열을 순회하면서 map에 넣어준다.
        int rank = 0;
        for (int v : sorted) {
            /*
            만약 이미 해당 원소의 순위가 매겨졌다면 이것은 중복이 되는 경우이기 떄문에 넘어가야 한다.
             */
            if (!rankingMap.containsKey(v)){
                rankingMap.put(v, rank);
                rank++;
            }

        }

        StringBuilder sb = new StringBuilder();

        for (int key : origin){
            int ranking = rankingMap.get(key);
            sb.append(ranking).append(" ");
        }

        System.out.println(sb);

    }
}
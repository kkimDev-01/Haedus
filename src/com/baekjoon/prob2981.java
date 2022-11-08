package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/*
문제 : https://www.acmicpc.net/problem/2981
해설 : https://st-lab.tistory.com/155
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob2981 {
}

/*
결과 
    - 메모리 : 15048
    - 시간 : 824
    - 시간복잡도 : 
*/
class prob2981_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int gcdVal = arr[1] - arr[0];

        for (int i = 2; i < N; i++){
            gcdVal = gcd(gcdVal, arr[i] - arr[i - 1]);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <= gcdVal / 2; i++){
            if (gcdVal % i == 0){
                sb.append(i).append(" ");
            }
        }

        sb.append(gcdVal);

        System.out.println(sb);
    }

    private static int gcd(int a, int b) {

        while(b!= 0){
            int r = a % b;
            a = b;
            b = r;

        }

        return a;

    }
}

/*
결과
    - 메모리 : 14164
    - 시간 : 124
    - 시간복잡도 :
*/
class prob2981_2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int gcdVal = arr[1] - arr[0];

        for (int i = 2; i < N; i++){
            gcdVal = gcd(gcdVal, arr[i] - arr[i - 1]);
        }

        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(gcdVal); i++){
            if (i*i == gcdVal){
                list.add(i);

            } else if (gcdVal % i == 0) {
                list.add(i);
                list.add(gcdVal / i);
            }

        }

        Collections.sort(list);

        for (int val : list){
            sb.append(val).append(' ');
        }

        sb.append(gcdVal);
        System.out.println(sb);

    }

    private static int gcd(int a, int b) {

        while(b!= 0){
            int r = a % b;
            a = b;
            b = r;

        }

        return a;
    }

}
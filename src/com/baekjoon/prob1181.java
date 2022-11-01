package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class prob1181 {
}

/*
문제 : https://www.acmicpc.net/problem/1181
해설 : https://st-lab.tistory.com/112
결과 
    - 메모리 : 
    - 시간 : 
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/


/*
문제 :
해설 :
결과
    - 메모리 : 58440
    - 시간 : 876
    - 시간복잡도 :
정리내용(블로그)
    -  :
    -  :
    -  :
*/
//1. Scanner + System.out.println
class prob1181_1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        String[] arr = new String[N];

        in.nextLine();   //개행 버림 : Scanner의 next 계열로 입력을 받은 뒤 nextLine()을 쓰면 개행(\n)이 저장되므로, 이 점에 유의하여 입력되는 개행을 한 번 버려야 함 (두 메소드의 메커니즘이 달라서 발생하는 에러)

        for (int i = 0; i < N; i++) {
            arr[i] = in.nextLine();
        }

        Arrays.sort(arr, (s1, s2) -> {
            //단어의 길이가 같은 경우
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);       //compareTo : 단어의 사전순 정렬
            }
            //그외의 경우
            return s1.length() - s2.length();
        });

        System.out.println(arr[0]);

        for (int i = 1; i < N; i++) {
            //중복되지 않은 단어만 출력
            if (!arr[i].equals(arr[i-1])){
                System.out.println(arr[i]);
            }
        }

    }
}

/*
문제 :
해설 : 
결과 
    - 메모리 : 60932
    - 시간 : 624
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
// 2. [Scanner + StringBuilder]
class prob1181_2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        String[] arr = new String[N];

        in.nextLine();   //개행 버림 : Scanner의 next 계열로 입력을 받은 뒤 nextLine()을 쓰면 개행(\n)이 저장되므로, 이 점에 유의하여 입력되는 개행을 한 번 버려야 함 (두 메소드의 메커니즘이 달라서 발생하는 에러)

        for (int i = 0; i < N; i++) {
            arr[i] = in.nextLine();
        }

        Arrays.sort(arr, (s1, s2) -> {
            //단어의 길이가 같은 경우
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);       //compareTo : 단어의 사전순 정렬
            }
            //그외의 경우
            return s1.length() - s2.length();
        });

        StringBuilder sb = new StringBuilder();

        sb.append(arr[0]).append('\n');

        for (int i = 1; i < N; i++) {
            //중복되지 않는 단어만 출력
            if (!arr[i].equals(arr[i-1])){
                sb.append(arr[i]).append('\n');
            }
        }

        System.out.println(sb);

    }
}

/*
문제 : 
해설 : 
결과 
    - 메모리 : 26604
    - 시간 : 356
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
//3. [BufferedReader + StringBuilder] 
class prob1181_3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, (s1, s2) -> {
            //단어의 길이가 같은 경우
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);       //compareTo : 단어의 사전순 정렬
            }
            //그외의 경우
            return s1.length() - s2.length();
        });

        StringBuilder sb = new StringBuilder();

        sb.append(arr[0]).append('\n');

        for (int i = 1; i < N; i++) {
            //중복되지 않는 단어만 출력
            if (!arr[i].equals(arr[i-1])){
                sb.append(arr[i]).append('\n');
            }
        }

        System.out.println(sb);
        
    }
}
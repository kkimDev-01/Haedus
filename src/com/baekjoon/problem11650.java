package com.baekjoon;


/*
문제 : https://www.acmicpc.net/problem/11650
해설 : https://st-lab.tistory.com/110
결과 
    - 메모리 :
    - 시간 :
    - 시간복잡도 :
정리내용(블로그)
    - Arrays.sort()의 Comparator 인자를 람다식으로 구현
    -  : 
    -  : 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class problem11650 {
}

/*
문제 :
해설 :
결과
    - 메모리 : 191636
    - 시간 : 2028
    - 시간복잡도 : n log2(n)
정리내용(블로그)
    -  :
    -  :
    -  :
*/
// 1. Scanner + System.out.println()
class problem11650_1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[][] arr = new int[N][2];    //N행 2열

        for (int i = 0; i < N; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        Arrays.sort(arr, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e1[1] - e2[1];   //오름차순 정렬
            }
            return e1[0] - e2[0];
        });

        for (int i = 0; i < N; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }

        
    }
}


/*
문제 : 
해설 : 
결과 
    - 메모리 : 190568
    - 시간 : 1460
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
class problem11650_2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[][] arr = new int[N][2];    //N행 2열

        for (int i = 0; i < N; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        Arrays.sort(arr, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e1[1] - e2[1];   //오름차순 정렬
            }
            return e1[0] - e2[0];
        });

        // 이전과 동일

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(arr[i][0] + " " + arr[i][1]).append('\n');
        }
        System.out.println(sb);


    }
}

/*
문제 : 
해설 : 
결과 
    - 메모리 : 55436
    - 시간 : 876
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
//방법 3  :  [BufferedReader + StringBuilder]
//문자열 분리는 StringTokenizer 을 사용
class problem11650_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e1[1] - e2[1];   //오름차순 정렬
            }
            return e1[0] - e2[0];
        });

        // 이전과 동일

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(arr[i][0] + " " + arr[i][1]).append('\n');
        }
        System.out.println(sb);

    }
}
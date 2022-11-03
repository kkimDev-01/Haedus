package com.baekjoon;

import java.io.*;

/*
문제 : https://www.acmicpc.net/problem/11729
해설 : https://st-lab.tistory.com/96
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob11729 {
}


/*
결과
    - 메모리 : 71180
    - 시간 : 532
    - 시간복잡도 :
*/
//BufferedReader + StringBuilder
class Main_1 {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        sb.append((int) (Math.pow(2, N) - 1)).append('\n');   //하노이 탑의 해

        Hanoi(N, 1, 2, 3);      //static으로 선언하면, 변수를 넘겨주지 않아도, 해당 클래스 내의 다른 스태틱 메서드에서 수정이 가능

        System.out.println(sb);



    }

    private static void Hanoi(int N, int start, int mid, int to) {
        if (N == 1){
            sb.append(start + " " + to + "\n");
            return;
        }

        // A -> C 로 옯긴다고 가정할 때
        // Step 1 : N-1 개를 A -> B 로 이동 (= start 지점의 N-1개의 원판을 mid 지점으로 옮긴다)
        Hanoi(N - 1, start, to, mid);

        // Step 2 : 1개를 A -> C로 이동 (= start 지점의 N번째 원판을 to지점으로 옮긴다.)
        sb.append(start + " " + to + "\n");

        // Step 3 :  N-1개를 B에서 C로 이동 (= mid 지점의 N-1개의 원판을 to 지점으로 옮긴다.)
        Hanoi(N - 1, mid, start, to);

    }
}


/*
결과 
    - 메모리 : 55180
    - 시간 : 472
    - 시간복잡도 : 
*/
//BufferedReader + BufferedWriter
class Main_2 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        bw.write((int) (Math.pow(2, N) - 1) + "\n");

        Hanoi(N, 1, 2, 3);

        bw.flush();
        bw.close();
    }

    public static void Hanoi(int N, int start, int mid, int to) throws IOException {
        // 이동할 원반의 수가 1개라면?
        if (N == 1) {
            bw.write(start + " " + to + "\n");
            return;
        }

        // A -> C로 옮긴다고 가정할 떄,
        // STEP 1 : N-1개를 A에서 B로 이동 (= start 지점의 N-1개의 원판을 mid 지점으로 옮긴다.)
        Hanoi(N - 1, start, to, mid);

        // STEP 2 : 1개를 A에서 C로 이동 (= start 지점의 N번째 원판을 to지점으로 옮긴다.)
        bw.write(start + " " + to + "\n");

        // STEP 3 : N-1개를 B에서 C로 이동 (= mid 지점의 N-1개의 원판을 to 지점으로 옮긴다.)
        Hanoi(N - 1, mid, start, to);

    }
    
}
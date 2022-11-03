package com.baekjoon;

import java.io.*;


/*
문제 : https://www.acmicpc.net/problem/2447
해설 : https://st-lab.tistory.com/95
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob2447 {
}


/*
결과 
    - 메모리 : 48040
    - 시간 : 352
    - 시간복잡도 :
*/
//BufferedReader + StringBuilder
class prob2447_1 {

    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        star(0, 0, N, false);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }

        System.out.print(sb);




    }

    private static void star(int x, int y, int N, boolean blank) {


        if (blank) {
            for (int i = x; i < x + N; i++){
                for (int j = y; j < y + N; j++){
                    arr[i][j] = ' ';
                }
            }
            return;
        }


        if (N == 1) {
            arr[x][y] = '*';
            return;
        }



        int size = N/3;

        int count = 0;

        for (int i = x; i < x + N; i += size){
            for (int j = y; j < y + N; j += size){
                count++;
                if (count == 5){
                    star(i, j, size, true);
                }else {
                    star(i, j, size, false);
                }
            }
        }



    }


}

/*
결과 
    - 메모리 : 24820
    - 시간 : 248
    - 시간복잡도 : 
*/
class prob2447_2 {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        star(0, 0, N, false);

        for (int i = 0; i < N; i++) {
            bw.write(arr[i]);       //출력
            bw.write("\n");     //출력
        }
        bw.flush();     //버퍼에 남아있는 데이터를 출력해 없앤 후
        bw.close();     //스트림을 닫는다.

    }

    private static void star(int x, int y, int N, boolean blank) {


        if (blank) {
            for (int i = x; i < x + N; i++){
                for (int j = y; j < y + N; j++){
                    arr[i][j] = ' ';
                }
            }
            return;
        }


        if (N == 1) {
            arr[x][y] = '*';
            return;
        }



        int size = N/3;

        int count = 0;

        for (int i = x; i < x + N; i += size){
            for (int j = y; j < y + N; j += size){
                count++;
                if (count == 5){
                    star(i, j, size, true);
                }else {
                    star(i, j, size, false);
                }
            }
        }



    }
}


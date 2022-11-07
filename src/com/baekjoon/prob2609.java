package com.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제 : https://www.acmicpc.net/problem/2609
해설 : https://st-lab.tistory.com/154
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob2609 {
}

/*
결과
    - 메모리 : 14308
    - 시간 : 124
    - 시간복잡도 :
*/
//재귀
class prob2609_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);

        int d = gcd(a, b); //최대공약수

        System.out.println(d);
        System.out.println(a * b / d);


    }

    private static int gcd(int a, int b) {
        if (b==0){
            return a;
        }

        return gcd(b, a % b);
    }
}

/*
결과
    - 메모리 : 14224
    - 시간 : 124
    - 시간복잡도 :
*/
//반복문
class prob2609_2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);

        int d = gcd(a, b); //최대공약수

        System.out.println(d);
        System.out.println(a * b / d);

    }

    private static int gcd(int a, int b) {

        while (b != 0){
            int r = a % b; //나머지
            a = b;
            b = r;
        }

        return a;
    }
}
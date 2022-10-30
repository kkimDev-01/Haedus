package com.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/*
문제 : https://www.acmicpc.net/problem/1427
    - 정렬하려고 하는 수 N이 주어진다.
    - 해당 수의 각 자리를 내림차순으로 정렬하라.

해설 : https://st-lab.tistory.com/109
 */

public class problem1427 {
}

/*
1. Scanner + 수학 연산 + 카운팅 정렬
    - 결과
        - 메모리 : 17748
        - 시간 : 204
        - 시간복잡도 : O(n) * 2
 */

class problem1427_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] counting = new int[10];

        int N = in.nextInt();

        while (N != 0){
            counting[N % 10]++; //마지막 자리에 해당 하는 수에 대한 카운팅
            N /= 10;    //마지막 수 날려버림
        }

        for (int i = 9; i >= 0; i--) {
            while (counting[i]-- > 0) {     //비교식 판단 후에 가감계산. while 문 안에 마지막에 counting[i]-- 하는 것과 동일
                System.out.print(i);
            }
        }

    }
}
/*
2. Scanner + toCharArray + Arrays.sort
 */
/*
문제 : 
해설 : 
결과 
    - 메모리 : 17788
    - 시간 : 208
    - 시간복잡도 : N log2(N)
정리내용(블로그)
    -  : .
    -  : 
    -  : 
*/
class problem1427_2 {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        char[] arr = in.nextLine().toCharArray();   //"123" -> ['1', '2', '3']

        Arrays.sort(arr);   //dual-pivot quick-sort : insertion sort + quick sort  => 블로그 정리 필요

        for (int i = arr.length - 1; i>= 0; i--) {
            System.out.print(arr[i]);
        }
    }
}

//3. BufferedReader + toCharArray + Arrays.sort
class problem1427_3 {
    public static void main(String[] args) {


    }
}

//4. BufferedReader + charAt + 카운팅 정렬
class problem1427_4 {
    public static void main(String[] args) {


    }
}

//5. InputStream + 카운팅 정렬
class problem1427_5 {
    public static void main(String[] args) {


    }
}

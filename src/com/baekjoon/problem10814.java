package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
문제 : https://www.acmicpc.net/problem/10814
해설 : https://st-lab.tistory.com/113
결과 
    - 메모리 : 
    - 시간 : 
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/

public class problem10814 {
}


/*
문제 :
해설 : 
결과 
    - 메모리 : 60508
    - 시간 : 844
    - 시간복잡도 : 
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
//방법 3 : [String[][] + BufferedReader + StringBuilder]
class problem10814_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[][] arr = new String[N][2];

        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split(" ");     //api문서를 보면 split 사용을 권장하고 있음
            arr[i][0] = st[0];
            arr[i][1] = st[1];
        }

        Arrays.sort(arr, (s1, s2) -> {
            return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        }

        System.out.println(sb);
    }
}

/*
문제 :
해설 :
결과
    - 메모리 : 59644
    - 시간 : 808
    - 시간복잡도 :
정리내용(블로그)
    -  :
    -  :
    -  :
*/
// 방법 5 : [Person[] + BufferedReader + StringBuilder]
class problem10814_2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Person[] p = new Person[N];

        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split(" ");
            int age = Integer.parseInt(st[0]);
            String name = st[1];
            p[i] = new Person(age, name);
        }

        Arrays.sort(p, (p1, p2) -> {
            return p1.age - p2.age;
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            //객체배열의 객체를 출력하면 해당 인덱스의 객체의 toString()이 출력됨.
            sb.append(p[i]);
        }
        System.out.println(sb);


    }

    private static class Person {

        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return age + " " + name + "\n";
        }
    }
}


/*
문제 :
해설 :
결과
    - 메모리 : 51620
    - 시간 : 520
    - 시간복잡도 : O(n)   (sort를 안쓰고 배열 인덱스 접근으로 sorting을 우회적으로 구현)
정리내용(블로그)
    -  :
    -  :
    -  :
*/
// 방법 7 : [StringBuilder[] + BufferedReader + StringBuilder]
class problem10814_3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        //입력되는 나이의 범위 : 1 ~ 200
        StringBuilder[] p = new StringBuilder[201];

        //객체배열의 인덱스에 각 StringBuilder 객체를 생성해준다.
        for (int i = 0; i < p.length; i++) {
            p[i] = new StringBuilder();
        }

        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split(" ");
            int age = Integer.parseInt(st[0]);
            String name = st[1];
            // 카운팅 정렬 : 나이를 index 로 하여 해당 배열에 나이와 이름을 append() 한다
            p[age].append(age).append(" ").append(name).append("\n");
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder val : p) {
            sb.append(val);
        }

        System.out.println(sb);

    }
}
package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
문제 : https://www.acmicpc.net/problem/10815
해설 :
참고 :
    - Collections.sort() in Java with Examples : https://www.geeksforgeeks.org/collections-sort-java-examples/
    - JAVA 람다식을 사용한 forEach 사용 방법 예제 : https://ponyozzang.tistory.com/406
    - Java 8 Stream : map 함수 - https://smlee729.wordpress.com/2016/08/28/java-8-stream-map-%ED%95%A8%EC%88%98/
정리내용(블로그)
    -  :
    -  :
    -  :
*/
public class prob10815 {
}

/*
결과 
    - 메모리 : 152592
    - 시간 : 1340
    - 시간복잡도 : 
*/
class prob10815_1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        List<Integer> whereToSearch = new ArrayList<>();

        for (String item : br.readLine().split(" ")){
            whereToSearch.add(Integer.parseInt(item));
        }

        br.readLine();

        List<Integer> whatToSearch = new ArrayList<>();

        for (String item : br.readLine().split(" ")){
            whatToSearch.add(Integer.parseInt(item));
        }


        Collections.sort(whereToSearch);

        StringBuilder sb = new StringBuilder();

        for (int item : whatToSearch){

            if (Collections.binarySearch(whereToSearch, item) < 0){
                sb.append(0).append(" ");
            } else {
                sb.append(1).append(" ");
            }

        }


        System.out.println(sb);

    }
}

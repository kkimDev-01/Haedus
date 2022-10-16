package com.baekjoon.level.sort;


import java.util.*;

public class prob2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfNum = scanner.nextInt();

        StringBuilder sb = new StringBuilder();

        List<Integer> numList = new ArrayList<>();

        for (int i = 0; i < numOfNum; i++){
            int num = scanner.nextInt();
            numList.add(num);
        }

        Collections.sort(numList);

        for (int num : numList){
            sb.append(num).append("\n");
        }
        System.out.println(sb);


    }
}

//StringBuilder.append로 모은 후 출력해야 함
//for문 내부에서 한줄씩 출력할 경우 시간초과 발생...

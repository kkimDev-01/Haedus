package com.baekjoon;

import java.util.*;

public class prob25305 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
//        int numPeople = Integer.parseInt(input.split(" ")[0]);
        int numWinner = Integer.parseInt(input.split(" ")[1]);
        String scores = sc.nextLine();
        List<String> scoreList = new ArrayList<>(Arrays.asList(scores.split(" ")));
        Collections.sort(scoreList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o2) - Integer.parseInt(o1);
            }
        });
        System.out.println(scoreList.get(numWinner-1));


    }

}

//Collections.sort에서 comparator 사용법
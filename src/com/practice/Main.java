package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class subMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        int BATCH_SIZE = 10;

        for (int i = 0; i < 112; i++){
            list.add(i);
        }

        List<Integer> subList = list.subList(100, list.size());

        for (int item : subList){
            System.out.println(item);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        // write your code here
        /*
        long start = 0;

        long n;

        for (int i = 0; i < 10; i++){
            n = start++;
            String str = Long.toString(n);
            System.out.println(str);

            System.out.println("Start = "+ start);
        }

        System.out.println(start);
        */

        List<Integer> list = new ArrayList<>();

        int BATCH_SIZE = 10;

        for (int i = 0; i < 112; i++){
            list.add(i);
        }

        int totalPage = list.size()/BATCH_SIZE + 1;

        for (int page = 0; page < totalPage; page++){

            int start = page * BATCH_SIZE;
            int end = (page + 1) * BATCH_SIZE;

            List<Integer> subList = list.subList(start, Math.min(end, list.size()));

            System.out.println(subList.get(0));
            System.out.println(subList.get(subList.size()-1));

        }


    }

}



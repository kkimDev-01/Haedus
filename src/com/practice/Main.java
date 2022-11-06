package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        StringBuilder sb = new StringBuilder();

        StringBuilder sb1 = new StringBuilder();

        sb.append("1");
        sb1.append("2");

        sb.append(sb1);

        System.out.println(sb);

        List<Integer> al = new ArrayList<Integer>();
        al.add(100);
        al.add(0);
        al.add(30);
        al.add(10);
        al.add(2);

        // The last parameter specifies the comparator
        // method used for sorting.
        int index = Collections.binarySearch(
                al, 50, Collections.reverseOrder());

        System.out.println("Found at index " + index);
    }

}

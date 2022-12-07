package com.leetcode.unclassified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge_Intervals {
    public static void main(String[] args) {
//        int[] s1 = {1, 3};
//        int[] s2 = {2, 6};
//        int[] s3 = {8, 10};
//        int[] s4 = {15, 18};
//
        int[] s1 = {1, 4};
        int[] s2 = {4, 5};
        int[][] input = new int[2][2];
        input[0] = s1;
        input[1] = s2;
//        input[2] = s3;
//        input[3] = s4;

        int[][] res = merge(input);

        for (int[] re : res) {
            System.out.println(re);
        }

    }

    public static int[][] merge(int[][] intervals) {

        if (intervals.length <= 1){
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if (o1[0] < o2[0]){
                    return -1;
                }else if (o1[0] == o2[0]){
                    return 0;
                }
                return 1;

            }
        });

        int[] prev = intervals[0];
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {

            //overlap
            if (prev[1] >= intervals[i][0]){
                int end = Math.max(prev[1], intervals[i][1]);
                prev[1] = end;

            } else {
                list.add(prev);
                prev = intervals[i];


            }

        }

        list.add(prev);

        int[][] res = new int[list.size()][2];

        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);

        }

        return res;

    }

}

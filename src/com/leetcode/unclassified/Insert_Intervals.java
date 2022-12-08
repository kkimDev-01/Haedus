package com.leetcode.unclassified;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Insert_Intervals {
    public static void main(String[] args) {

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> list = new ArrayList<>();

        for (int[] interval : intervals) {
            list.add(interval);
        }

        list.add(newInterval);

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] == o2[0]) {
                    return 0;
                }
                return 1;


            }
        });

        int[] prev = list.get(0);
        List<int[]> resList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {

            //overlap
            if (prev[1] >= list.get(i)[0]){
                int end = Math.max(prev[1], list.get(i)[1]);
                prev[1] = end;

            } else {
                resList.add(prev);
                prev = list.get(i);


            }

        }

        resList.add(prev);

        int[][] res = new int[resList.size()][2];

        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);

        }

        return res;


    }

}

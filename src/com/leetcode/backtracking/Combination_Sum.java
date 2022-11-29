package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_Sum {

    public static void main(String[] args) {

    }

    static List<List<Integer>> list;

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        list = new ArrayList<>();

        Arrays.sort(candidates);

        backtrack(new ArrayList<>(), candidates, target, 0);

        return list;

    }

    private static void backtrack(ArrayList<Integer> tempList, int[] candidates, int remain, int start) {

        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                backtrack(tempList, candidates, remain - candidates[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }

    }
}

package com.leetcode.two_pointers;

import java.util.*;

public class Three_Sum {
    public static void main(String[] args) {

        int[] nums = {-1,0,1,2,-1,-4};

        System.out.println(threeSum(nums));

    }

    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        int start = 0;
        int end = nums.length - 1;
        int target;

        HashMap<List<Integer>, Boolean> resMap = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> element;

        while (end - start > 1){

            target = -(nums[start] + nums[end]);

            for (int i = start+1; i < end; i++) {
                if (nums[i] == target){
                    element = new ArrayList<>();
                    element.add(nums[start]);
                    element.add(target);
                    element.add(nums[end]);

                    resMap.put(element, true);

                }
            }

            if((nums[end] - nums[end-1]) > (nums[start+1] - nums[start])) {
                start++;
            }else{
                end--;
            }


        }

        for (List<Integer> list : resMap.keySet()) {
            res.add(list);
        }

        return res;
    }
}

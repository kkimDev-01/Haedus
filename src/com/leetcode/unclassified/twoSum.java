package com.leetcode.unclassified;

import java.util.Arrays;

public class twoSum {
    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(twoSumSol(nums, target));


    }



    public static int[] twoSumSol(int[] nums, int target) {

        int[] numsClone = nums.clone();

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length-1;

        int idx1 = 0;
        int idx2 = 0;

        while (left < right) {
            if (nums[left] + nums[right] == target) {
                for (int i = 0; i < numsClone.length; i++) {
                    if (numsClone[i] == nums[left]) {
                        idx1 = i;
                    }
                }
                for (int i = numsClone.length -1; i >= 0; i--) {
                    if (numsClone[i] == nums[right]) {
                        idx2 = i;
                    }
                }
                break;
            }
            else if (nums[left] + nums[right] > target) {
                right--;
            }
            else {
                left++;
            }
        }

        int[] res = new int[2];
        res[0] = idx1;
        res[1] = idx2;

        return res;
    }
}

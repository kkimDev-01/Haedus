package com.leetcode.binary_search;

import java.util.Arrays;
import java.util.HashMap;

public class Search_in_Rotated_Sorted_Array {
    public static void main(String[] args) {

        int[] nums = {1};
        int target = 0;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1){
            return nums[0] == target ? 0 : -1;
        }

        int pivot = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]){
                pivot = i;
                break;
            }
        }

        int[] beforePivot = new int[pivot];
        int[] afterPivot = new int[nums.length - pivot];

        for (int i = pivot; i < nums.length; i++) {
            afterPivot[i - pivot] = nums[i];
        }

        for (int i = 0; i < pivot; i++){
            beforePivot[i] = nums[i];
        }

        int beforePivotRes = binarySearch(beforePivot, target);
        int afterPivotRes = binarySearch(afterPivot, target);


        return beforePivotRes >= 0? beforePivotRes : (afterPivotRes >= 0?  afterPivotRes+pivot : -1);
    }

    private static int binarySearch(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1){
            return nums[0] == target ? 0 : -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while(end - start > 1){

            int middle = (end+start)/2;

            if (nums[middle] == target){
                return middle;
            } else if (nums[middle] < target){
                start = middle;
            } else {
                end = middle;
            }

        }

        if (nums[end] == target){
            return end;
        } else if (nums[start] == target){
            return start;
        }

        return -1;
    }

}

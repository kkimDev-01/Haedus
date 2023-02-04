package com.practice;

import java.util.*;

public class ModifiedBinarySearch {

    public static void main(String[] args) {
        //[6,7,1,2,3,4,5] , 3
        //Integer[] arr = {6,7,1,2,3,4,5};
        Integer[] arr = {4};

        List<Integer> nums = new ArrayList<>(Arrays.asList(arr));

        System.out.println(binarySearchRotated(nums, 1));

    }




    public static int binarySearchRotated(List<Integer> nums, int target) {

        if (nums.size() == 0) {
            return -1;
        }

        if (nums.size() == 1) {
            return nums.get(0) != target ? -1 : 0;
        }

        int start = 0;
        int end = nums.size()-1;

        int res = -1;

        // Write your code here
        while(res == -1) {

            int mid = (start + end)/2;
            if (nums.get(start)<= nums.get(mid)){
                if (nums.get(start)<= target && target <= nums.get(mid)) {
                    res = binarySearchIndex(start, end, nums, target);
                }
                else {
                    start = mid+1;
                }
            }
            else {
                if (nums.get(mid) <= target && target <= nums.get(end)) {
                    res = binarySearchIndex(mid, end, nums, target);
                }
                else {
                    end = mid - 1;
                }

            }
        }

        return res;
    }

    private static int binarySearchIndex (int start, int end, List<Integer> nums, int target) {

        int mid = (start+end)/2;

        if (start == end && target != nums.get(start)) {
            return -1;
        }

        if (nums.get(mid) == target) {
            return mid;
        }

        if (nums.get(mid) > target) {
            return binarySearchIndex(start, mid-1, nums, target);
        }

        return binarySearchIndex(mid+1, end, nums, target);



    }


}

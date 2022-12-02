package com.leetcode.two_pointers;

public class Maximum_Subarray {

    /*
    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.
     */


    public static void main(String[] args) {

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));

    }


    public static int maxSubArray(int[] nums) {

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];
            max = Math.max(sum, max);

            if (sum < 0 ) {
                sum = 0;
            }

        }

        return max;

    }


    public static int maxSubArray_Failed(int[] nums) {

        int start = 0;
        int end = nums.length - 1;
        int curSum = 0;
        int max = 0;
        int round = 1;

        for (int num : nums) {
            max += num;
        }

        curSum = max;

        while (start < end){
            if (round % 3 == 1){

                max = Math.max(max, curSum -nums[start]);

            }else if (round % 3 == 2) {

                max = Math.max(max, curSum - nums[end]);

            }else {
                if (end - start > 1){
                    curSum -= (nums[start] + nums[end]);
                    max = Math.max(max, curSum);
                    start++;
                    end--;
                }else {
                    return max;
                }

            }
            round++;
        }

        return max;

    }//maxSubArray



}

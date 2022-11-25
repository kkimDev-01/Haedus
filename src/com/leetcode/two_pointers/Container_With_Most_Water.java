package com.leetcode.two_pointers;

public class Container_With_Most_Water {

    public static void main(String[] args) {

    }

    public int maxArea(int[] height) {

        int start = 0;
        int end = height.length-1;

        int max = 0;

        while (start < end) {
            max = Math.max((end - start) * Math.min(height[start], height[end]), max);

            if (height[start] > height[end]){
                end--;
            }else{
                start++;
            }

        }


        return max;
    }

}

package com.leetcode.two_pointers;

import java.util.*;

public class Three_Sum {
    public static void main(String[] args) {

        int[] nums = {-4,-1,-4,0,2,-2,-4,-3,2,-3,2,3,3,-4};

        System.out.println(threeSum3(nums));

    }
/*
    threeSum3 is my original

    Runtime: 53 ms, faster than 42.70% of Java online submissions for 3Sum.
    Memory Usage: 46.1 MB, less than 98.48% of Java online submissions for 3Sum.
*/

    public static List<List<Integer>> threeSum3(int[] nums) {

        Set<List<Integer>> resSet = new HashSet<>();
        Arrays.sort(nums);

        int left;
        int right;
        int sum;
        List<Integer> list;

        for (int i = 0; i < nums.length - 2; i++) {

            if (nums[i] > 0) break;

            if (nums[i] == 0 && nums[i+1] == 0 && nums[i+2] == 0){
                list = new ArrayList<>(Arrays.asList(nums[i], nums[i+1], nums[i+2]));
                resSet.add(list);
                break;
            }


            left = i+1;
            right = nums.length-1;

            while (left < right){

                while ((left+2 <= nums.length-1) && nums[left] == nums[left+1] && nums[left+1] == nums[left+2]){
                    left++;
                }
                while ((right-2 >= 0) && nums[right] == nums[right-1] && nums[right-1] == nums[right-2]){
                    right--;
                }

                sum = nums[i] + nums[left] + nums[right];

                if (sum > 0) {
                    right--;
                }else if (sum < 0) {
                    left++;
                }else {
                    list = new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right]));
                    Collections.sort(list);
                    resSet.add(list);
                    left++;
                    right--;
                }

/*                if (left == right){
                    if (nums[left-1] == nums[left]
                        ||(right + 1 <= nums.length-1 && nums[right] == nums[right+1])){
                        list = new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right]));
                        Collections.sort(list);
                        resSet.add(list);
                    }
                }*/

            }//while

        }//for

        return new ArrayList<>(resSet);

    }


    //Runtime: 47 ms, faster than 35.83% of Java online submissions for 3Sum.
    //Memory Usage: 60.1 MB, less than 32.20% of Java online submissions for 3Sum.
    //Two pointers
    //Time: O(N * LogN + N * N); Space : O(N + LogN)
    public static List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet();
        Arrays.sort(nums);

        for(int i = 0; i <= nums.length - 3 && nums[i] <= 0;){
            int left = i + 1, right = nums.length - 1;
            if (0 - nums[i] - nums[left] < 0) break;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) right--; //skip duplicated number
                } else {
                    if (sum == 0) {
                        resultSet.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        right--;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    }
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                }
            }
            i++;
            while(i < nums.length - 2 && nums[i] == nums[i - 1]) i++;
        }
        return new ArrayList<>(resultSet);
    }

    //nice try...
    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        HashMap<Integer, List<Integer>> vToIMap = new HashMap<>();
        HashMap<List<Integer>, Boolean> resMap = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            if (vToIMap.containsKey(nums[i])){
                vToIMap.get(nums[i]).add(i);
            }else {
                List<Integer> idxs = new ArrayList<>();
                idxs.add(i);
                vToIMap.put(nums[i], idxs);
            }
        }

        int start = 0;
        int end = nums.length - 1;
        int target;
        List<Integer> idxs;
        List<Integer> element;


        while (end - start > 1){

            target = -(nums[start] + nums[end]);


            if (vToIMap.containsKey(target)){
                idxs = vToIMap.get(target);

                for (Integer idx : idxs) {
                    if (idx > start && idx < end){

                        element = new ArrayList<>();
                        element.add(nums[start]);
                        element.add(target);
                        element.add(nums[end]);
                        Collections.sort(element);
                        resMap.put(element, true);
                        break;
                    }

                }//for

            }//if

            if (nums[start]+nums[start+1]+nums[end] > 0) {
                end--;
                continue;
            }else if(nums[start]+nums[end-1]+nums[end] < 0){
                start++;
                continue;
            }

        }//while

        for (List<Integer> list : resMap.keySet()) {
            res.add(list);
        }

        return res;

    }//threeSum
}

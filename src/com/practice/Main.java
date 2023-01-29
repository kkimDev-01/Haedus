package com.practice;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        //medianSlidingWindow(nums, 3);
        //[[2,6,8],[3,7,10],[5,8,11]] , 5
        //[[4,6],[2,3],[8,9]] , 10

        Integer[] a1 = {2,6,8};
        Integer[] a2 = {3,7,10};
        Integer[] a3 = {5,8,11};


        List<Integer[]> lists = new ArrayList<>();
        lists.add(a1);
        lists.add(a2);
        lists.add(a3);

        findKthSmallest(lists, 50);


    /*
      List < List < Integer >> inputs =
                (Arrays.asList(
                        Arrays.asList(1, 1), Arrays.asList(5, 5), Arrays.asList(8, 8), Arrays.asList(4, 4), Arrays.asList(6, 6), Arrays.asList(10, 10), Arrays.asList(7, 7)
                ));

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
      for (List input : inputs) {
          ArrayList<Integer> temp = new ArrayList<>(input);
          list.add(temp);
      }

        tasks(list);

     */
    }


    public static int findKthSmallest(List<Integer[]> lists, int k) {
        // Your code will replace this placeholder return statement

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int res = 0;
        int rank = 0;


        //assumption : there is no empty list
        for (Integer[] list : lists) {
            minHeap.add(list[0]);
        }

        rank++;


        outerloop:
        while (k >0) {
            for (int i = 0; i < lists.size(); i++) {

                if (minHeap.isEmpty()){
                    break outerloop;
                }

                if (lists.get(i).length - 1 < rank && i < lists.size() - 1) {
                    continue;
                }
                minHeap.add(lists.get(i)[rank]);
                res = minHeap.poll();
                k--;
                if (k == 0){
                    return res;
                }
            }
            rank++;
        }

        return res;
    }


    public static int[] mergeSorted(int[] nums1, int m, int[] nums2, int n) {

        // Write your code here

        int lastIdx = nums1.length-1;

        while (m - 1>= 0 && n - 1 >= 0) {
            if (nums1[m-1] <= nums2[n-1]) {
                nums1[lastIdx] = nums2[n - 1];
                n--;
            } else {
                nums1[lastIdx] = nums1[m - 1];
                m--;
            }
            lastIdx--;
        }

        if (m-1 < 0) {
            for (int i = 0; i < n; i++){
                nums1[i] = nums2[i];
            }
        }

        return nums1;
    }


    class KthLargest {
        private int k;
        private PriorityQueue<Integer> topKHeap;

        // constructor to initialize topKHeap and add values in it
        public KthLargest(int k, int[] nums) {
            // Write your code Here
            this.k = k;
            topKHeap = new PriorityQueue<>();
            for (int num : nums) {
                topKHeap.add(num);
            }
            while (topKHeap.size() > k) {
                topKHeap.poll();
            }

        }
        // adds element in the topKHeap
        public int add(int val) {
            // Your code will replace this placeholder return statement
            topKHeap.add(val);
            if (topKHeap.size() > k) {
                topKHeap.poll();
            }

            return returnKthLargest();
        }
        // returns kth largest element from topKHeap
        public int returnKthLargest() {
            // Your code will replace this placeholder return statement

            return topKHeap.peek();
        }

    }


















    public static int tasks(ArrayList<ArrayList<Integer>> tasksList) {

        PriorityQueue<ArrayList<Integer>> minStartHeap = new PriorityQueue<>(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.get(0) < o2.get(0)) {
                    return -1;
                } else if (o1.get(0) > o2.get(0)) {
                    return 1;
                }
                return 0;
            }
        });

        PriorityQueue<Integer> minEndHeap = new PriorityQueue<>();


        for (ArrayList list : tasksList){
            minStartHeap.add(list);
        }

        while (!minStartHeap.isEmpty()){
            if (minEndHeap.isEmpty()){
                minEndHeap.add(minStartHeap.poll().get(1));
                continue;
            }
            if (minStartHeap.peek().get(0) >= minEndHeap.peek()) {
                minEndHeap.poll();
            }
            minEndHeap.add(minStartHeap.poll().get(1));
        }



        return minEndHeap.size();
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        List<Double> res = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            insertNum(nums[i], maxHeap,minHeap);
        }

        res.add(findMedian(maxHeap, minHeap));

        for (int i = k; i < nums.length; i++) {
            if (maxHeap.peek() >= nums[i-k]) {
                maxHeap.remove(nums[i-k]);
            }else {
                minHeap.remove(nums[i-k]);
            }
            insertNum(nums[i], maxHeap, minHeap);
            res.add(findMedian(maxHeap, minHeap));

        }


        return res.stream().mapToDouble(i -> i).toArray();
    }

    private static double findMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap ) {
        // Write your code here
        if (minHeap.size() == maxHeap.size()){
            return (minHeap.peek() + 0D + maxHeap.peek())/2;
        }

        return minHeap.size() > maxHeap.size()? minHeap.peek() : maxHeap.peek();
    }

    private static void insertNum(int num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap ) {
        // Write your code here
        if (maxHeap.isEmpty() && !minHeap.isEmpty()){
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
            return;
        }

        if (!maxHeap.isEmpty() && minHeap.isEmpty()){
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            return;
        }

        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (maxHeap.peek() < num) {
            minHeap.add(num);
        }
        else {
            maxHeap.add(num);
        }

        if (minHeap.size() - maxHeap.size() >= 2){
            maxHeap.add(minHeap.poll());
        }

        if (maxHeap.size() - minHeap.size() >=2) {
            minHeap.add(maxHeap.poll());
        }

    }

    class MedianOfAStream {

        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianOfAStream() {
            // Write your code here
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void insertNum(int num) {
            // Write your code here
            if (maxHeap.isEmpty()){
                maxHeap.add(num);
                return;
            }


            if (maxHeap.peek() < num) {
                minHeap.add(num);
            }
            else {
                maxHeap.add(num);
            }

            if (minHeap.size() - maxHeap.size() >= 2){
                maxHeap.add(minHeap.poll());
            }

            if (maxHeap.size() - minHeap.size() >=2) {
                minHeap.add(maxHeap.poll());
            }

        }

        public double findMedian() {
            // Write your code here
            if (minHeap.size() == maxHeap.size()){
                return (minHeap.peek() + 0D + maxHeap.peek())/2;
            }

            return minHeap.size() > maxHeap.size()? minHeap.peek() : maxHeap.peek();
        }
    }

    public static int maximumCapital(int c, int k, int[] capitals,int[] profits) {

        // replace the dummy return with your code
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int capital : capitals){
            minHeap.add(capital);
        }

        for (int i = 0; i < profits.length && k > 0; i++) {

            if (c >= minHeap.peek()){
                maxHeap.add(profits[i]);
                minHeap.poll();
                continue;
            }
            if (!maxHeap.isEmpty()){
                c += maxHeap.poll();
                i--;
                k--;
                continue;
            }

            break;

        }

        while (k > 0 && !maxHeap.isEmpty()){
            c += maxHeap.poll();
            k--;
        }
        return c;

    }


}

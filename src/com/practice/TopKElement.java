package com.practice;


import java.util.*;
import java.util.stream.Collectors;

public class TopKElement {
    public static void main(String[] args) {

        //[1, 2, 3, 4, 5] , 4 , 3

        //int[] nums = {1, 2, 3, 4, 5};

        //List<Integer> res = findClosestElements(nums, 4, 3);

        //int[] nums = {1, 1, 2, 2, 3, 3, 4, 4, 5, 8, 8};

        //int res = singleNonDuplicate(nums);

        //[-71, -33, -11, -10, 1, 6] , 10

        int[] arr = {-71, -33, -11, -10, 1, 6};

        boolean res = search(arr, 10);

    }

    public static boolean search(int[] arr, int t) {

        // Write your code here
        int start = 0;
        int end = arr.length-1;
        int res = -1;

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        while(start <= end && res == -1) {

            int mid = (start + end)/2;
            if (arr[start]<= arr[mid]){
                if (arr[start]<= t && t <= arr[mid]) {
                    res = binarySearchIndex(start, end, list, t);
                }
                else {
                    start = mid+1;
                }
            }
            else {
                if (arr[mid] <= t && t <= arr[end]) {
                    res = binarySearchIndex(mid, end, list, t);
                }
                else {
                    end = mid - 1;
                }

            }
        }

        if (res == -1) {
            return false;
        }
        return true;

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


    public static int singleNonDuplicate(int[] nums) {

        // Your code will replace this placeholder return statement

        int start = 0;
        int end = nums.length -1;
        int mid = 0;

        while (start < end) {
            mid = (start + end) / 2;

            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid+1]) {
                start = mid + 2;
                continue;
            }else {
                end = mid;
            }

        }


        return nums[start];
    }


    public static List<Integer> findClosestElements(int[] nums, int k, int num) {

        // Your code will replace this placeholder return statement
        int start = 0;
        int end = nums.length-1;
        int mid = 0;
        int dist = Integer.MAX_VALUE;

        if (num <= nums[0]) {
            end = k - 1;
            return Arrays.stream(nums).boxed().collect(Collectors.toList()).subList(start, end+1);
        }

        if (nums[nums.length-1] <= num) {
            start = nums.length -k;
            return Arrays.stream(nums).boxed().collect(Collectors.toList()).subList(start, end+1);
        }

        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - num) <  dist) {
                mid = i;
                dist = Math.abs(nums[mid] - num);
            } else {
                break;
            }
        }

        start = mid;
        end = mid;

        while (end - start + 1 < k) {
            start--;
            end++;

            if (Math.abs(nums[start] - num) < Math.abs(nums[end] - num)) {
                end--;
            }
            else if (Math.abs(nums[start] - num) > Math.abs(nums[end] - num)) {
                start++;
            }
            else {
                end--;
            }

        }

        return Arrays.stream(nums).boxed().collect(Collectors.toList()).subList(start, end+1);
    }

    public static int findKthLargest(int[] arr, int k) {

        // Your code will replace this placeholder return statement
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i : arr) {
            minHeap.add(i);
        }

        while (minHeap.size() > k) {
            minHeap.poll();
        }


        return minHeap.peek();
    }

    public static List<Integer> topKFrequent(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        PriorityQueue<FrequencyObject> minHeap = new PriorityQueue<>(new Comparator<FrequencyObject>() {
            @Override
            public int compare(FrequencyObject o1, FrequencyObject o2) {

                if (o1.getVal() < o2.getVal()) {
                    return -1;
                } else if (o1.getVal() > o2.getVal()) {
                    return 1;
                }

                return 0;
            }
        });

        for (Integer key : map.keySet()) {
            minHeap.add(new FrequencyObject(key, map.get(key)));
        }

        while (minHeap.size() > k) {
            minHeap.poll();
        }

        List<Integer> res = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll().getKey());
        }

        return res;
    }

    static class FrequencyObject {
        private int key;
        private int val;

        public FrequencyObject(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }


    public static List<Point> kClosest(Point[] points, int k) {
        // Your code will replace this placeholder return statement

        PriorityQueue<Point> maxHeap = new PriorityQueue< >((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());

        for (int i = 0; i < k; i++) {
            maxHeap.add(points[i]);
        }

        for (int i = k; i < points.length; i++) {
            if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
                maxHeap.poll();
                maxHeap.add(points[i]);
            }
        }

        List<Point> res = new ArrayList<>();

        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.poll());
        }

        return res;
    }

    public static String reorganizeString(String string1) {

        // Write your code here
        String[] arr = string1.split("");

        Map<String, Integer> map = new HashMap<>();

        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        PriorityQueue<MapObject> maxHeap = new PriorityQueue<>(new Comparator<MapObject>() {
            @Override
            public int compare(MapObject o1, MapObject o2) {

                if (o1.getVal() < o2.getVal()) {
                    return 1;
                } else if (o1.getVal() > o2.getVal()) {
                    return -1;
                }
                return 0;
            }
        });

        for (String s : map.keySet()) {
            MapObject mapObject = new MapObject(s, map.get(s));
            maxHeap.add(mapObject);
        }

        String res = "";

        while (res.length() < string1.length() && maxHeap.size() > 0) {

            if (res.equals("")){
                res += getElem(maxHeap).getS();
                continue;
            }


            if (res.substring(res.length()-1).equals(maxHeap.peek().getS())){
                try {
                    MapObject first = maxHeap.poll();
                    res += getElem(maxHeap).getS();
                    maxHeap.add(first);
                } catch (Exception e) {
                    break;
                }
                continue;
            }

            res += getElem(maxHeap).getS();

        }

        if (res.length() == string1.length()) {
            return res;
        }


        return "";
    }

    private static MapObject getElem (PriorityQueue<MapObject> maxHeap) {
        MapObject top = maxHeap.poll();

        if (top.getVal() >= 2) {
            top.setVal(top.getVal()-1);
            maxHeap.add(top);
        }

        return top;
    }

    static class MapObject {
        private String s;
        private int val;

        public MapObject(String s, int val) {
            this.s = s;
            this.val = val;
        }

        public String getS() {
            return s;
        }

        public int getVal() {
            return val;
        }

        public void setS(String s) {
            this.s = s;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }


}

package com.leetcode.sliding_window;

import java.util.HashMap;

public class Minimum_Window_Substring {
    public static void main(String[] args) {

        String s = "a";
        String t = "aa";

        System.out.println(minWindow(s, t));

    }

    public static String minWindow(String s, String t) {



        String[] sArr = s.split("");
        String[] tArr = t.split("");

        HashMap<String, Integer> tMap = new HashMap<>();


        for (String s1 : tArr) {
            tMap.put(s1, tMap.getOrDefault(s1, 0) + 1);
        }

        HashMap<String, Integer> sMap = new HashMap<>();

        for (int i = 0; i < sArr.length; i++) {
            sMap.put(sArr[i], sMap.getOrDefault(sArr[i], 0) + 1);
        }

        if(!tMapInsMapA(tMap, sMap)){
            return "";
        }


        int left = 0;
        String min = s;

        int right = tMap.keySet().size() - 1;

        sMap = new HashMap<>();

        for (int i = 0; i <= right; i++) {
            sMap.put(sArr[i], sMap.getOrDefault(sArr[i], 0) + 1);
        }

        while(right <= s.length()-1){

            if(tMapInsMapA(tMap, sMap)){
                if (min.length() > right-left + 1) {
                    min = s.substring(left, right) + s.charAt(right);
                }
                sMap.put(sArr[left], sMap.getOrDefault(sArr[left], 0) - 1);
                left++;


            }else if (right == s.length() - 1){
                right++;
            } else {
                right++;
                sMap.put(sArr[right], sMap.getOrDefault(sArr[right], 0) + 1);
            }
            
        }



        return min;

    }

    private static boolean tMapInsMapA(HashMap<String, Integer> tMap, HashMap<String, Integer> sMap) {

        for (String s : tMap.keySet()) {
            if (sMap.getOrDefault(s, 0) < tMap.get(s)){
                return false;
            }
        }
        return true;
    }
}

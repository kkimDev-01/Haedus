package com.leetcode.sliding_window.distinct_characters;

import java.util.HashMap;

public class prob003 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("tmmzuxt"));
    }

    public static int lengthOfLongestSubstring2(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        int start = 0;
        int maxLength = 0;
        int length = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);

                while(map.get(c) > 1){
                    char del = s.charAt(start++);
                    map.put(del, map.get(del) - 1);
                    if (map.get(del) == 0){
                        map.remove(del);
                    }
                    length = i+1-start;
                }

            } else {
                map.put(c, 1);
                length = i+1-start;
            }

            maxLength = Math.max(maxLength, length);

        }
        return s.length() > 0? maxLength : 0;

    }






    public static int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> map;
        Boolean flag;

        for (int i = s.length(); i > 0; i--) {
            for (int j = 0; j <= s.length()- i; j++){
                String subStr = s.substring(j, j+i);
                map = new HashMap<>();
                flag = true;
                for (int k = 0; k < subStr.length(); k++) {
                    if (!map.containsKey(subStr.charAt(k))){
                        map.put(subStr.charAt(k), 1);
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return i;
                }

            }

        }

        if (s.length() > 0){
            return 1;
        }
        return 0;
    }
}

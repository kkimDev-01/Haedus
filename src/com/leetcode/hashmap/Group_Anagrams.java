package com.leetcode.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Group_Anagrams {

    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs.length == 1) {
            List<String> list = new ArrayList<>();
            list.add(strs[0]);
            List<List<String>> res = new ArrayList<>();
            res.add(list);
            return res;
        }

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String[] strArr = str.split("");
            Arrays.sort(strArr);

            String sortStr = String.join("", strArr);

            if (!map.containsKey(sortStr)) map.put(sortStr, new ArrayList<>());

            map.get(sortStr).add(str);
        }//for


        List<List<String>> list = new ArrayList<>();

        for (String s : map.keySet()) {
            list.add(map.get(s));
        }

        return list;


    }//method

}

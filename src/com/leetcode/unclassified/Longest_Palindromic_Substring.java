package com.leetcode.unclassified;

public class Longest_Palindromic_Substring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abb"));
    }

    public static int start;
    public static int end;
    public static String res;
    public static String str;

    public static String longestPalindrome(String s) {

        str = s;

        for (int i = s.length() - 1; i >= 0; i--) {

            for (int j = 0; j < s.length() - i; j++){

                start = j;
                end = start + i;

                if (isPalindrome()){
                    return res;
                }

            }//for j (starting index)

        }//for i

        return s.charAt(0)+"";

    }//longestPalindrome

    private static boolean isPalindrome() {

        int subStart = start;
        int subEnd = end;

        while (subStart < subEnd) {
            if (!(str.charAt(subStart) == str.charAt(subEnd))){
                return false;
            }
            subStart++;
            subEnd--;
        }

        if (end - start == str.length() -1) {
            res = str;
        }else {
            res = str.substring(start, end + 1);
        }

        return true;

    }

}

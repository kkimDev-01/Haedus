package com.leetcode.stack;

import java.util.HashMap;
import java.util.Stack;

public class Valid_Parentheses {

    public static void main(String[] args) {
        System.out.println(isValid("({[]})"));
    }

    public static boolean isValid(String s) {

        Stack<String> stack = new Stack<>();

        HashMap<String, String> map = new HashMap<>();
        map.put("(", ")");
        map.put("{", "}");
        map.put("[", "]");

        String[] arr = s.split("");

        for (String elem : arr) {

            if (elem.equals("(") || elem.equals("{") || elem.equals("[")){
                stack.push(elem);
            }else{

                if (stack.isEmpty()){
                    return false;
                }

                if (map.get(stack.peek()).equals(elem)){
                    stack.pop();
                }else{
                    break;
                }
            }

        }//for

        if (stack.isEmpty()){
            return true;
        }

        return false;

    }
}

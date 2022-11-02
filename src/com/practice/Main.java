package com.practice;

public class Main {

    public static void main(String[] args) {
        // write your code here

        StringBuilder sb = new StringBuilder();

        StringBuilder sb1 = new StringBuilder();

        sb.append("1");
        sb1.append("2");

        sb.append(sb1);

        System.out.println(sb);

    }

}

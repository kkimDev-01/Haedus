package com.practice;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("hello world!");

        int length = 100;

        System.out.println(length >> 3); //비트 연산 : 12%
        System.out.println(length >> 6);

        int seventh = (length >> 3) + (length >> 6) + 1;

        System.out.println(seventh);

        System.out.println('9'-'0');

    }
}

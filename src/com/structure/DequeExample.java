package com.structure;

import java.util.LinkedList;
import java.util.Deque;

public class DequeExample {

    public static void main(String[] args) {

        Deque<Integer> deque = new LinkedList<>();

        LinkedList<Integer> linkedList = new LinkedList<>();

        //마지막에 원소 삽입
        deque.add(1);

        //맨 앞에 원소 삽입
       deque.addFirst(2);

        //마지막에 원소 삽입
        deque.addLast(3);

        //맨 앞의 원소 제거 후 해당 원소를 리턴
        System.out.println(deque.poll());

        System.out.println(deque.pollLast());

    }
}

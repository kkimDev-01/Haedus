package com.practice;

import java.util.*;
public class InPlaceLinkedList{

    public static void main(String[] args) {

        //[1, 1, 2, 2, 3, -1, 10, 12]
        LinkedListNode s1 = new LinkedListNode(1);
        LinkedListNode s2 = new LinkedListNode(1);
        LinkedListNode s3 = new LinkedListNode(2);
        LinkedListNode s4 = new LinkedListNode(2);
        LinkedListNode s5 = new LinkedListNode(3);
        LinkedListNode s6 = new LinkedListNode(-1);
        LinkedListNode s7 = new LinkedListNode(10);
        LinkedListNode s8 = new LinkedListNode(12);

        s1.next = s2;
        s2.next = s3;
        s3.next = s4;
        s4.next = s5;
        s5.next = s6;
        s6.next = s7;
        s7.next = s8;

        //[10, 20, -22, 21, -12]
        LinkedListNode a1 = new LinkedListNode(10);
        LinkedListNode a2 = new LinkedListNode(20);
        LinkedListNode a3 = new LinkedListNode(-22);
        LinkedListNode a4 = new LinkedListNode(21);
        LinkedListNode a5 = new LinkedListNode(-12);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        //[3, 2, 3, 4, 5, 6] , 3
        LinkedListNode b1 = new LinkedListNode(1);
        LinkedListNode b2 = new LinkedListNode(2);
        LinkedListNode b3 = new LinkedListNode(3);
        LinkedListNode b4 = new LinkedListNode(4);

        b1.next = b2;
        b2.next = b3;
        b3.next = b4;


        //swapNodes(b1, 3);

        //reverseEvenLengthGroups(b1);

        swapPairs(b1);
    }

//    private static LinkedListNode LEFT = null;
//    private static LinkedListNode RIGHT = null;
    private static LinkedListNode FIRST = null;
//
//    private static LinkedListNode HEAD = null;

    private static void reverseSubList(LinkedListNode left, LinkedListNode start, LinkedListNode right) {
        LinkedListNode reversed = null;
        LinkedListNode todo = start;
        LinkedListNode temp = null;
        while (todo != right){
            temp = todo;
            todo = todo.next;
            temp.next = reversed;
            reversed = temp;
        }
        if (left != null) {
            left.next = reversed;
        }
        if (FIRST == null){
            FIRST = reversed;
        }

        start.next = right;

    }

    public static LinkedListNode swapPairs(LinkedListNode head) {
        // Write your code here
        LinkedListNode RIGHT = head;
        LinkedListNode LEFT = null;

        while(true) {
            try {
                for (int i = 0; i < 2; i++) {
                    RIGHT = RIGHT.next;
                }
            } catch (Exception e) {
                break;
            }

            if (FIRST == null) {
                reverseSubList(null, head, RIGHT);
            }
            else {
                reverseSubList(LEFT, LEFT.next, RIGHT);
            }

            if (LEFT == null){
                LEFT = FIRST.next;
            }
            else {
                for (int i = 0; i < 2; i++) {
                    LEFT = LEFT.next;
                }
            }

        }

        return FIRST;
    }



    public static LinkedListNode reverseEvenLengthGroups(LinkedListNode head) {
        // Write your code here
        int n = 2;
        LinkedListNode left = head;
        LinkedListNode right = head;
        Boolean reached = false;
        int modifiedN = 0;

        while (true) {
            try {
                right = left;

                for (int i = 0; i < n + 1; i++){
                    if (right == null){
                        reached = true;
                        modifiedN = i+1;
                        break;
                    }
                    right = right.next;
                }
                if (reached){
                    n = modifiedN;
                }

                if (n % 2  == 0) {
                    reverseSubList(left, left.next, right);
                }

                if (reached){
                    break;
                }

                for (int i = 0; i < n; i++) {
                    left = left.next;
                }
                n++;
            } catch (Exception e) {
                break;
            }
        }

        return head;
    }



    public static LinkedListNode swapNodes(LinkedListNode head, int k) {
        // Write your code here

        LinkedListNode temp = head;

        int length = 0;

        while (temp != null) {
            length++;
            temp = temp.next;
        }

        LinkedListNode s1 = head;
        LinkedListNode s2 = head;


        for (int i = 0; i < k-1; i++) {
            s1 = s1.next;
        }

        for (int i = 0; i < length - k ; i++) {
            s2 = s2.next;
        }

        int tmp = s1.data;
        s1.data = s2.data;
        s2.data = tmp;

        return head;
    }





    public static LinkedListNode reorderList(LinkedListNode head) {
        // Write your code here

        LinkedListNode slow = head;
        LinkedListNode fast = head;


        while (true) {
            try{
                fast = fast.next;
                fast = fast.next;
                slow = slow.next;
            } catch (Exception e){
                break;
            }

        }


        if (fast != null){
            slow = slow.next;
        }

        LinkedListNode reversed = null;
        LinkedListNode todo = slow;
        LinkedListNode temp = null;

        while (todo != null){
            temp = todo;
            todo = todo.next;
            temp.next = reversed;
            reversed = temp;
        }

        LinkedListNode first = head;
        LinkedListNode second = reversed;
        LinkedListNode temp1 = null;
        LinkedListNode temp2 = null;

        Boolean headDone = false;

        while (first.next != second && first != second) {

            temp1 = first;
            first = first.next;
            temp2 = second;
            second = second.next;
            temp1.next = temp2;
            temp2.next = first;

            if (!headDone) {
                head = temp1;
                headDone = true;
            }

        }



        return head;
    }

    public static LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
        // Write your code here
        // Tip: You may use some of the code templates provided
        // in the support files

        LinkedListNode todo = head;
        LinkedListNode temp = null;
        LinkedListNode reversed = null;

        LinkedListNode leftEnd = head;
        LinkedListNode rightStart = head;

        for (int i = 0; i < left -2; i++){
            leftEnd = leftEnd.next;
        }

        for (int i = 0; i< right; i++){
            rightStart = rightStart.next;
        }

        for (int i = 0; i< left - 1; i++) {
            todo = todo.next;
        }

        LinkedListNode intervalEnd = null;


        for (int i = 0; i < right - left + 1; i++) {
            temp = todo;
            todo = todo.next;
            temp.next = reversed;
            reversed = temp;

            if (intervalEnd == null) {
                intervalEnd = reversed;
            }

        }

        intervalEnd.next = rightStart;

        if (left == 1) {
            return reversed;
        }

        leftEnd.next = reversed;

        return head;
    }



    public static LinkedListNode reverseLinkedList(LinkedListNode head, int k) {
        // Write your code here
        // Tip: You may use some of the code templates provided
        // in the support files

        Boolean headDone = false;
        LinkedListNode todo = head;

        while (true) {
            LinkedListNode nextTodo = todo;
            LinkedListNode temp = null;
            LinkedListNode reversed = null;
            LinkedListNode nextOfReversed = null;
            try {
                for (int i = 0; i < k; i++){
                    nextTodo = nextTodo.next;
                }
            }catch (Exception e){
                break;
            }

            while (todo != nextTodo){
                temp = todo;
                todo = todo.next;
                temp.next = reversed;
                reversed = temp;
            }

            if (!headDone) {
                head = reversed;
                headDone = true;
            }

            nextOfReversed = nextTodo;

            for (int i = 0; i < k-1; i++) {
                reversed = reversed.next;
            }
            for (int i = 0; i< k-1; i++) {
                if (nextOfReversed == null){
                    break;
                }
                if (nextOfReversed.next == null){
                    nextOfReversed = nextTodo;
                    break;
                }
                nextOfReversed = nextOfReversed.next;
            }

            reversed.next = nextOfReversed;

            todo = nextTodo;

        }

        return head;
    }
}
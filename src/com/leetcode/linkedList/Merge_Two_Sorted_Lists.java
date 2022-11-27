package com.leetcode.linkedList;

public class Merge_Two_Sorted_Lists {

    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null){
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode prehead = new ListNode(-1);
        ListNode cur = prehead;

        while(list1 != null && list2 != null){
            if (list1 == null) {
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;
                continue;
            }

            if (list2 == null) {
                cur.next = list1;
                list1 = list1.next;
                cur = cur.next;
                continue;
            }

            if (list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }

            cur = cur.next;

        }

        return prehead.next;

    }

}

package com.structure.List;

import java.util.ArrayList;
import java.util.List;

public class ListInterface {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);

        list.remove(1);

        System.out.println(list);

        System.out.println(list.size());

        System.out.println(list.contains(1));

        System.out.println(list.get(0));

        System.out.println(list.set(0, 3));

        System.out.println(list.contains(3));

        System.out.println(list.indexOf(3));

        list.clear();

        System.out.println(list.isEmpty());

    }
}

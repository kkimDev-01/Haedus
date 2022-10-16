package com.baekjoon;

import java.util.*;

public class prob2108 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfNums = sc.nextInt();
        List<Integer> numList = new ArrayList<>();

        for (int i = 0; i < numOfNums; i++) {
            numList.add(sc.nextInt());
        }

        Collections.sort(numList);

        int sum = 0;

        HashMap<Integer, Integer> countMap = new HashMap<>();
        List<List<Integer>> countList = new ArrayList<>();

        for (int num : numList){
            sum += num;
            if (countMap.containsKey(num)){
                countMap.put(num, countMap.get(num) + 1);
            }
            else {
                countMap.put(num, 1);
            }
        }

        for (int key : countMap.keySet()){
            List<Integer> elem = new ArrayList<>();
            elem.add(key);
            elem.add(countMap.get(key));
            countList.add(elem);
        }

        Collections.sort(countList, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o2.get(1) - o1.get(1);
            }
        });

        List<List<Integer>> duplicates = new ArrayList<>();
        for (int i = 0; i<countList.size(); i++){
            if (countList.get(0).get(1) == countList.get(i).get(1)){
                duplicates.add(countList.get(i));
            }
        }

        int mode;

        if (duplicates.size() == 1){
            mode = duplicates.get(0).get(0);
        }
        else{
            Collections.sort(duplicates, new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    return o1.get(0) - o2.get(0);
                }
            });

            mode = duplicates.get(1).get(0);
        }


        int median = numList.get(numOfNums/2);
        int range = numList.get(numList.size()-1) - numList.get(0);

        int average = (int) Math.round(((double) sum) / numOfNums);

        System.out.println(average);
        System.out.println(median);
        System.out.println(mode);
        System.out.println(range);


    }
}

// 시간제한 2초
// 메모리 제한

//시간 2204ms (시간오버인데, 일정 수준은 봐주는 듯, 아니면 반올림)
// 메모리 : 317664 KB =
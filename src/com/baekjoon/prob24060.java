package com.baekjoon;


/*
문제 : https://www.acmicpc.net/problem/24060
해설 : https://st-lab.tistory.com/233
정리내용(블로그)
    - 병합정렬
    - Call_by_Value_VS_Call_by_Reference
    -
*/
public class prob24060 {
}


//재귀 : Top-Down 방식
class prob24060_1 {
    private static int[] sorted;    // 합치는 과정에서 정렬하여 원소를 담을 임시배열

    public static void merge_sort(int[] a) {
        sorted = new int[a.length];
        merge_sort(a, 0, a.length - 1);     //int[] 배열은 메모리 공유되는듯
        sorted = null;
    }

    private static void merge_sort(int[] a, int left, int right){

        if (left == right) return;  //left==right 즉, 부분리스트가 1개의 원소만 갖고있는경우, 더이상 쪼갤 수 없으므로 return한다.

        int mid = (left + right) / 2;   //절반 위치

        merge_sort(a, left, mid);   //절반 중 왼쪽 부분리스트 (left ~ mid)
        merge_sort(a, mid + 1, right);  //절반 중 오른쪽 부분리스트 (mid + 1 ~ right)

        merge(a, left, mid, right);

    }

    private static void merge(int[] a, int left, int mid, int right) {
        int l = left;		// 왼쪽 부분리스트 시작점
        int r = mid + 1;	// 오른쪽 부분리스트의 시작점
        int idx = left;		// 채워넣을 배열의 인덱스


        while(l <= mid && r <= right) {
            /*
             *  왼쪽 부분리스트 l번째 원소가 오른쪽 부분리스트 r번째 원소보다 작거나 같을 경우
             *  왼쪽의 l번째 원소를 새 배열에 넣고 l과 idx를 1 증가시킨다.
             */
            if(a[l] <= a[r]) {
                sorted[idx] = a[l];
                idx++;
                l++;
            }
            /*
             *  오른쪽 부분리스트 r번째 원소가 왼쪽 부분리스트 l번째 원소보다 작거나 같을 경우
             *  오른쪽의 r번째 원소를 새 배열에 넣고 r과 idx를 1 증가시킨다.
             */
            else {
                sorted[idx] = a[r];
                idx++;
                r++;
            }
        }

        /*
         * 왼쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (l > mid)
         * = 오른쪽 부분리스트 원소가 아직 남아있을 경우
         * 오른쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
         */
        if(l > mid) {
            while(r <= right) {
                sorted[idx] = a[r];
                idx++;
                r++;
            }
        }

        /*
         * 오른쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (r > right)
         * = 왼쪽 부분리스트 원소가 아직 남아있을 경우
         * 왼쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
         */
        else {
            while(l <= mid) {
                sorted[idx] = a[l];
                idx++;
                l++;
            }
        }

        /*
         * 정렬된 새 배열을 기존의 배열에 복사하여 옮겨준다.
         */
        for(int i = left; i <= right; i++) {
            a[i] = sorted[i];
        }
    }

}


//반복문 : Bottom-Up 방식  (참고블로그에 있으나, 생략. 재귀라도 기억한다는 목표)
class prob24060_2 {

}
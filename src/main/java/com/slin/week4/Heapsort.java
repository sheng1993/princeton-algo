package com.slin.week4;

/**
 * Heap construction takes linear time.
 * Sort takes NlogN.
 * 
 * In-place sorting algorithm with NlogN wort-case.
 * - Mergesort -> no, linear extra space.
 * - Quicksort -> no, quadratic in wort case.
 */
public class Heapsort
{
    public static void sort(Comparable[] arr)
    {
        int N = arr.length;
        for (int k=N/2; k>=1; k--)
        {
            sink(arr, k, N);
        }

        while (N > 1)
        {
            exch(arr, 1, N--);
            sink(arr, 1, N);
        }
    }
    
    private static void sink(Comparable[] arr, int k, int N)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && lessThan(arr, j, j+1)) j ++;
            if (!lessThan(arr, k, j)) break;
            exch(arr, k, j);
            k = j;
        }
    }

    private static boolean lessThan(Comparable[] arr, int a, int b)
    {
        return arr[a-1].compareTo(arr[b-1]) < 0;
    }

    private static void exch(Comparable[] arr, int i, int j)
    {
        Comparable t = arr[i-1];
        arr[i-1] = arr[j-1];
        arr[j-1] = t;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5,4,3,2,1};
        Heapsort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
package com.slin.week3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * NlogN, no additional space
 */
public class Quicksort
{
    public static void sort(Comparable[] arr)
    {
        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j-1);
        sort(arr, j+1, hi);
    }

    protected static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            while (lessThan(a[++i], a[lo]))
            {
                if (i==hi) break;
            }

            while (lessThan(a[lo], a[--j]))
            {
                if (j==lo) break;
            }

            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }
    
    private static boolean lessThan(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] arr, int i, int j)
    {
        if (i == j) return;

        Comparable aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }

    public static void main(final String[] args) 
    {
        Integer[] arr = new Integer[]{5,4,3,2,1};
        Quicksort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
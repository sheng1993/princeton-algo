package com.slin.week3;

import edu.princeton.cs.algs4.StdRandom;

public class Quickselect extends Quicksort
{
    public static Comparable select(Comparable[] arr, int k)
    {
        if (k <= 0 || k > arr.length)
        {
            throw new java.lang.ArrayIndexOutOfBoundsException();
        }

        k = k - 1;

        StdRandom.shuffle(arr);
        int lo = 0, hi = arr.length - 1;
        while (hi > lo)
        {
            int j = partition(arr, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return arr[k];
        }
        return arr[k];
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{50,30,40,60,25};
        System.out.println(Quickselect.select(arr, 0));
    }
}
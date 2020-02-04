package com.slin.week3;

/**
 * Merge sort uses at most NlogN compares and 6NlogN array access to sort
 * Merge takes O(N)
 */
public class MergeSort
{
    public static void sort(Comparable[] arr)
    {
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length-1);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int low, int hi)
    {
        if (hi<=low) return;

        int mid = low + (hi - low) / 2;
        sort(arr, aux, low, mid);
        sort(arr, aux, mid+1, hi);
        merge(arr, aux, low, mid, hi);
    }

    private static void merge(Comparable[] arr, Comparable[] aux, int low, int mid, int hi)
    {
        for (int k=low; k<=hi; k++)
        {
            aux[k] = arr[k];
        }

        int i = low, j = mid+1;
        for (int k=low; k<=hi; k++)
        {
            if      (i > mid)                       arr[k] = aux[j++];
            else if (j > hi)                        arr[k] = aux[i++];
            else if (lessThan(aux[j], aux[i]))      arr[k] = aux[j++];
            else                                    arr[k] = aux[i++];
        }
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
        MergeSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
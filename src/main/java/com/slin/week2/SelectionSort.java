package com.slin.week2;

/**
 * Selection sort uses ~(N^2)/2 compares and N exchanges
 */
public class SelectionSort
{
    public static void sort(Comparable<?>[] arr)
    {
        for (int i=0; i<arr.length; i++)
        {
            int min = i;
            for (int j=i+1; j<arr.length; j++)
            {
                if (lessThan(arr[j], arr[i]))
                {
                    min = j;
                }
            }
            exch(arr, i, min);
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
        SelectionSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
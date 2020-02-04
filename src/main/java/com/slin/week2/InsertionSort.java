package com.slin.week2;

/**
 * Best case: N-1 compares and 0 exchanges.
 * Wort case: N^2 compares and N^2 exchanges.
 * Partially sorted arrays: exchanges + (N+1) => linear time.
 */
public class InsertionSort
{
    public static void sort(Comparable[] arr)
    {
        int N = arr.length;

        for (int i=0; i<N; i++)
        {
            for (int j=i; j>0; j--)
            {
                if (lessThan(arr[j], arr[j-1]))
                {
                    exch(arr, j, j-1);
                }
                else
                {
                    break;
                }
            }
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
        InsertionSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
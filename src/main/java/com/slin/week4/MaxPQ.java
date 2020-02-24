package com.slin.week4;

public class MaxPQ<Key extends Comparable<Key>>
{
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity)
    {
        this.pq = (Key[])new Comparable[capacity+1];
    }

    public boolean isEmpty()
    {
        return this.N == 0;
    }

    public void insert(Key x)
    {
        this.pq[++N] = x;
        this.swim(N);
    }

    public Key delMax()
    {
        Key max = this.pq[1];
        exch(1, this.N--);
        this.sink(1);
        this.pq[N+1] = null;
        return max;
    }

    private void sink(int k)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && lessThan(j, j+1)) j ++;
            if (!lessThan(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k)
    {
        while (k > 1 && lessThan(k/2, k))
        {
            this.exch(k, k/2);
            k = k/2;
        }
    }

    private boolean lessThan(int a, int b)
    {
        return this.pq[a].compareTo(this.pq[b]) < 0;
    }

    private void exch(int i, int j)
    {
        Key t = this.pq[i];
        this.pq[i] = this.pq[j];
        this.pq[j] = t;
    }
}
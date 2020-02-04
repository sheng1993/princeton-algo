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

    private boolean less(int i, int j)
    {
        return this.pq[i].compareTo(this.pq[j]) < 0;
    }

    private void exch(int i, int j)
    {
        Key t = this.pq[i];
        this.pq[i] = this.pq[j];
        this.pq[j] = t;
    }
}
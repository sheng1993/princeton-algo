package com.slin.week7;

public class Edge implements Comparable<Edge>
{
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either()
    {
        return this.v;
    }

    public int other(int v)
    {
        if (v == this.v)
        {
            return this.w;
        }

        return this.v;
    }

    public double weight()
    {
        return this.weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight < o.weight)
        {
            return -1;
        }
        else if (this.weight > o.weight)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public String toString()
    {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
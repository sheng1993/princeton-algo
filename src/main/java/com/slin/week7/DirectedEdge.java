package com.slin.week7;

public class DirectedEdge
{
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from()
    {
        return this.v;
    }

    public int to()
    {
        return this.w;
    }

    public double weight()
    {
        return this.weight;
    }

    public String toString()
    {
        return String.format("%d->%d %f", this.v, this.w, this.weight);
    }
}
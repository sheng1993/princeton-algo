package com.slin.week6;

import com.slin.week7.EdgeWeightedDigraph;

import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;

public class Topological
{
    private Iterable<Integer> order;

    public Topological(Digraph G)
    {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle())
        {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.resersePost();
        }
    }

    public Iterable<Integer> order()
    {
        return this.order;
    }

    public boolean isDAG()
    {
        return this.order != null;
    }
}
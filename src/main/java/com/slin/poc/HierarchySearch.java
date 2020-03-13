package com.slin.poc;

import com.slin.week6.Digraph;

import edu.princeton.cs.algs4.Stack;

public class HierarchySearch
{
    private boolean[] marked;
    private int[] edgeTo;

    public HierarchySearch(Digraph G)
    {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];

        for (int v=0; v<G.V(); v++)
        {
            this.edgeTo[v] = -1;
        }

        for (int v=0; v<G.V(); v++)
        {
            if (!this.marked[v])
            {
                this.dfs(G, v);
            }
        }
    }

    public Iterable<Integer> pathTo(int v)
    {
        Stack<Integer> path = new Stack<>();

        if (this.marked[v])
        {
            for (int x=v; x>-1; x=this.edgeTo[x])
            {
                path.push(x);
            }
        }

        return path;
    }

    private void dfs(Digraph G, int v)
    {
        this.marked[v] = true;
        for (int w : G.adj(v))
        {
            if (!this.marked[w])
            {
                this.edgeTo[w] = v;
                this.dfs(G, w);
            }
        }
    }
}
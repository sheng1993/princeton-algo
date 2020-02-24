package com.slin.week6;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Paths
{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public Paths(Graph G, int s)
    {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        this.dfs(G, s);
    }

    private void dfs(Graph G, int v)
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

    public boolean hasPathTo(int v)
    {
        return this.marked[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        if (!this.hasPathTo(v))  return null;

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != this.s; x = this.edgeTo[x])
        {
            path.push(x);
        }

        return path;
    }

    public static void main(String[] args) 
    {
        Graph G = new Graph(6);
        G.addEdge(0, 2);
        G.addEdge(0, 1);
        G.addEdge(0, 5);
        G.addEdge(5, 3);
        G.addEdge(3, 4);
        G.addEdge(3, 2);
        G.addEdge(4, 2);

        Paths search = new Paths(G, 0);
        int s = 0;
        for (int v = 0; v < G.V(); v++)
        {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v))
                for (int x : search.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}
package com.slin.week6;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths
{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s)
    {
        this.s = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.bfs(G, s);
    }

    private void bfs(Graph G, int s)
    {
        Queue<Integer> queue = new Queue<>();
        this.marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty())
        {
            int v = queue.dequeue();
            for (int w : G.adj(v))
            {
                if (!this.marked[w])
                {
                    this.edgeTo[w] = v;
                    this.marked[w] = true;
                    queue.enqueue(w);
                }
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

        BreadthFirstPaths search = new BreadthFirstPaths(G, 0);
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
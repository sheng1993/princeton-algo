package com.slin.week6;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdOut;

public class CC
{
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G)
    {
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];
        
        for (int s=0; s<G.V(); s++)
        {
            if (!this.marked[s])
            {
                this.dfs(G, s);
                this.count++;
            }            
        }
    }

    private void dfs(Graph G, int v)
    {
        this.marked[v] = true;
        this.id[v] = count;
        for (int w : G.adj(v))
        {
            if (!this.marked[w]) this.dfs(G, w);
        }
    }

    public boolean connected(int v, int w)
    {
        return this.id[v] == this.id[w];
    }

    public int id(int v)
    {
        return this.id[v];
    }

    public int count()
    {
        return this.count;
    }

    public static void main(String[] args)
    {
        Graph G = new Graph(13);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 5);
        G.addEdge(0, 6);
        G.addEdge(5, 3);
        G.addEdge(5, 4);
        G.addEdge(4, 6);
        G.addEdge(7, 8);
        G.addEdge(9, 10);
        G.addEdge(9, 11);
        G.addEdge(9, 12);

        CC cc = new CC(G);

        int M = cc.count();
        StdOut.println(M + " components");
        Bag<Integer>[] components;
        components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++)
            components[i] = new Bag<Integer>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);
        for (int i = 0; i < M; i++)
        {
            for (int v: components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
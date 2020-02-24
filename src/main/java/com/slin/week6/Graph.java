package com.slin.week6;

import edu.princeton.cs.algs4.Bag;

public class Graph
{
    private static final String NEWLINE = System.getProperty("line.separator");

    protected final int V;
    protected int E;
    protected Bag<Integer>[] adj;

    public Graph(int V)
    {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[])new Bag[V];
        for (int v=0; v<this.V; v++)
        {
            this.adj[v] = new Bag<Integer>();
        }
    }
    
    public int V()
    {
        return this.V;
    }

    public int E()
    {
        return this.E;
    }

    public void addEdge(int v, int w)
    {
        this.adj[v].add(w);
        this.adj[w].add(v);
        this.E++;
    }

    public Iterable<Integer> adj(int v)
    {
        return this.adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        System.out.println(G.toString());
    }
}
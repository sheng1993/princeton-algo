package com.slin.week7;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedDigraph
{
    private static final String NEWLINE = System.getProperty("line.separator");
    
    private int V;
    private int E;
    private final Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V)
    {
        this.V = V;
        this.adj = (Bag<DirectedEdge>[])new Bag[this.V];
        for (int v=0; v<this.V; v++)
        {
            this.adj[v] = new Bag<DirectedEdge>();
        }
    }

    public void addEdge(DirectedEdge e)
    {
        int v = e.from();
        this.adj[v].add(e);
        this.E++;
    }

    public int V()
    {
        return this.V;
    }

    public int E()
    {
        return this.E;
    }

    public Iterable<DirectedEdge> adj(int v)
    {
        return this.adj[v];
    }

    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
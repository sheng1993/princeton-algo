package com.slin.week7;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedGraph
{
    private static final String NEWLINE = System.getProperty("line.separator");
    
    private final int V;
    private final Bag<Edge>[] adj;
    private int E;

    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        this.adj = (Bag<Edge>[])new Bag[this.V];
        for (int v=0; v<this.V; v++)
        {
            this.adj[v] = new Bag<>();
        }
    }    

    public void addEdge(Edge e)
    {
        int v = e.either(), w = e.other(v);
        this.adj[v].add(e);
        this.adj[w].add(e);
        this.E++;
    }

    public Iterable<Edge> adj(int v)
    {
        return this.adj[v];
    }

    public Iterable<Edge> edges()
    {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public int V()
    {
        return this.V;
    }

    public int E()
    {
        return this.E;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
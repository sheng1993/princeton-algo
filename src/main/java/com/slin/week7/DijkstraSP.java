package com.slin.week7;

import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * E log V
 * Does not work with negative weights
 * 
 * 
 * **No directed cycles -> Topological Sort
 * **No negative weights -> Dijkstra
 * **No negative cycles -> Bellman-Ford
 * 
 */
public class DijkstraSP
{
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s)
    {
        this.edgeTo = new DirectedEdge[G.V()];
        this.distTo = new double[G.V()];
        this.pq = new IndexMinPQ<Double>(G.V());

        for (int v=0; v<G.V(); v++)
        {
            this.distTo[v] = Double.POSITIVE_INFINITY;
        }
        this.distTo[s] = 0.0;

        this.pq.insert(s, 0.0);
        while (!this.pq.isEmpty())
        {
            int v = this.pq.delMin();
            for (DirectedEdge e : G.adj(v))
            {
                this.relax(e);
            }
        }
    }

    private void relax(DirectedEdge e)
    {
        int v = e.from(), w = e.to();
        if (this.distTo[w] > this.distTo[v] + e.weight())
        {
            this.distTo[w] = this.distTo[v] + e.weight();
            this.edgeTo[w] = e;
            if (this.pq.contains(w))    this.pq.decreaseKey(w, this.distTo[w]);
            else                        this.pq.insert(w, this.distTo[w]);
        }
    }
}
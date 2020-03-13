package com.slin.week7;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.QuickUnionUF;

/**
 * E log E (in worst case)
 * if edges are already sorted, order of growth is E log* V
 */
public class KruskalMST
{
    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph G)
    {
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges())
        {
            pq.insert(e);
        }

        QuickUnionUF uf = new QuickUnionUF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1)
        {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w))
            {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> edges()
    {
        return this.mst;
    }
}
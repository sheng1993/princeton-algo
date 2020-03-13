package com.slin.week7;

/**
 * Computes SPT in E + V.
 */
public class AcyclicSP
{
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s)
    {
        this.edgeTo = new DirectedEdge[G.V()];
        this.distTo = new double[G.V()];

        for (int v=0; v<G.V(); v++)
        {
            this.distTo[v] = Double.POSITIVE_INFINITY;
        }
        this.distTo[s] = 0;

        // Topological topological = new Topological(G);
        // for (int v : topological.order())
        // {
        //      for (DirectedEdge e: G.adj(v))
        //      {
        //          this.relax(e);
        //      }
        // }
    }
}
package com.slin.week6;

public class Cycle
{
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G)
    {
        this.marked = new boolean[G.V()];
        for (int s=0; s<G.V(); s++)
        {
            if (!this.marked[s])
            {
                this.dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int v, int u)
    {
        this.marked[v] = true;
        for (int w : G.adj(v))
        {
            if (!this.marked[w])
            {
                this.dfs(G, w, v);
            }
            else if (w != u)
            {
                this.hasCycle = true;
            }
        }
    }

    public boolean hasCycle()
    {
        return this.hasCycle;
    }

    public static void main(String[] args) {
        
    }
}
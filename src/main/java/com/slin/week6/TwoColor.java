package com.slin.week6;

public class TwoColor
{
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G)
    {
        this.marked = new boolean[G.V()];
        this.color = new boolean[G.V()];
        for (int s=0; s<G.V(); s++)
        {
            if (this.marked[s])
            {
                this.dfs(G, s);
            }
        }
    }

    public boolean isBipartite()
    {
        return this.isTwoColorable;
    }

    private void dfs(Graph G, int v)
    {
        this.marked[v] = true;
        for (int w : G.adj(v))
        {
            if (!this.marked[w])
            {
                this.color[w] = !this.color[v];
                this.dfs(G, w);
            }
            else if (this.color[w] == this.color[v])
            {
                this.isTwoColorable = false;
            }
        }
    }
}
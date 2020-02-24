package com.slin.week1;

/**
 * Union-Find with path compression
 */
public class QuickFindUF
{
    private int[] parent;
    private int[] size;

    public QuickFindUF(int n)
    {
        this.parent = new int[n];
        this.size = new int[n];

        for (int i = 0; i < n; i++)
        {
            this.parent[i] = i;
            this.size[i] = 0;
        }
    }

    /**
     * Check if p and q are connected
     * @param p
     * @param q
     * @return True if p and q are conneced, else False
     */
    public boolean connected(int p, int q)
    {
        return this.root(p) == this.root(q);
    }

    /**
     * Union for p and q
     * @param p
     * @param q
     */
    public void union(int p, int q)
    {
        int i = this.root(p);
        int j = this.root(q);
        if (i == j) return;

        if (this.size[i] < this.size[j])
        {
            this.parent[i] = j;
            this.size[j] += this.size[i];
        }
        else
        {
            this.parent[j] = i;
            this.size[i] += this.size[j];
        }
    }

    private int root(final int i)
    {
        int root = i;
        while (root != this.parent[i])
        {
            this.parent[root] = this.parent[this.parent[root]];
            root = this.parent[root];
        }
        return root;
    }
}
package com.slin.week6;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder
{
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G)
    {
        this.pre = new Queue<>();
        this.post = new Queue<>();
        this.reversePost = new Stack<>();
        this.marked = new boolean[G.V()];

        for (int v = 0; v<G.V(); v++)
        {
            if (!this.marked[v])
            {
                this.dfs(G, v);
            }
        }
    }

    public Iterable<Integer> pre()
    {
        return this.pre;
    }

    public Iterable<Integer> post()
    {
        return this.post;
    }

    public Iterable<Integer> resersePost()
    {
        return this.reversePost;
    }

    private void dfs(Digraph G, int v)
    {
        this.pre.enqueue(v);
        this.marked[v] = true;
        for (int w : G.adj(v))
        {
            if (!this.marked[w])
            {
                this.dfs(G, w);
            }
        }
        this.post.enqueue(v);
        this.reversePost.push(v);
    }
}
package com.slin.week6;

import java.util.HashMap;
import java.util.Map;

public class SymbolGraph
{
    protected Graph G;
    protected Map<String, Integer> nameToIdx;
    protected String[] idxToName;
    protected int count;

    public SymbolGraph()
    {
       this.init(0);
    }

    public SymbolGraph(String filename, String delim)
    {
        this.init(0);
    }

    public boolean contains(String key){
        return this.nameToIdx.containsKey(key);
    }

    public int index(String key)
    {
        return this.nameToIdx.get(key);
    }

    public String name(int v)
    {
        return this.idxToName[v];
    }

    public Graph G()
    {
        return this.G;
    }

    protected void init(int v)
    {
        this.count = 0;
        this.nameToIdx = new HashMap<>();
        this.idxToName = new String[v];
        this.G = new Graph(v);
    }

    public static void main(String[] args) {
    }
}
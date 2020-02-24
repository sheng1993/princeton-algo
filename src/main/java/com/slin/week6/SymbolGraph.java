package com.slin.week6;

import java.util.HashMap;
import java.util.Map;

public class SymbolGraph
{
    private Graph G;
    private Map<String, Integer> nameToIdx;
    private String[] idxToName;

    public SymbolGraph(String filename, String delim)
    {
        int V = 0;

        this.nameToIdx = new HashMap<>();
        this.idxToName = new String[V];
        this.G = new Graph(nameToIdx.size());
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
}
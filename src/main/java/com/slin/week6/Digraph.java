package com.slin.week6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.In;

public class Digraph extends Graph
{
    public Digraph(int V)
    {
        super(V);
    }
    
    @Override
    public void addEdge(int v, int w)
    {
        this.adj[v].add(w);
        this.E++;
    }

    public Digraph reverse()
    {
        Digraph R = new Digraph(this.V);
        for (int v=0; v<this.V; v++)
        {
            for (int w : this.adj(v))
            {
                R.addEdge(w, v);
            }
        }

        return R;
    }

    public static void main(String[] args) {
        String[] lines = new In("C:\\Users\\shlin\\Desktop\\graph.txt").readAllLines();

        Map<String, Map<String, List<String>>> libs = new HashMap<>();

        int i = 0;
        while (i < lines.length)
        {
            String lib = lines[i++];
            Map<String, List<String>> windows = new HashMap<>();
            int windowsCount = Integer.parseInt(lines[i++]);
            for(int j=0; j<windowsCount; j++)
            {
                String window = lines[i++];
                int refCount = Integer.parseInt(lines[i++]);
                List<String> references = new ArrayList<>();
                for(int z=0; z<refCount; z++)
                {
                    references.add(lines[i++]);
                }
                windows.put(window, references);
            }

            libs.put(lib, windows);
        }

        int vertexCount = 0;
        for (String lib : libs.keySet())
        {
            vertexCount += libs.get(lib).size();
        }
        
        Digraph G = new Digraph(5);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        System.out.println(G.toString());
    }
}
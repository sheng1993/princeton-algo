package com.slin.poc;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.slin.week6.DirectedCycle;

import edu.princeton.cs.algs4.Stack;

public class CircularReference
{
    private ReferenceGraph RG;
    private boolean[] marked;
    private int[] edgeTo;
    private Set<Integer> blacklisted;

    public CircularReference(ReferenceGraph RG)
    {
        this.RG = RG;
        this.blacklisted = new HashSet<>();
        this.populateLibraryGraph();
    }

    public Collection<Integer> blacklist()
    {
        return this.blacklisted;
    }

    public void populateLibraryGraph()
    {
        this.marked = new boolean[this.RG.V()];
        this.RG.libraryGraph().reset();
        for (int v=0; v<this.RG.G().V(); v++)
        {
            if (!this.marked[v])
            {
                this.simpleDfs(v);
            }
        }
    }

    private void simpleDfs(int v)
    {
        this.marked[v] = true;
        String[] sourceParts = this.RG.idxToName(v).split("@");
        for (int w : this.RG.G().adj(v))
        {
            String[] targetParts = this.RG.idxToName(w).split("@");
            if (sourceParts[0] != targetParts[0] && !this.blacklisted.contains(v) && !this.blacklisted.contains(w))
            {
                this.RG.libraryGraph().addEdge(this.RG.libToIdx(sourceParts[0]), this.RG.libToIdx(targetParts[0]));
            }
            if (!this.marked[w])
            {
                this.simpleDfs(w);
            }
        }
    }

    public void process()
    {
        int cycledWindow = -1;
        
        System.out.println("Starting to check for cycles...");
        do
        {
            cycledWindow = -1;           
            

            tranversal:
            for (String lib : this.RG.getLibs().keySet())
            {               
                this.marked = new boolean[this.RG.V()];
                this.edgeTo = new int[this.RG.V()];
                for (int i=0; i<this.RG.V(); i++)
                {
                    this.edgeTo[i] = -1;
                }
                this.RG.libraryGraph().reset();

                Map<String, List<String>> wins = this.RG.getLibs().get(lib);
                for (String win : wins.keySet())
                {
                    String name = lib + "@" + win;
                    int windowIdx = this.RG.nameToIdx(name);
                    if (!this.marked[windowIdx])
                    {
                        cycledWindow = this.dfs(windowIdx);
                        if (cycledWindow != -1)
                        {
                            break tranversal;
                        }
                    }
                }
            }

            if (cycledWindow != -1)
            {
                // Blacklist cycled window
                this.blacklist(cycledWindow);
            }
        }
        while (cycledWindow != -1);

        System.out.println("Finished cheking cycles...");
        System.out.println(String.format("Total number of windows to be moved: %d of %d", this.blacklisted.size(), this.RG.V()));
    }

    private int dfs(int v)
    {
        this.marked[v] = true;
        
        int parentIdx = this.edgeTo[v];
        if (parentIdx != -1)
        {
            int result = this.checkCycle(parentIdx, v);
            if (result != -1)
            {
                return result;
            }
        }

        for (int w : this.RG.G().adj(v))
        {         
            if (this.blacklisted.contains(w))
            {
                continue;
            }
            
            if (!this.marked[w])
            {
                this.edgeTo[w] = v;
                int result =  this.dfs(w);
                if (result != -1)
                {
                    return result;
                }
            }
            else
            {
                int result = this.checkCycle(v, w);
                if (result != -1)
                {
                    return result;
                }
            }
        }

        return -1;
    }

    private int checkCycle(int source, int target)
    {
        String[] sourceParts = this.RG.idxToName(source).split("@");
        String[] targetParts = this.RG.idxToName(target).split("@");

        // Avoid self-loop
        if (sourceParts[0] != targetParts[0] && !this.RG.libraryGraph().hasEdge(this.RG.libToIdx(sourceParts[0]), this.RG.libToIdx(targetParts[0])))
        {
            this.RG.libraryGraph().addEdge(this.RG.libToIdx(sourceParts[0]), this.RG.libToIdx(targetParts[0]));
        }

        DirectedCycle cycleDetector = new DirectedCycle(this.RG.libraryGraph());            
        if(cycleDetector.hasCycle())
        {
            this.printCycle(source, target);
            return target;
        }

        return -1;
    }

    private void printCycle(int v, int w)
    {
        Stack<Integer> cycle = new Stack<Integer>();
        
        cycle.push(w);
        for (int x=v; x!=-1; x=this.edgeTo[x])
        {
            cycle.push(x);
        }        

        for (Integer e : cycle)
        {            
            System.out.print(String.format(" %s ->", this.RG.idxToName(e)));
        }

        System.out.println();
    }

    private void blacklist(int w)
    {
        for (int x : this.RG.pathTo(w))
        {
            this.blacklisted.add(x);
        }

        String[] parts = this.RG.idxToName(w).split("@");
        Map<String, List<String>> wins = this.RG.getLibs().get(parts[0]);
        for (String ref : wins.get(parts[1]))
        {
            try
            {
                this.blacklist(this.RG.nameToIdx(this.RG.firstMatchName(ref)));
            }
            catch(Exception e)
            {
                
            }
        }
    }
}
package com.slin.poc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.slin.week6.Digraph;
import com.slin.week6.SymbolDigraph;

import edu.princeton.cs.algs4.In;

public class ReferenceGraph extends SymbolDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private Map<String, Map<String, List<String>>> libs;
    private int V;
    private Map<String, List<String>> windowOrder;
    private Digraph LG;
    private Map<String, Integer> libToIdx;
    private String[] idxToLib;
    private HierarchySearch hierarchySearch;

    public ReferenceGraph() {
        this.load("C:\\Users\\shlin\\Desktop\\graph2.txt");
        this.init(this.V);
        this.initLibraryGraph();

        this.windowOrder = new HashMap<>();

        for (String lib : this.libs.keySet()) 
        {
            Map<String, List<String>> wins = this.libs.get(lib);
            for (String win : wins.keySet())
            {
                this.addVertex(lib, win);
            }
        }

        for (String lib : this.libs.keySet()) 
        {
            Map<String, List<String>> wins = this.libs.get(lib);
            for (String win : wins.keySet())
            {
                String name = lib + "@" + win;
                for (String ref : wins.get(win)) 
                {
                    if (this.windowOrder.containsKey(ref)) 
                    {
                        this.G.addEdge(this.nameToIdx.get(name), this.nameToIdx.get(this.firstMatchName(ref)));
                    }
                }
            }
        }

        this.hierarchySearch = new HierarchySearch(this.G.reverse());
    }

    public int nameToIdx(String name) {
        return this.nameToIdx.get(name);
    }

    public int libToIdx(String name) {
        return this.libToIdx.get(name);
    }

    public String idxToName(int v) {
        return this.idxToName[v];
    }

    public Map<String, Map<String, List<String>>> getLibs() {
        return this.libs;
    }

    public Digraph libraryGraph() {
        return this.LG;

    }

    public Iterable<Integer> pathTo(int v) {
        return this.hierarchySearch.pathTo(v);
    }

    public int V() {
        return this.V;
    }

    private void initLibraryGraph() {
        int libraryCount = this.libs.size();
        this.LG = new Digraph(libraryCount);
        this.libToIdx = new HashMap<>();
        this.idxToLib = new String[libraryCount];
        int count = 0;

        for (String lib : this.libs.keySet()) {
            this.idxToLib[count] = lib;
            this.libToIdx.put(lib, count++);
        }
    }

    public String firstMatchName(String window) {
        if (!this.windowOrder.containsKey(window)) {
            throw new RuntimeException("No window with name " + window + " found.");
        }

        return this.windowOrder.get(window).get(0) + "@" + window;
    }

    private void addVertex(String lib, String win) {
        String name = lib + "@" + win;
        this.idxToName[this.count] = name;
        this.nameToIdx.put(name, this.count++);

        if (!this.windowOrder.containsKey(win)) {
            this.windowOrder.put(win, new LinkedList<>());
        }

        this.windowOrder.get(win).add(lib);
    }

    private void load(String filename) {
        String[] lines = new In(filename).readAllLines();
        libs = new LinkedHashMap<>();

        int i = 0;
        while (i < lines.length) {
            String lib = lines[i++];
            Map<String, List<String>> windows = new HashMap<>();
            int windowsCount = Integer.parseInt(lines[i++]);
            for (int j = 0; j < windowsCount; j++) {
                String[] windowInfo = lines[i++].split(" ");

                if (!windows.containsKey(windowInfo[0]))
                {
                    List<String> references = new ArrayList<>();                    
                    windows.put(windowInfo[0], references);
                }
                windows.get(windowInfo[0]).add(windowInfo[1]);             
            }

            libs.put(lib, windows);
        }

        for (String lib : libs.keySet()) {
            this.V += libs.get(lib).size();
        }
    }

    private void exportLibraryGraph(String file, Collection<Integer> blacklist)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("digraph G {" + NEWLINE);

        for (int l=0; l<this.LG.V(); l++)
        {
            buffer.append(String.format("%d [label=\"%s\"]" + NEWLINE, l, this.idxToLib[l]));
        }

        Set<String> references = new HashSet<>();

        for (int v=0; v<this.LG.V(); v++)
        {
            for (int w : this.LG.adj(v))
            {
                references.add(String.format("%d -> %d;" + NEWLINE, v, w));
            }
        }

        if (blacklist != null)
        {
            buffer.append(String.format("%d [label=\"%s\"]" + NEWLINE, this.LG.V(), "CommonLib"));
            for (int b : blacklist)
            {
                String[] parts = this.idxToName(b).split("@");
                references.add(String.format("%s -> %d;" + NEWLINE, this.libToIdx.get(parts[0]), this.LG.V()));
            }
        }

        for (String ref : references)
        {
            buffer.append(ref);
        }

        buffer.append("}");
        try (FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(buffer.toString());

        } 
        catch (IOException e) 
        {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void main(String[] args) {
        ReferenceGraph RG = new ReferenceGraph();        
        CircularReference CR = new CircularReference(RG);
        RG.exportLibraryGraph("C:/workspace/Java/princeton-algo/src/main/java/com/slin/poc/before.dot", null);
        CR.process();
        CR.populateLibraryGraph();
        RG.exportLibraryGraph("C:/workspace/Java/princeton-algo/src/main/java/com/slin/poc/after.dot", CR.blacklist());

        //RG.blacklisted.stream().limit(30).forEach((n) -> {System.out.print(RG.idxToName[n] + " ");});
        
        Map<String, Integer> wordCount = CR.blacklist().stream().map(i -> RG.idxToName[i])
        .map(name -> new AbstractMap.SimpleEntry<>(name.split("@")[0], 1))
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (v1, v2) -> v1 + v2));
        wordCount.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));

        System.out.println("\r\nWindows blacklisted:");
        for (int w : CR.blacklist())
        {
            System.out.println(RG.idxToName(w));
        }

        assert RG != null;
    }
}
import java.io.*;
import java.util.*;
import java.util.Collection;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.HashMap;

public class GraphBuilder{
    
    
    public static Graph buildGraph(ArrayList<String> lines){
        Graph graph = new Graph();
        for(String line : lines){
            String[] parts = line.split(" ");
            String u = parts[0]; //vertex
            String v = parts[1]; //vertex
            int w = Integer.parseInt(parts[2]); //weight

            graph.V.add(u);
            graph.V.add(v);

            graph.E.putIfAbsent(u, new HashSet<>());
            graph.E.get(u).add(v);

            graph.E.putIfAbsent(v, new HashSet<>());
            graph.E.get(v).add(u);

            // Homemade tuple, do java have a builtin pair/tuple??
            Pair<String, String> uv = new Pair<>(u, v);
            Pair<String, String> vu = new Pair<>(v, u);

            graph.w.put(uv, w);
            graph.w.put(vu, w);
        }
        System.out.println("Graph: " + graph.V);
        // System.out.println(graph.E);
        // System.out.println(graph.w);
        return graph;
    }

    public static void main(String[] args){ //should be recursive??
        Scanner scanner = null;
        File file = new File("graph");
        ArrayList<String> lines = new ArrayList<>();
        try{
            scanner = new Scanner(file);
        }catch (FileNotFoundException e){
            System.err.println(e);
        }
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().strip();
            lines.add(line);
        }
        
        Graph G = buildGraph(lines);
        System.out.println("DFS: " + DFS.dfs_rec(G, "A", new HashSet<String>(), new ArrayList<>()));
        // System.out.println("DFS: " + dfs(G, "A"));
        System.out.println("BFS: " + BFS.bfs(G, "A"));
        scanner = new Scanner(System.in);
        
        // System.out.println(bfsShortestPathBetween(G, "A", "G"));
        System.out.println("Shortest paths from A using bfs:");
        BFS.bfsAllShortestPaths(G, "A");
        
        System.out.println("\nShortest paths from A using dijk:");
        Dijkstra.dijkAllShortestPaths(G, "A");

        System.out.println("\nCost of moving from A using bfs:");
        HashMap<String,Integer> dist = Dijkstra.dijkstra(G, "A");
        for(String s : dist.keySet()){
            System.out.println(s + " = "+ dist.get(s));
        }
        
        System.out.println("\nPath from A to J with cost using dijk:");
        HashMap<Integer, ArrayList<String>> path = Dijkstra.dijkShortestPathBetweenCost(G, "A", "J");
        for(int i : path.keySet()){
            System.out.println("Cost: " + i + ", Path: " + path.get(i));
        }

        scanner.close();
    }
}

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BFS {
    
    public static ArrayList<String> bfs(Graph G, String s){
        Set<String> visited = new HashSet<>();
        visited.add(s);
        Deque<String> queue = new ArrayDeque<>();
        queue.addLast(s); //O(1)
        ArrayList<String> result = new ArrayList<>();
        // System.out.print(s +" --> ");

        while(!queue.isEmpty()){
            String u = queue.removeFirst(); //O(1)
            result.add(u);
            for(String v : G.E.get(u)){
                if(!visited.contains(v)){
                    // System.out.print(v+" --> ");
                    visited.add(v);
                    queue.addLast(v);
                }
            }
        }
        // System.out.println("None");
        return result;
    }
    public static HashMap<String,String> bfsShortestPathFrom(Graph G, String s){
        HashMap<String,String> parents = new HashMap<>();
        parents.put(s, null);
        Deque<String> queue = new ArrayDeque<>();
        queue.addLast(s); //O(1)

        while(!queue.isEmpty()){
            String u = queue.removeFirst(); //O(1)
            for(String v : G.E.get(u)){
                if(!parents.containsKey(v)){
                    parents.put(v, u);
                    queue.addLast(v);
                }
            }
        }
        // System.out.println("None");
        return parents;
    }

    public static ArrayList<String> bfsShortestPathBetween(Graph G, String s, String t){
        HashMap<String,String> parents = bfsShortestPathFrom(G, s);
        String v = t;
        ArrayList<String> path = new ArrayList<>();
        if(!parents.containsKey(t)){
            return path;
        }
        while(v != null){
            path.add(v);
            v = parents.get(v);
        }
        Collections.reverse(path);
        return path;
    }

    public static ArrayList<ArrayList<String>> bfsAllShortestPaths(Graph G, String s){
        HashMap<String,String> parents = bfsShortestPathFrom(G, s);
        ArrayList<ArrayList<String>> paths = new ArrayList<>();

        for(String v : G.V){
            ArrayList<String> path = new ArrayList<>();
            while(parents.containsKey(v)){
                path.add(v);
                v = parents.get(v);
            }
            Collections.reverse(path);
            paths.add(path);
            System.out.println(path);
        }
        return paths;
    }
}

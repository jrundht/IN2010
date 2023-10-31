import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;

public class Dijkstra {

    public static HashMap<String,Integer> dijkstra(Graph G, String s){
        Deque<Pair<Integer, String>> queue = new ArrayDeque<>();
        queue.add(new Pair<Integer, String>(0, s));

        HashMap<String,Integer> dist = new HashMap<>();
        dist.put(s, 0);

        while(!queue.isEmpty()){
            Pair<Integer, String> pair = queue.removeFirst();
            int cost = pair.first;
            String u = pair.second;

            if(cost != dist.getOrDefault(u, Integer.MAX_VALUE)) continue;

            for(String v : G.E.get(u)){
                int c = cost + G.w.get((new Pair<String, String> (u, v)));
                int vCost = dist.getOrDefault(v, Integer.MAX_VALUE);
                if(c < vCost){
                    dist.put(v, c);
                    queue.addLast(new Pair<Integer, String>(c, v));
                }   
            }
        }
        return dist;
    }
    
    public static HashMap<String,String> dijkShortestPathFrom(Graph G, String s){
        HashMap<String,String> parents = new HashMap<>();
        parents.put(s, null);

        Deque<Pair<Integer, String>> queue = new ArrayDeque<>();
        queue.add(new Pair<Integer, String>(0, s));

        HashMap<String,Integer> dist = new HashMap<>();
        dist.put(s, 0);

        while(!queue.isEmpty()){
            Pair<Integer, String> pair = queue.removeFirst();
            int cost = pair.first;
            String u = pair.second;

            if(cost != dist.getOrDefault(u, Integer.MAX_VALUE)) continue;

            for(String v : G.E.get(u)){
                int c = cost + G.w.get((new Pair<String, String> (u, v)));
                if(c < dist.getOrDefault(v, Integer.MAX_VALUE)){
                    dist.put(v, c);
                    queue.addLast(new Pair<Integer, String>(c, v));
                    parents.put(v, u);
                }   
            }
        }
        return parents;
    }
    
    public static HashMap<Integer, ArrayList<String>> dijkShortestPathBetweenCost(Graph G, String s, String t){
        HashMap<String,String> parents = dijkShortestPathFrom(G, s);

        String v = t;
        HashMap<Integer, ArrayList<String>> path = new HashMap<>();
        if(!parents.containsKey(t)){
            return path;
        }
        int cost = dijkstra(G, s).get(t);
        ArrayList<String> steps = new ArrayList<>();
        path.put(cost, steps);

        while(v != null){
            steps.add(v);
            v = parents.get(v);
        }
        Collections.reverse(steps);
        path.put(cost, steps);
        
        return path;
    }

    public static ArrayList<ArrayList<String>> dijkAllShortestPaths(Graph G, String s){
        HashMap<String,String> parents = dijkShortestPathFrom(G, s);
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

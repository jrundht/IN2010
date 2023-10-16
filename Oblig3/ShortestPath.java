import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;

public class ShortestPath{
    /*
     * Takes in a Graph and an actor, and finds the shortest path to all other actors
     */
    public static HashMap<Vertex, Vertex> shortestPathFrom(Graph G, Actor s){
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        parents.put(s, null);

        Deque<Tuple<Double, Vertex>> queue = new ArrayDeque<>();
        queue.add(new Tuple<Double, Vertex>(0.0, s));

        HashMap<Vertex,Double> dist = new HashMap<>();
        dist.put(s, (double) 0);

        while(!queue.isEmpty()){
            Tuple<Double, Vertex> pair = queue.removeFirst();
            double cost = pair.element1;
            Vertex u = pair.element2;

            if(cost != dist.getOrDefault(u, Double.MAX_VALUE)) continue;

            for(Vertex v : G.E.get(u)){
                double c = cost + G.w.get((new Edge(u, v)));
                if(c < dist.getOrDefault(v, Double.POSITIVE_INFINITY)){
                    dist.put(v, c);
                    queue.addLast(new Tuple<Double, Vertex>(c, v));
                    parents.put(v, u);
                }
            }
        }
        return parents;    
    }
    
    public static ArrayList<Vertex> shortestPathBetween(Graph G, Actor s, Actor t){
        HashMap<Vertex, Vertex> parents = shortestPathFrom(G, s);
        
        Vertex v = t;
        if(!parents.containsKey(v)) return null;
        
        ArrayList<Vertex> path = new ArrayList<>();
        while(v != null){
            path.add(v);
            v = parents.get(v);
        }
        Collections.reverse(path);
        
        return path;    
    }
    
    public static HashMap<Vertex, Vertex> chillestPathFrom(Graph G, Actor s){
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        parents.put(s, null);

        Deque<Tuple<Double, Vertex>> queue = new ArrayDeque<>();
        queue.add(new Tuple<Double, Vertex>( 0.0, s));

        HashMap<Vertex,Double> dist = new HashMap<>();
        dist.put(s, (double) 0);

        while(!queue.isEmpty()){
            Tuple<Double, Vertex> pair = queue.removeFirst();
            double cost = pair.element1; //a rating will never be higher than 10.0
            Vertex u = pair.element2;

            if(cost != dist.getOrDefault(u, Double.MAX_VALUE)) continue;
            
            for(Vertex v : G.E.get(u)){
                double c = cost + (10 - G.w.get((new Edge(u, v))));
                if(c <  dist.getOrDefault(v, Double.POSITIVE_INFINITY)){
                    dist.put(v, c);
                    queue.addLast(new Tuple<Double, Vertex>(c, v));
                    parents.put(v, u);
                }
            }
        }
        return parents;    
    }

    public static ArrayList<Vertex> chillestPathBetween(Graph G, Actor s, Actor t){
        HashMap<Vertex, Vertex> parents = chillestPathFrom(G, s);
        
        Vertex v = t;
        if(!parents.containsKey(v)) return null;
        
        ArrayList<Vertex> path = new ArrayList<>();
        while(v != null){
            path.add(v);
            v = parents.get(v);
        }
        Collections.reverse(path);
        
        return path;    
    }

    // O(|E|)
    public static Set<Vertex> visited(Graph G, Vertex u){
        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(u);
        
        while(!stack.isEmpty()){
            Vertex v = stack.pop();

            if(!visited.contains(v)){
                visited.add(v);
                for(Vertex current : G.E.get(v)){
                    if(!visited.contains(current)){
                        stack.push(current);
                    }           
                }
            }
        }
        return visited;
    }

    // O((|V| + |E|)*n)??
    public static HashMap<Integer, Integer> components(Graph G){
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> oneVisit = new HashSet<>();
        HashMap<Integer, Integer> components = new HashMap<>();

        for(Vertex v : G.E.keySet()){
            if(!visited.contains(v)){
                oneVisit = visited(G, v); //go through one component
                visited.addAll(oneVisit); //O(n)

                int count = components.getOrDefault(oneVisit.size(), 0);
                components.put(oneVisit.size(), count + 1);
            }
        }
        return components;
    }
}
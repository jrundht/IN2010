import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ShortestPath{
    /*
     * Takes in a Graph and an actor, and finds the shortest path to all other actors
     */
    public static ArrayList<Vertex> bfsShortestPath(Graph G,  Actor s, Actor t){
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        parents.put(s, null);
        Deque<Vertex> queue = new ArrayDeque<>();
        queue.addLast(s); //O(1)

        outerloop:
        while(!queue.isEmpty()){
            Vertex u = queue.removeFirst();
            for(Vertex v : G.E.get(u)){
                if(!parents.containsKey(v)){
                    parents.put(v, u);
                    queue.addLast(v);
                }
                if(v.equals(t)) break outerloop;
            }
        }
        
        Vertex v = t;
        ArrayList<Vertex> path = new ArrayList<>();
        while(v != null){
            path.add(v);
            v = parents.get(v);
        }
        Collections.reverse(path);
        return path;
    }

    public static ArrayList<Vertex> chillestPathFrom(Graph G, Actor s, Actor t){
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        parents.put(s, null);

        PriorityQueue<Tuple<Double, Vertex>> queue = new PriorityQueue<>(); //priorityqueue
        queue.add(new Tuple<Double, Vertex>( 0.0, s));

        HashMap<Vertex,Double> dist = new HashMap<>();
        dist.put(s, (double) 0);

        outerloop:
        while(!queue.isEmpty()){ //when you pop actor t from queue, you will have found the shortest path
            Tuple<Double, Vertex> pair = queue.poll();
            double cost = pair.element1; //a rating will never be higher than 10.0
            Vertex u = pair.element2;
            
            if(u.equals(t)) break outerloop;
            
            if(cost != dist.getOrDefault(u, Double.MAX_VALUE)) continue;
            
            for(Vertex v : G.E.get(u)){
                double c = cost + (10 - G.w.get((new Edge(u, v))));
                if(c <  dist.getOrDefault(v, Double.POSITIVE_INFINITY)){
                    dist.put(v, c);
                    queue.add(new Tuple<Double, Vertex>(c, v));
                    parents.put(v, u);
                }
            }
        }

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
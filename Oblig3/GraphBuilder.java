import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphBuilder {
    Graph graph = new Graph();
    HashMap<String, Actor> actors = new HashMap<>();

    public void addMovies(ArrayList<String> lines){
        for(String line : lines){
            // String[] parts = line.split("\t");
            Vertex v = graph.addVertex(line);
            graph.E.put(v, new HashSet<>()); //in case somehow a movie is twice in the same list
        }
    }

    /*
     * One line is on the form ID   name    movieid     movieid....
     */
    public void addActors(ArrayList<String> lines){
        for(String line : lines){
            String[] parts = line.split("\t");
            
            Vertex v = graph.addVertex(line);
            graph.E.put(v, new HashSet<>()); //in case somehow an actor is twice in the same list
            actors.put(parts[1], (Actor) v);
            
            Movie u = null;
            
            //connect to movies
            for(int i = 2; i < parts.length; i++){
                u = (Movie) graph.findByID(parts[i]);
                
                if(u == null) continue;
                graph.addEdge(v, u);
                graph.addEdge(u, v); 

                graph.w.put(new Edge(u, v), u.getRating());
                graph.w.put(new Edge(v, u), u.getRating());
            }

        }
    }

    public Actor findActor(String name){
        return actors.get(name);
    }

    public void printNodesEdges(){
        System.out.println("Nodes: " + graph.V.size());
        System.out.println("Edges: " + graph.w.size());
    }
}

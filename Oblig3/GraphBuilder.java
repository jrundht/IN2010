import java.util.ArrayList;
import java.util.HashSet;

public class GraphBuilder {
    Graph graph = new Graph();

    public void addMovies(ArrayList<String> lines){
        for(String line : lines){
            String[] parts = line.split("\t");
            Vertex v = graph.addVertex(line);
            // graph.E.putIfAbsent(v, new HashSet<>()); //in case somehow a movie is twice in the same list
            graph.E.put(v, new HashSet<>()); //in case somehow a movie is twice in the same list
        }
    }

    /*
     * One line is on the form ID   name    movieid     movieid....
     */
    public void addActors(ArrayList<String> lines){
        for(String line : lines){
            //first, make vertex
            Vertex v = graph.addVertex(line);
            // graph.E.putIfAbsent(v, new HashSet<>()); //in case somehow an actor is twice in the same list
            graph.E.put(v, new HashSet<>()); //in case somehow an actor is twice in the same list

            String[] parts = line.split("\t");
            Movie u = null;
            //connect to movies
            for(int i = 2; i < parts.length; i++){
                u = (Movie) graph.findByID(parts[i]);
                
                if(u == null) break;

                graph.addEdge(v, u);
                graph.addEdge(u, v);

                graph.w.put(new Edge(u, v), u.getRating());
                graph.w.put(new Edge(v, u), u.getRating());
            }
        }
    }

    public void printNodesEdges(){
        System.out.println("Nodes: " + graph.V.size());
        System.out.println("Edges: " + graph.w.size());
    }
}

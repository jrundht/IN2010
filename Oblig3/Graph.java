import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph {
    Set<Vertex> V = new HashSet<>();                    //All Nodes/Vertices
    HashMap<String, Vertex> vertices = new HashMap<>(); //Hashmap to find vertices by id
    HashMap<Vertex, Set<Vertex>> E = new HashMap<>();   //All Edges, represented
    HashMap<Edge, Double> w = new HashMap<>();          //Edges, and weight/the cost to cross from one to the other

    public Vertex addVertex(String data){
        Vertex newVertex = null;
        String[] values = data.split("\t");
        if(data.charAt(0) == 't'){
            newVertex = new Movie(values[0], values[1], Double.parseDouble(values[2]));
        }else{
            newVertex = new Actor(values[0], values[1]);
        }
        V.add(newVertex);
        vertices.put(values[0], newVertex);
        return newVertex;
    }

    public Vertex findByID(String id){
        if(vertices.containsKey(id)){
            return vertices.get(id);
        }
        return null;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2){
        E.get(vertex1).add(vertex2);
    }
}
 
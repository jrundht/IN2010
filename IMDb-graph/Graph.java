import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph {
    Set<Vertex> V = new HashSet<>(250000);                    //All Nodes/Vertices
    HashMap<String, Vertex> vertices = new HashMap<>(250000); //Hashmap to find vertices by id
    HashMap<Vertex, Set<Vertex>> E = new HashMap<>(250000);   //All Edges, represented
    HashMap<Edge, Double> w = new HashMap<>(2000000);          //Edges, and weight/the cost to cross from one to the other

    public Vertex addVertex(String data){
        Vertex newVertex = null;
        try {
            String[] values = data.split("\t");
            if(data.charAt(0) == 't'){
                newVertex = new Movie(values[0], values[1], Double.parseDouble(values[2]));
                if(values.length == 4) ( (Movie) newVertex).year = Integer.parseInt(values[3]);
            }else{
                newVertex = new Actor(values[0], values[1]);
            }
            
            V.add(newVertex);
            vertices.put(values[0], newVertex);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing: " + e.getMessage());
        }
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
 
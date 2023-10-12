import java.util.*;

public class Edge {
    String node1;
    String node2;

    public Edge(String node1, String node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return node1.equals(edge.node1) && node2.equals(edge.node2);
    }

    @Override
    //Gives an id when edge is a key in a HashMap
    public int hashCode() {
        return Objects.hash(node1, node2);
    }

    @Override
    public String toString(){
        return "(" + node1 + ", " + node2 + ")";
    }
}
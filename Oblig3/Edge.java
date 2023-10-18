import java.util.*;

public class Edge{
    Vertex element1;
    Vertex element2;

    public Edge(Vertex element1, Vertex element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        @SuppressWarnings("unchecked")
        Edge pair = (Edge) obj;
        return element1.equals(pair.element1) && element2.equals(pair.element2);
    }

    @Override
    //Gives an id for iterating in a HashMap
    public int hashCode() {
        return Objects.hash(element1, element2);
    }

    @Override
    public String toString(){
        return "(" + element1 + ", " + element2 + ")";
    }
}
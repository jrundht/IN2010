import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph{
    Set<String> V = new HashSet<>(); //Nodes
    HashMap<String, Set<String>> E = new HashMap<>(); //Edges
    HashMap<Pair<String, String>, Integer> w = new HashMap<>(); //Pairs, and weight/the cost to cross from one to the other
}

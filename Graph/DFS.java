import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS {
     /*
     * G -> graph, s -> startnode, visited -> list of all the visited nodes, result -> all the nodes in the order of visit
     */
    public static ArrayList<String> dfs_rec(Graph G, String s, HashSet<String> visited, ArrayList<String> result){
        HashMap<String, Set<String>> E = G.E;
        result.add(s);
        visited.add(s);

        for(String v : E.get(s)){
            if(!visited.contains(v)){
                dfs_rec(G, v, visited, result);
            }
        }
        return result;
    }
    
    public static ArrayList<String> dfs(Graph G, String s){
        HashMap<String, Set<String>> E = G.E;
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();
        
        Stack<String> stack = new Stack<>();
        stack.add(s);

        result.add(s);
        visited.add(s);

        while(!stack.empty()){
            String u = stack.pop();
            if(!visited.contains(u)){
                result.add(u);
                visited.add(u);
                for(String v : E.get(s)){
                    stack.add(v);
                }
            }
        }
        return result;
    }
}

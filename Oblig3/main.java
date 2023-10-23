import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Main{
    static GraphBuilder graph = new GraphBuilder();
    static Graph g = graph.graph;

    public static void main(String[] args){
        ArrayList<String> movies = FileReadr.readFile("movies.tsv"); //O(N)
        graph.addMovies(movies);

        ArrayList<String> actors = FileReadr.readFile("actors.tsv"); //O(N)
        graph.addActors(actors);

        // Find path between two actors with input
        // lookUp();

        //print number of nodes/edges
        graph.printNodesEdges();

        // Shortest path with bfs
        System.out.print("\nShortest path:");
        Paths.bfs(g, graph.findActor("Donald Glover"), graph.findActor("Jeremy Irons"));
        
        Paths.bfs(g, graph.findActor("Scarlett Johansson"), graph.findActor("Emma Mackey"));
        
        Paths.bfs(g, graph.findActor("Carrie Coon"), graph.findActor("Julie Delpy"));
        
        Paths.bfs(g, graph.findActor("Christian Bale"), graph.findActor("Lupita Nyong'o"));
       
        Paths.bfs(g, graph.findActor("Tuva Novotny"), graph.findActor("Michael K. Williams"));

        //Chillest path
        System.out.print("\nChillest path:");
        Paths.chillest(g, graph.findActor("Donald Glover"), graph.findActor("Jeremy Irons"));

        Paths.chillest(g, graph.findActor("Scarlett Johansson"), graph.findActor("Emma Mackey"));

        Paths.chillest(g, graph.findActor("Carrie Coon"), graph.findActor("Julie Delpy"));
        
        Paths.chillest(g, graph.findActor("Christian Bale"), graph.findActor("Lupita Nyong'o"));
       
        Paths.chillest(g, graph.findActor("Tuva Novotny"), graph.findActor("Michael K. Williams"));
        
        // Components
        HashMap<Integer, Integer> components = Paths.components(g);
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(components); //sort components
        System.out.println();
        for(int i : sortedMap.keySet()){
            System.out.println("There are " + sortedMap.get(i) + " components of size " + i);
        }
    }
}

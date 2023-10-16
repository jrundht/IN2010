import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.Scanner;

public class m{
    static GraphBuilder graph = new GraphBuilder();
    static Graph g = graph.graph;

    public static void lookUp(){
        Scanner scanner = new Scanner(System.in);
        boolean goOn = true;
        System.out.print("\nWrite the name of two actors you want to connect, separate the names with a tab. (quit? press n): ");
        String inp = scanner.nextLine();
        ArrayList<Vertex> path = ShortestPath.chillestPathBetween(g, graph.actors.get("Donald Glover"), graph.actors.get("Jeremy Irons"));

        while(goOn){
            if(inp.equals("n")) return;
            String[] names = inp.split("\t");
            Actor name1 = graph.findActor(names[0]);
            Actor name2 = graph.findActor(names[1]);

            if(name1 != null && name2 != null){
                path = ShortestPath.shortestPathBetween(g, name1, name2);
                System.out.println("\nShortest path " + name1 + " and " + name2 + " are connected through:");
                for(int i = 1; i < path.size()-1; i ++){
                    System.out.println("===[ " + path.get(i) + " ] ===> " + path.get(++i));
                    
                }
                path = ShortestPath.chillestPathBetween(g, name1, name2);
                System.out.println("\nMost enjoyable path " + name1 + " and " + name2 + " are connected through:");
                for(int i = 1; i < path.size()-1; i ++){
                    System.out.println("===[ " + path.get(i) + " ] ===> " + path.get(++i));
                    
                }
            }else{
                if(name1 == null){
                    System.out.println(names[0] + " is not in the dataset");
                }
                if(name2 == null){
                    System.out.println(names[1] + " is not in the dataset");
                }
            }
            
            System.out.print("\nWrite the name of two actors you want to connect, separate the names with a tab. (quit? press n): ");
            inp = scanner.nextLine();
        }
        scanner.close();
    }    

    public static void main(String[] args){
        ArrayList<String> movies = FileReadr.readFile("six-degrees-of-imdb-ressursside-main/movies.tsv"); //O(N)
        graph.addMovies(movies);

        ArrayList<String> actors = FileReadr.readFile("six-degrees-of-imdb-ressursside-main/actors.tsv"); //O(N)
        graph.addActors(actors);

        lookUp();

        // ArrayList<Vertex> path = ShortestPath.shortestPathBetween(g, graph.actors.get("Donald Glover"), graph.actors.get("Jeremy Irons"));
        // System.out.println("\n" + "Shortest path: Donald Glover" + " and " + "Jeremy Irons" + " are connected through:");
        // for(int i = 1; i < path.size()-1; i ++){
        //     System.out.println("===[ " + path.get(i) + " ] ===> " + path.get(++i));
                    
        // }
        // path = ShortestPath.chillestPathBetween(g, graph.actors.get("Donald Glover"), graph.actors.get("Jeremy Irons"));
        // System.out.println("\n" + "Chillest path: Donald Glover" + " and " + "Jeremy Irons" + " are connected through:");
        // for(int i = 1; i < path.size()-1; i ++){
        //     System.out.println("===[ " + path.get(i) + " ] ===> " + path.get(++i));
                    
        // }
        
        HashMap<Integer, Integer> components = ShortestPath.components(g);
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(components);
        for(int i : sortedMap.keySet()){
            System.out.println("There are " + sortedMap.get(i) + " components of size " + i);
        }
    }
}

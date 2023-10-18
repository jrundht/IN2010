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
                path = ShortestPath.bfsShortestPath(g, name1, name2);
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

    public static void bfs(Actor actor1, Actor actor2){
        ArrayList<Vertex> path = ShortestPath.bfsShortestPath(g,actor1, actor2);
        // System.out.println("\n" + "Shortest path: " + actor1 + " and " + actor2 + " are connected through:");
        System.out.println("\n" + actor1);
        for(int i = 1; i < path.size()-1; i ++){
            System.out.println("===[ " + path.get(i) + " ] ===> " + path.get(++i));
        }
    }
    
    public static void chillest(Actor actor1, Actor actor2){
        ArrayList<Vertex> path = ShortestPath.chillestPathBetween(g,actor1, actor2);
        // System.out.println("\n" + "Most enjoyable path: " + actor1 + " and " + actor2 + " are connected through:");
        System.out.println("\n" + actor1);
        double w = 0.0;
        for(int i = 1; i < path.size()-1; i ++){
            if(path.get(i) instanceof Movie){
                Movie m = (Movie) path.get(i);
                w += 10 - m.getRating();
            }
            System.out.println("===[ " + path.get(i) + " ] ===> " + path.get(++i));
        }
        System.out.printf("Total weight: %,.1f", w);
        System.out.println();
    }

    public static void main(String[] args){
        ArrayList<String> movies = FileReadr.readFile("six-degrees-of-imdb-ressursside-main/movies.tsv"); //O(N)
        graph.addMovies(movies);

        ArrayList<String> actors = FileReadr.readFile("six-degrees-of-imdb-ressursside-main/actors.tsv"); //O(N)
        graph.addActors(actors);

        // Find path between two actors
        // lookUp();

        // ShortestPath.bfsShortestPath(g,graph.findActor("Donald Glover"), graph.findActor("Jeremy Irons"));
        // Shortest path with bfs
        System.out.println("\nShortest path:");
        bfs(graph.findActor("Donald Glover"), graph.findActor("Jeremy Irons"));
        
        bfs(graph.findActor("Scarlett Johansson"), graph.findActor("Emma Mackey"));
        
        bfs(graph.findActor("Carrie Coon"), graph.findActor("Julie Delpy"));
        
        bfs(graph.findActor("Christian Bale"), graph.findActor("Lupita Nyong'o"));
       
        bfs(graph.findActor("Tuva Novotny"), graph.findActor("Michael K. Williams"));

        //Chillest path
        System.out.println("\nChillest path:");
        chillest(graph.findActor("Donald Glover"), graph.findActor("Jeremy Irons"));

        chillest(graph.findActor("Scarlett Johansson"), graph.findActor("Emma Mackey"));

        chillest(graph.findActor("Carrie Coon"), graph.findActor("Julie Delpy"));
        
        chillest(graph.findActor("Christian Bale"), graph.findActor("Lupita Nyong'o"));
       
        chillest(graph.findActor("Tuva Novotny"), graph.findActor("Michael K. Williams"));
        
        // Components
        HashMap<Integer, Integer> components = ShortestPath.components(g);
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(components);
        System.out.println();
        for(int i : sortedMap.keySet()){
            System.out.println("There are " + sortedMap.get(i) + " components of size " + i);
        }
    }
}

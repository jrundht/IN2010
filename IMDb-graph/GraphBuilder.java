import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class GraphBuilder {
    Graph graph = new Graph();
    HashMap<String, Actor> actors = new HashMap<>(150000);

    public void addMovies(ArrayList<String> lines){
        for(String line : lines){
            // String[] parts = line.split("\t");
            Vertex v = graph.addVertex(line);
            graph.E.put(v, new HashSet<>()); //in case somehow a movie is twice in the same list
        }
    }

    /*
     * One line is on the form ID   name    movieid     movieid....
     */
    public void addActors(ArrayList<String> lines){
        for(String line : lines){
            String[] parts = line.split("\t");
            
            Vertex v = graph.addVertex(line);
            graph.E.put(v, new HashSet<>()); //in case somehow an actor is twice in the same list
            actors.put(parts[1], (Actor) v);
            
            Movie u = null;
            
            //connect to movies
            for(int i = 2; i < parts.length; i++){
                u = (Movie) graph.findByID(parts[i]);
                
                if(u == null) continue;
                graph.addEdge(v, u);
                graph.addEdge(u, v); 

                graph.w.put(new Edge(u, v), u.getRating());
                graph.w.put(new Edge(v, u), u.getRating());
            }

        }
    }

    public Actor findActor(String name){
        return actors.get(name);
    }

    public void printNodesEdges(){
        System.out.println("Nodes: " + graph.V.size());
        System.out.println("Edges: " + graph.w.size());
    }

    public void lookUp(){
        try (Scanner scanner = new Scanner(System.in)) {
            boolean goOn = true;
            System.out.print("\nWrite the name of two actors you want to connect, separate the names with a tab. (quit? press n): ");
            String inp = scanner.nextLine();
            ArrayList<Vertex> path = Paths.chillestPathFrom(graph, actors.get("Donald Glover"), actors.get("Jeremy Irons"));

            while(goOn){
                if(inp.equals("n")) return;
                String[] names = inp.split("\t");
                Actor name1 = findActor(names[0]);
                Actor name2 = findActor(names[1]);

                if(name1 != null && name2 != null){
                    path = Paths.bfsShortestPath(graph, name1, name2);
                    System.out.println("\nShortest path " + name1 + " and " + name2 + " are connected through:");
                    for(int i = 1; i < path.size()-1; i ++){
                        System.out.println("===[ " + path.get(i) + " ] ===> " + path.get(++i));
                        
                    }
                    path = Paths.chillestPathFrom(graph, name1, name2);
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
    }
}

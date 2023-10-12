import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class main{
    
    public static void main(String[] args){
        GraphBuilder graph = new GraphBuilder();
        ArrayList<String> movies = FileReadr.readFile("six-degrees-of-imdb-ressursside-main/movies.tsv"); //O(N)
        graph.addMovies(movies);

        ArrayList<String> actors = FileReadr.readFile("six-degrees-of-imdb-ressursside-main/actors.tsv"); //O(N)
        graph.addActors(actors);

        System.out.println("Exp nodes: " + (movies.size()+actors.size()));
        graph.printNodesEdges();
    }
}

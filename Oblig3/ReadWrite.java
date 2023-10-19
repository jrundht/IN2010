import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
public class ReadWrite {
    private static HashMap<String, Movie> movies = new HashMap<>();

    public static ArrayList<String> readFile(String filename){
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(filename))){
            String line = bf.readLine();
            while(line != null){
                lines.add(line);
                line = bf.readLine();
            }
        }catch (IOException e){
            System.err.println(e);
        }
        return lines;
    }

    public static void writeNamesToFile(ArrayList<String> lines){
        File file = new File("six-degrees-of-imdb-ressursside-main/actors2.tsv");

        try(PrintWriter writer = new PrintWriter(file)){
            for(String line : lines){
                String[] parts = line.split("\t");
                if(parts[5].strip().equals("\\N")) continue;//skip those not connected to a movie

                String[] prof = parts[4].split(",");
                boolean actor = false;
                for(String p : prof){
                    if(p.strip().equals("actor") ||p.strip().equals("actress")) actor = true;
                }
                if(!actor) continue; //skip those who arent actors

                String[] movies = parts[5].split(",");
                writer.print(parts[0] + "\t" + parts[1] + "\t");

                for(String m : movies){
                    writer.print(m + "\t");
                }
                writer.print("\n");
            }
            writer.close();

        } catch (FileNotFoundException e){
            System.exit(-1);
        }
    }
    public static void writeMoviesToFile(ArrayList<String> lines){
        File file = new File("six-degrees-of-imdb-ressursside-main/movies2.tsv");

        try(PrintWriter writer = new PrintWriter(file)){
            for(String line : lines){
                String[] parts = line.split("\t");
                if(!parts[1].equals("movie")) continue;

                writer.print(parts[0] + "\t" + parts[2] + "\t" + parts[5] + "\n");

            }
            writer.close();

        } catch (FileNotFoundException e){
            System.exit(-1);
        }
    }

    public static void addMovies(ArrayList<String> lines){
        for(String line : lines){
            String[] parts = line.split("\t");
            try {
                Movie newMovie = new Movie(parts[0], parts[1], 0);
                if(!parts[2].strip().equals("\\N")) newMovie.year = Integer.parseInt(parts[2]);
                movies.put(parts[0], newMovie);
                
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
                System.out.println(line);
            }
        }
    }

    public static void addRating(ArrayList<String> lines){
        for(String line : lines){
            String[] parts = line.split("\t");
            try {
                Movie movie = movies.get(parts[0]);
                if(movie != null) movie.rating = Double.parseDouble(parts[1]);
                
            } catch (NullPointerException e) {
                System.err.println(e);
                System.out.println(parts[0]);
            }
        }
    }

    public static void writeMovieFile(){
        File file = new File("six-degrees-of-imdb-ressursside-main/movies_big_file.tsv");
        try(PrintWriter writer = new PrintWriter(file)){
            for(String m : movies.keySet()){
                Movie movie = movies.get(m);
                writer.print(movie.getID() + "\t" + movie.getName() + "\t" + movie.getRating() + "\t" + movie.year + "\n");

            }
            writer.close();

        } catch (FileNotFoundException e){
            System.exit(-1);
        }
    }


    public static void main(String[] args){
        // writeNamesToFile(readFile("six-degrees-of-imdb-ressursside-main/name.basics.tsv"));
        ArrayList<String> movies = readFile("six-degrees-of-imdb-ressursside-main/title.basics.tsv");
        writeMoviesToFile(movies);
        ArrayList<String> movie = readFile("six-degrees-of-imdb-ressursside-main/movies2.tsv");
        addMovies(movie);
        ArrayList<String> rating = readFile("six-degrees-of-imdb-ressursside-main/title.ratings.tsv");
        addRating(rating);
        writeMovieFile();
    }
}

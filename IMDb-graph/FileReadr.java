import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FileReadr{
    /*
     * Reads a single file into a arraylist, line by line
     */
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
}

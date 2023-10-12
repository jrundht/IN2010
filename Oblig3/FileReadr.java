import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class FileReadr{
    /*
     * Reads a single file into a arraylist, line by line
     */
    public static ArrayList<String> readFile(String filename){
        Scanner scanner = null;
        File file = new File(filename);
        ArrayList<String> lines = new ArrayList<>();
        try{
            scanner = new Scanner(file);
        }catch (FileNotFoundException e){
            System.err.println(e);
        }
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().strip();
            lines.add(line);
        }
        scanner.close();
        return lines;
    }
}

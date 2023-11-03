import java.util.ArrayList;
import java.util.Random;

public class RandomList {
    static ArrayList<Integer> createRandomList(int size){
        ArrayList<Integer> arr = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < size; i++){
            arr.add(rand.nextInt(100));
        }

        return arr;
    }
}

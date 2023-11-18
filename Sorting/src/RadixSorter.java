import java.util.ArrayList;
import java.util.Collections;

public class RadixSorter {


    public static void radixSort(ArrayList<Integer> A){
        //should find the biggest digit by iteration, which would add a O(n) operation
        int d = Collections.max(A);
        int digit = 0;

        // find out how many times d goes up in ten, to be used when looking at the ciphers
        while(d != 0){
            digit++;
            d = d / 10;
        }
        for(int i = 0; i < digit; i++){
            BucketSorter.bucketSort(A, i);
        }

    }

    public static void main(String[] args) {
        //ArrayList<Integer> A = RandomList.createRandomList(20);
        ArrayList<Integer> A = new ArrayList<>();
        Collections.addAll(A, 1814, 232, 2888, 31, 1455, 2242, 4345, 1470, 515, 3632);
        System.out.println(A);
        radixSort(A);
        System.out.println(A);
    }
}
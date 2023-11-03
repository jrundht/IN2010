import java.util.ArrayList;

public class RadixSorter {
    private static int N = 10;

    public static ArrayList<Integer> radixSort(ArrayList<Integer> A, int numSorts){
        for(int digit = 0; digit <= numSorts; digit++){
            A = BucketSorter.bucketSort(A, digit);
            /* add check to see if it is sorted?
             * if bucketsort is O(n) it might save some O(n) operations,
             * but it will add O(n) operations to each iteration of the loop
            */
        }
        return A;
    }
    public static void main(String[] args) {
        ArrayList<Integer> A = RandomList.createRandomList(20);
        //Collections.addAll(A, 1814, 232, 2888, 31, 1455, 2242, 4345, 1470, 515, 3632);
        System.out.println(A);
        A = radixSort(A, 10);
        System.out.println(A);
    }
}

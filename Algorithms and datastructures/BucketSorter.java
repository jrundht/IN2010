import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class BucketSorter {
    private static int N = 10;
    /*
     * One pass through bucketSort will order numbers by digit-place
     * e.g [1814, 232, 2888, 31, 1455, 2242, 4345, 1470, 515, 3632]
     * will sort to:
     * Sorted: [1470, 31, 232, 2242, 3632, 1814, 1455, 4345, 515, 2888]
     * where the last digit determined the ordering
     */
    public static ArrayList<Integer> bucketSort(ArrayList<Integer> A, int digit){
        var B = new ArrayList<ArrayList<Integer>>();
        //int[][] B = new int[N][A.size()];
        for(int i = 0; i < N; i++){
            B.add(new ArrayList<Integer>());
        }

        int exp = (int) Math.pow(10, digit);
        for (Integer i : A) {
            int k = Math.floorMod((i / exp), N);//key associated with A[i]

            B.get(k).add(i);
        }
        int j = 0;
        for(int k = 0; k < N; k++){
            for(Integer x : B.get(k)){
                A.set(j++, x);
            }
        }
        System.out.println("Sorted: " + A);
        return A;
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<Integer>();
        Collections.addAll(A, 1814, 232, 2888, 31, 1455, 2242, 4345, 1470, 515, 3632);
        System.out.println(A);
        A = bucketSort(A, 0);
        System.out.println(A);
    }
}

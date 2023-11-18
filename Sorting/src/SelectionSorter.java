import java.util.Arrays;

public class SelectionSorter {
    /*
     * 1. Start at the front of the array, find the smallest value
     * 2. swap places
     * 3. Go to the next index, find the smallest value in the rest of the array
     * 4. Repeat step 2 for all indexes in the array
     */
    static void SelectionSort(int[] A){
        for(int i = 0; i < A.length; i++){
            int k = i;
            for(int j = i + 1; j < A.length; j++){
                if(A[j] < A[k]){
                    k = j;
                }
            }
            if(i != k){
                int tmp = A[i];
                A[i] = A[k];
                A[k] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[] {9, 2, 6, 1, 7, 4, 8, 5, 3};

        System.out.println(Arrays.toString(A));
        SelectionSort(A);
        System.out.println(Arrays.toString(A));

    }
}

import java.util.Arrays;

public class InsertionSorter {
    /*
     * i = 1
     * 1. j = i
     * 2. Start at index i, check if the element to the left is larger
     * 3. if the left element is larger -> swap elements
     * 4. if j > 0 -> Decrement j, go to step 1
     * 5. if i < size-1 -> increment i, go to step 1
     *
     */
    static void InsertionSort(int[] A){
        for(int i = 1; i < A.length; i++){
            int j = i;
            while(j > 0 && A[j] < A[j-1]){
                int tmp = A[j];
                A[j] = A[j-1];
                A[j-1] = tmp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[] {9, 2, 6, 1, 7, 4, 8, 5, 3};

        System.out.println(Arrays.toString(A));
        InsertionSort(A);
        System.out.println(Arrays.toString(A));

    }
}

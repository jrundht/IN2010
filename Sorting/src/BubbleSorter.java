import java.util.Arrays;

public class BubbleSorter {
    /*
     * 1. Start at the front of the array
     * 2. If an element - j -  is larger than the one to the right - j + 1
     * 3. Swap them
     * 4. If j < size - i, go to step 1
     *
     * At first iteration the largest 'bubble' will be at the right position.
     * So for each iteration, the next last position is filled
     *
     * Imagine an element as a bubble, being brought through the array
     *
     */
    static void BubbleSort(int[] A){
        for(int i = 0; i < A.length - 1; i++){
            for(int j = 0; j < A.length - i - 1; j++){
                if(A[j] > A[j+1]){
                    int tmp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = tmp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] A = new int[] {9, 2, 6, 1, 7, 4, 8, 5, 3};

        System.out.println(Arrays.toString(A));
        BubbleSort(A);
        System.out.println(Arrays.toString(A));

    }
}

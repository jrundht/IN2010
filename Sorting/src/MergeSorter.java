import java.util.Arrays;

public class MergeSorter {
    /*
     * Split the array in two halves until you are left with
     * an array with one element, then merge back into original array
     */
    static int[] Merge(int[] A, int[] Aleft, int[] Aright){
        int i = 0;
        int j = 0;
        while(i < Aleft.length && j < Aright.length){
            if(Aleft[i] < Aright[j]){
                A[i + j] = Aleft[i];
                i++;
            }else{
                A[i + j] = Aright[j];
                j++;
            }
        }
        while(i < Aleft.length){
            A[i + j] = Aleft[i];
            i++;
        }
        while(j < Aright.length){
            A[i + j] = Aright[j];
            j++;
        }

        return A;
    }

    static int[] MergeSort(int[] A){
        if(A.length <= 1) return A;

        int i = (A.length)/2;
        int[] Aleft = MergeSort(Arrays.copyOfRange(A, 0, i));
        int[] Aright = MergeSort(Arrays.copyOfRange(A, i, A.length));

        return Merge(A, Aleft, Aright);
    }

    public static void main(String[] args) {
        int[] A = new int[] {9, 2, 6, 1, 7, 4, 8, 5, 3};

        System.out.println(Arrays.toString(A));
        A = MergeSort(A);
        System.out.println(Arrays.toString(A));

    }
}

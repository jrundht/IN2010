import java.util.ArrayList;
import java.util.Arrays;

public class QuickSorter {

    /*
     * Choosing pivot based on median of the three indexes
     */
    private static int choosePivot(int[]  A, int low, int high){
        int middle = A[(low + high)/2];
        int lowA = A[low];
        int highA = A[high];

        if((middle >= lowA && middle <= highA) || (middle >= highA && middle <= lowA)){
            return A.length/2;
        } else if ((lowA >= middle && lowA <= highA) || (lowA >= highA && lowA <= middle)) {
            return low;
        } else{
            return high;
        }
    }

    public static int partition(int[] A, int low, int high){
        int p = choosePivot(A, low, high);

        // set pivot on the right side of the array
        int tmp = A[p];
        A[p] = A[high];
        A[high] = tmp;

        int pivot = A[high];
        int left = low;
        int right = high - 1; //from right to left

        while(left <= right){
            while(left <= right && A[left] <= pivot){
                left++;
            }
            while(right >= left && A[right] >= pivot){
                right--;
            }
            if(left < right){
                tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
            }
        }
        tmp = A[left];
        A[left] =  A[high];
        A[high] = tmp;
        //System.out.println(Arrays.toString(A));
        return left;
    }

    public static void quickSort(int[] A, int low, int high){
        if(low >= high) return;

        int p = partition(A, low, high);
        quickSort(A, low, p - 1);
        quickSort(A, p + 1, high);

    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 5, 9, 3, 8, 6, 4};

        System.out.println("Original: " + Arrays.toString(A));
        quickSort(A, 0, A.length - 1);
        System.out.println("Sorted: " + Arrays.toString(A));
    }
}

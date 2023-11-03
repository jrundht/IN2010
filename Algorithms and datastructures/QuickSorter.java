import java.util.ArrayList;

public class QuickSorter {

    /*
     * Choosing pivot based on median of the three indexes
     */
    private static int choosePivot(ArrayList<Integer>  A, int low, int high){
        int pivot = A.get((low + high)/2);
        int lowA = A.get(low);
        int highA = A.get(high);

        if((pivot >= lowA && pivot <= highA) || (pivot >= highA && pivot <= lowA)){
            return A.size()/2;
        } else if ((lowA >= pivot && lowA <= highA) || (lowA >= highA && lowA <= pivot)) {
            return low;
        } else{
            return high;
        }
    }

    public static int partition(ArrayList<Integer> A, int low, int high){
        int p = choosePivot(A, low, high);
        //System.out.printf("Low: %d, High: %d, p: %d%n", low, high, A.get(p));
        int tmp = A.get(p);
        A.set(p, A.get(high));
        A.set(high, tmp);
        //System.out.println(A);

        int pivot = A.get(high);
        int left = low;
        int right = high - 1;

        while(left <= right){
            while(left <= right && A.get(left) <= pivot){
                left++;
            }
            while(right >= left && A.get(right) >= pivot){
                right--;
            }
            if(left < right){
                tmp = A.get(left);
                A.set(left, A.get(right));
                A.set(right, tmp);
            }
        }
        tmp = A.get(left);
        A.set(left, A.get(high));
        A.set(high, tmp);

        return left;
    }

    public static ArrayList<Integer> quickSort(ArrayList<Integer> A, int low, int high){
        if(low >= high) return A;

        int p = partition(A, low, high);
        quickSort(A, low, p - 1);
        quickSort(A, p + 1, high);
        System.out.println(p);
        return A;
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        A.add(55);
        A.add(29);
        A.add(21);
        A.add(52);
        A.add(2);
        A.add(25);
        A.add(37);
        System.out.println("Original: " + A);
        System.out.println("Sorted: " + quickSort(A, 0, A.size() - 1));
    }
}

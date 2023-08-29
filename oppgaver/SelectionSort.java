import java.util.Arrays;
class SelectionSort{
    private static int[] selectionSort(int[] a){
        for(int i = 0; i < a.length; i++){
            int k = i;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < a[k]){
                    k = j;
                }
            }
            if(i != k){
                int tmp = a[i];
                a[i] = a[k];
                a[k] = tmp;
            }
        }
        return a;
    }
    public static void main(String[] args){
        int[] array = {3, 4, 1, 6, 34, 2, 9, 6, 1, 73, 98, 12, 11, 62, 18, 37, 8};  
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(selectionSort(array)));
    }
}
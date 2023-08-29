import java.util.Arrays;
class Bubblesort{
    private static int[] bubbleSort(int[] array){
        for(int i = 0; i < array.length - 2; i++){
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j + 1]){
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
    public static void main(String[] args){
        int[] array = {3, 4, 1, 6, 34, 2, 9, 6, 1, 73, 98, 12, 11, 62, 18, 37, 8};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(bubbleSort(array)));
    }
}
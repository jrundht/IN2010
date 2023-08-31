import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class MergeSort{

    private static ArrayList<Integer> merge(List<Integer> arr1, List<Integer> arr2){
        ArrayList<Integer> arr = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < arr1.size() && j < arr2.size()){
            if(arr1.get(i) < arr2.get(j)){
                arr.add(arr1.get(i++));
            }else{
                arr.add(arr2.get(j++));
            }
        }
        while(i < arr1.size()){
            arr.add(arr1.get(i++));
        }
        while(j < arr2.size()){
            arr.add(arr2.get(j++));
        }

        return arr;
    }

    private static ArrayList<Integer> mergeSort(ArrayList<Integer> arr){
        if(arr.size() <= 1) return arr;

        int i = (arr.size())/2;
        List<Integer> arr1 = mergeSort(new ArrayList<>(arr.subList(0, i)));
        List<Integer> arr2 = mergeSort(new ArrayList<>(arr.subList(i, arr.size())));
        return merge(arr1, arr2);
    }

    private static ArrayList<Integer> createRandomList(int str){
        ArrayList<Integer> arr = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < str; i++){
            arr.add(rand.nextInt(100));
        }

        return arr;
    }

    public static void main(String[] args){
        ArrayList<Integer> arr = createRandomList(30);
        
        System.out.println("Original:\n   " + arr.toString());
        System.out.println("Merged:\n   " + mergeSort(arr).toString());
    }
}
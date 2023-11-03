import java.util.ArrayList;
import java.util.Random;

class HeapSorter{
    static ArrayList<Integer> heap = createRandomList(20);

    /*
     * Building a max heap
     * then move the large elements from the front of the heap to the back 
     */
    static void heapSort(ArrayList<Integer> heap){
        buildMaxHeap(heap, heap.size());
        for(int i = heap.size()-1 ; i >= 0; i--){
            int tmp = heap.get(i);
            heap.set(i, heap.get(0));
            heap.set(0, tmp);
            bubbleDown(heap, 0, i);
        }
    }


    static void buildMaxHeap(ArrayList<Integer> heap, int n){
        for(int i = heap.size()/2; i >= 0; i--){
            bubbleDown(heap, i, n);
        }
    }

    static void bubbleDown(ArrayList<Integer> heap, int i, int n){
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left < n && heap.get(largest) < heap.get(left)){
            largest = left;
        }
        if(right < n && heap.get(largest) < heap.get(right)){
            largest = right;
        }
        if(i != largest){
            int tmp = heap.get(i);
            heap.set(i, heap.get(largest));
            heap.set(largest, tmp);
            bubbleDown(heap, largest, n);
        }
    }

    static ArrayList<Integer> createRandomList(int str){
        ArrayList<Integer> arr = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < str; i++){
            arr.add(rand.nextInt(100));
        }

        return arr;
    }
    public static void main(String[] args){
        System.out.println(heap);
        heapSort(heap);
        System.out.println(heap);
    }
}
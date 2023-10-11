import java.util.ArrayList;
import java.util.Scanner;
public class BinaryHeap {

    // ArrayList<Integer> heap = new ArrayList<>();


    public static int parentOf(int i){
        return (i-1) / 2;
    }
    public static int leftOf(int i){
        return (i*2) + 1;
    }
    public static int rightOf(int i){
        return (i*2) + 2;
    }

    public static void insert(ArrayList<Integer> heap, int x){
        heap.add(x);
        int i = heap.size() - 1;
        while(i > 0 && heap.get(i) < heap.get(parentOf(i))){
            int tmp = heap.get(i);
            heap.set(i, heap.get(parentOf(i)));
            heap.set(parentOf(i), tmp);
            i = parentOf(i);
        }
    }

    public static int removeMin(ArrayList<Integer> heap){
        int x = heap.get(0);
        heap.set(0, heap.get(heap.size()-1)); //set last element as root
        heap.remove(heap.size()-1); //remove last element
        int i = 0;
        while(rightOf(i) < heap.size() - 1){
            int j = heap.get(leftOf(i)) <= heap.get(rightOf(i)) ? leftOf(i) : rightOf(i);

            if(heap.get(j) > heap.get(i)) break;
            int tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
            i = j;
        }
        if(leftOf(i) <= heap.size() - 1 && heap.get(leftOf(i)) <= heap.get(i)){
            int tmp = heap.get(i);
            heap.set(i, heap.get(leftOf(i)));
            heap.set(leftOf(i), tmp);
        }
        return x;
    }

    /*
     * run: seq 20 | java BinaryHeap.java
     * seq 20 creates an ordered array from 1-20
     */
    public static void main(String[] args){
        ArrayList<Integer> heap = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            if(scanner.nextLine().equals("n")) break;
            BinaryHeap.insert(heap, scanner.nextInt());
        }
        
        System.out.println(heap);
        BinaryHeap.removeMin(heap);
        System.out.println(heap);
        scanner.close();
    }

}

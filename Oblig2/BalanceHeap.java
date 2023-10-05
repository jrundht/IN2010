import java.util.PriorityQueue;
import java.util.Scanner;

public class BalanceHeap {
   /*
    * Receives a sorted priorityque, prints out the elements in the order
    * needed to make balanced tree (given the tree does not have a built in balancer)
    */
    public static void balancer(PriorityQueue<Integer> heap){
        if(heap.size() < 2){
            System.out.println(heap.poll());
            return;
        }
        if(heap.size() < 3){ //2 elements
            int tmp = heap.poll();
            System.out.println(heap.poll());
            System.out.println(tmp);
            return;
        }

        int x = heap.size()/2; //the element i need
        PriorityQueue<Integer> heap1 = new PriorityQueue<>();
        for(int i = 0; i < x; i++){
            heap1.offer(heap.poll());
        }
        
        System.out.println(heap.poll());
        PriorityQueue<Integer> heap2 = new PriorityQueue<>();
        for(int j = 0; j < x; j++){
            heap2.offer(heap.poll());
        }
        balancer(heap2);
        balancer(heap1);
    }

    /*
     * run: seq 20 | java BalanceHeap | java BalanceChecker
     */
    public static void main(String[] args){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        heap.offer(0);
        while(scanner.hasNext()){
            heap.offer(scanner.nextInt());
        }
        balancer(heap);
        scanner.close();
    }
}

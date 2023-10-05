import java.util.Scanner;
import java.util.ArrayList;

public class BalanceArray{
    public static void balancer(ArrayList<Integer> array){
        if(array.size() < 2){
            System.out.println(array.get(0));
            return;
        }
        if(array.size() < 3){
            System.out.println(array.get(1));
            System.out.println(array.get(0));
            return;
        }
        System.out.println(array.get((array.size() / 2)));
        balancer(new ArrayList<>(array.subList((array.size() / 2) + 1, array.size())));
        balancer(new ArrayList<>(array.subList(0, (array.size() / 2))));
    }
    /*
     * run: seq 20 | java BalanceArray | java BalanceChecker
     */
    public static void main(String[] args){
        ArrayList<Integer> sortedList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        sortedList.add(0);
        while(scanner.hasNext()){
            sortedList.add(scanner.nextInt());
        }
        balancer(sortedList);
        scanner.close();
    }
}

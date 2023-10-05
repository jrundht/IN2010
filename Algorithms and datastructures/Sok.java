
public class Sok {
    private static boolean check(int[] A, int x){
        for(int i = 0; i < A.length; i++){
            if(x == A[i]) return true;
        }
        return false;
    }
    
    public static int[] createOrderedArray(int length) {
        int[] array = new int[length];
        
        for (int i = 0; i < length; i++) {
            array[i] = (i + 1) * 2; // Multiplying by 2 to create an ordered array with even numbers
        }
        
        return array;
    }

    public static void main(String[] args){
        int[] A = createOrderedArray(10);
        int x = Integer.parseInt(args[0]);
        // int x = 3;

        System.out.println(check(A,x));
    }
}

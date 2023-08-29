
public class Sok {
    private static boolean check(int[] A, int x){
        for(int i = 0; i < A.length; i++){
            if(x == A[i]) return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        int[] A = new int[5];
        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        A[3] = 4;
        A[4] = 5;
        int x = Integer.parseInt(args[0]);
        // int x = 3;

        System.out.println(check(A,x));
    }
}

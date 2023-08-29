class Binsok{
    private static boolean check(int[] array, int x){
        int low = 0;
        int high = array.length - 1;
        while(low <= high){
            int i = (high + low) / 2;
            if(array[i] == x){
                return true;
            }else if(array[i] < x){
                low = i + 1;
            }else if(array[i] > x){
                high = i - 1;
            }
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
        long start = System.currentTimeMillis();
        int[] array = createOrderedArray(20000);
        int x = Integer.parseInt(args[0]);
        boolean res = check(array, x);
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        System.out.println(res);
        System.out.println("The search took: " + elapsed);
    }
}
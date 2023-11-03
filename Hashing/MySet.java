import java.util.Objects;

public class MySet{
    private final double LOAD_FACTOR_THRESHHOLD = 0.75;
    private int n = 0; // number of elements
    private int N = 1; // current capacity
    private int[] set;

    MySet(){
        this.set = new int[N];
    }

    MySet(int N){
        if(N > 0) this.N = N;
        set = new int[this.N];
    }

    private double loadFactor(){ return (double) n/N; }

    private void ensureCapacity(){
        if(loadFactor() >= LOAD_FACTOR_THRESHHOLD){
            reHash();
        }
    }

    private void reHash() {
        int[] oldSet = set;
        n = 0;
        N = N * 2;
        set = new int[N];

        for(int i : oldSet){
            if(i != 0 && i != -1){
                insert(i);
            }
        }
    }

    private int hash(int value){
        return Math.floorMod(Objects.hashCode(value), N);
    }

    public int size(){
        return n;
    }

    public void insert(int value){
        int i  = hash(value);
        
        while(set[i] != 0){
            
            int vi = set[i];
            if(value == vi) return;
            i = (i + 1) % N;
        }
        n++;
        set[i] = value;
        ensureCapacity();
    }

    public boolean contains(int value){ 
        int i  = hash(value);
        while(set[i] != 0){
            int vi = set[i];
            if(value == vi) return true;
            i = (i + 1) % N;
        }
        return false;

    }

    public void remove(int value){
        int i = hash(value);
        while(set[i] != 0){
            int vi = set[i];
            if(value == vi){
                set[i] = -1;
                n--;
                return;
            }
            i = (i + 1) % N;
        }
    }
}

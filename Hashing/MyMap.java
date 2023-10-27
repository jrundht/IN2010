import java.util.*;

/*
 * Står i hashing notatet at vi antar at klassene som arver fra MyMap implementerer en iteerator
 * Skjønner ikke hvor/hvorfor??
 */
abstract class MyMap<K, V>{
    protected double LOAD_FACTOR_THRESHHOLD = 0.75;
    protected int n = 0; // number of pairs
    protected int N = 1; // current capacity

    public double loadFactor(){ return (double) n/N; }
    protected abstract void reHash();
    public void ensureCapacity(){
        if(loadFactor() >= LOAD_FACTOR_THRESHHOLD){
            reHash();
        }
    }
    public int hash(K key){
        return Objects.hashCode(key) % N;
        // return Math.floorMod(Objects.hashCode(key), N);
    }
    public int size(){
        return n;
    }

}

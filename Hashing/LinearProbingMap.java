import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Returns elements in order of insertion
 */
public class LinearProbingMap<K, V> extends MyMap<K, V> implements Iterable<Node<K, V>>{
    @SuppressWarnings("unchecked")
    Node<K, V>[] A = (Node<K, V>[]) new Node[N];
    
    LinearProbingMap(){}

    //Setting start size is optional
    LinearProbingMap(int N){
        if(N == 0) N = 1;
        this.N = N;
        @SuppressWarnings("unchecked")
        Node<K, V>[] newA = (Node<K, V>[]) new Node[N];
        A = newA;
    }

    @Override
    protected void reHash() {
        Node<K, V>[] kvs = A;
        n = 0;
        N = N * 2;

        @SuppressWarnings("unchecked")
        Node<K, V>[] newA = (Node<K, V>[]) new Node[N];
        A = newA;

        for(int i = 0; i < kvs.length; i++){
            if(kvs[i] != null){
                put(kvs[i].getKey(), kvs[i].getValue());
            }
        }
    }

    /*LinearProbingInsert*/
    public void put(K key, V value){
        int i = hash(key);
        
        while(A[i] != null){
            K ki = A[i].getKey();
            if(key.equals(ki)){
                A[i] = new Node<>(key, value);
                return;
            }
            i = (i +1) % N; 
        }
        n++;
        A[i] = new Node<>(key, value);

        ensureCapacity();
    }
    
    /*LinearProbingGet*/
    public V get(K key){
        int i = hash(key);
        while(A[i] != null){
            K ki = A[i].getKey();  
            V vi = A[i].getValue();
            if(key.equals(ki)){
                return vi;
            }
            i = (i +1) % N; 
        }
        return null;
    }
    
    public Node<K, V> getElement(K key){
        int i = hash(key);
        while(A[i] != null){
            K ki = A[i].getKey();  
            if(key.equals(ki)){
                return A[i];
            }
            i = (i +1) % N; 
        }
        return null;
    }
    
    /*LinearProbingRemove*/
    public void remove(K key){
        int i = hash(key);
        while(A[i] != null){
            K ki = A[i].getKey();  
            if(key.equals(ki)){
                n--;
                A[i] = null;
                fillHole(i);
                return;
            }
            i = (i +1) % N; 
        }
    }

    private void fillHole(int i){
        int s = 1;
        while(A[(i + s) % N] != null){
            Node<K, V> node = A[(i + s) % N];
            K k = node.getKey();
            int j = hash(k);
            int res = Math.floorMod(j - i , N);
            if(!(res > 0 && res <= s)){
                A[i] = node;
                A[(i + s) % N] = null;
                fillHole(i + 1);
                return; 
            }
            s++;
        }
    }
    public boolean contains(int value){
        for(Node<K, V> node : this){
            if((int)node.getValue() == value) return true;
        }
        return false;
    }

    /*LinearProbingIter*/
    public Iterator<Node<K, V>> iterator(){
        return new LinearProbingMapIterator();
    }

    private class LinearProbingMapIterator implements Iterator<Node<K, V>>{
        private int i = 0;
        private Node<K, V> currNode;

        LinearProbingMapIterator(){
            goToNextNonEmptyBucket();
        }
        private void goToNextNonEmptyBucket(){
            while(i < N && A[i] == null){
                i++;
            }
            if(i < N){
                currNode = A[i];
            } else {
                currNode = null;
            }
        }
        @Override
        public boolean hasNext() {
            return currNode != null;
        }
        @Override
        public Node<K, V> next() {
            if(!hasNext()) throw new NoSuchElementException();
            Node<K, V> prevNode = currNode;
            i++;
            goToNextNonEmptyBucket();
            return prevNode;
        }
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        // for(Node<K, V> node : this){
        //     if(node != null){
        //         str.append(node + ", ");
        //     }else{
        //         str.append("NULL, ");
        //     }
        // }
        for(int i = 0; i < A.length; i++){
            if(A[i] == null){ 
                str.append("NULL, ");
            } else{
                str.append(A[i] + ", ");
            }
            
        }
        if (str.length() > 0) {
            str.delete(str.length() - 2, str.length());
        }

        return "{" + str.toString() + "}";
    }
}

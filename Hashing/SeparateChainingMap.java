import java.util.*;

public class SeparateChainingMap<K, V> extends MyMap<K, V> implements Iterable<Node<K, V>>{
    @SuppressWarnings("unchecked")
    private List<Node<K, V>>[] A = (List<Node<K, V>>[]) new List[N];
    
    SeparateChainingMap(){}

    //Setting start size is optional
    SeparateChainingMap(int N){
        this.N = N;
        @SuppressWarnings("unchecked")
        List<Node<K, V>>[] newA = (List<Node<K, V>>[]) new List[N];
        A = newA;
    }

    @Override
    protected void reHash(){
        List<Node<K, V>>[] kvs = A; //key+value
        n = 0;
        N = N * 2;
        @SuppressWarnings("unchecked")
        List<Node<K, V>>[] newA = (List<Node<K, V>>[]) new List[N];
        A = newA;
        
        /*
         * put all nodes into the new, bigger array
         */
        for(List<Node<K, V>> kv : kvs){ 
            if(kv != null){
                for(Node<K, V> node : kv){
                    put(node.getKey(), node.getValue());
                }
            }
        }
        
    }
    
    /*separateChainingInsert*/
    public void put(K key, V value){
        ensureCapacity();

        int i = hash(key);

        if(A[i] == null){
            A[i] = new LinkedList<>();
        }
        
        for(Node<K, V> node : A[i]){
            if(node != null && node.getKey().equals(key)){
                node.setValue(value);
                return;
            }
        }

        A[i].add(new Node<>(key, value));
        n++;
    }

    
    /*separateChainingGet*/
    public V get(K key){
        int i = hash(key);
        if(A[i] == null){
            return null;
        }
        for(Node<K, V> node : A[i]){
            if(key == node.getKey()) return node.getValue();
        }
        return null;
    }
    
    /*separateChainingDelete*/
    public void remove(K key){
        // int i = hash(key);
        // if(A[i] == null) return;

        // // using lambda expression, node is the parameter representing element of linkedlist
        // if(A[i] != null) A[i].removeIf(node -> key.equals(node.getKey())); 
        // n--;
        //With the iterator
        Iterator<Node<K, V>> iterator = iterator();
        while(iterator.hasNext()){
            Node<K, V> node = iterator.next();
            if(node.getKey().equals(key)){
                iterator.remove();
                n--;
                return;
            }
        }
    }
    
    /*separateChainingIter*/
    public Iterator<Node<K, V>> iterator(){
        return new SeparateChainingMapIterator();
    }

    private class SeparateChainingMapIterator implements Iterator<Node<K, V>>{
        private int i = 0;
        private Node<K, V> currNode = null;
        private Iterator<Node<K, V>> lIterator;

        SeparateChainingMapIterator(){
            goToNextNonEmptyBucket();
        }

        // find the next bucket in A which is not empty, and set the lIterator to iterate over this
        private void goToNextNonEmptyBucket(){ 
            while(i < N && (A[i] == null || A[i].isEmpty())){
                i++;
            }
            if(i < N && A[i] != null){
                lIterator = A[i].iterator(); 
            }
        }

        @Override
        public boolean hasNext() {
            if(lIterator != null && lIterator.hasNext()){
                return true;
            }
            // return false;
            return i + 1 < N;
        }

        @Override
        public Node<K, V> next() {
            if(!hasNext())throw new NoSuchElementException();

            if(lIterator != null && lIterator.hasNext()){
                currNode = lIterator.next();
                return currNode;
            } else{
                i++;
                goToNextNonEmptyBucket();
                return next();
            }
        }

        public void remove(){
            lIterator.remove();
        }
        
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < A.length; i++){
            if(A[i] == null){ 
                str.append("NULL, ");
                continue;
            }
            for(Node<K, V> node : A[i]){
                if(node != null) str.append(node + ", ");
            }
        }

        //remove last ', '
        if (str.length() > 0) {
            str.delete(str.length() - 2, str.length());
        }
        return "{" + str.toString() + "}";
    }
}

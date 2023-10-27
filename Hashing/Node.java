import java.util.*;
import java.util.Map.*;
    /*
     * Same as built in Node class in Hashmaps 
     * (from hashmap sourcecode: 
     * https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/HashMap.java)
     */
    class Node<K, V> implements Entry<K, V>{
        private final K key;
        private V value;
        private Node<K, V> next = null; // for separate chaining

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final K getKey(){ return key; }

        public V getValue(){ return value; }

        public V setValue(V newV){
            V oldV = value;
            value = newV;
            return oldV;
        }

        public void setNext(Node<K, V> node){ next = node; }

        public Node<K, V> next(){ return next; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            // @SuppressWarnings("unchecked")
            Node<?, ?> node = (Node<?, ?>) obj;
            return obj instanceof Entry<?, ?> 
                    && Objects.equals(key, node.getKey()) 
                    && Objects.equals(value, node.getValue());
        }

        @Override
        public int hashCode() { return Objects.hash(key, value); }

        @Override
        public String toString(){ return key + " -> " + value; }
    }
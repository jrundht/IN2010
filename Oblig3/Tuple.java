import java.util.*;

public class Tuple<E extends Comparable<E>, T> implements Comparable<Tuple<E, T>>{
    E element1;
    T element2;

    public Tuple(E element1, T element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        @SuppressWarnings("unchecked")
        Tuple<E, T> pair = (Tuple<E, T>) obj;
        return element1.equals(pair.element1) && element2.equals(pair.element2);
    }

    @Override
    //Gives an id for iterating in a HashMap
    public int hashCode() {
        return Objects.hash(element1, element2);
    }

    @Override
    public String toString(){
        return "(" + element1 + ", " + element2 + ")";
    }

    @Override
    public int compareTo(Tuple<E, T> o) {
        return element1.compareTo(o.element1);
    }

}
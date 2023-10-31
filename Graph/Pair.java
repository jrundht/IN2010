import java.util.*;

public class Pair<E, T> {
    E first;
    T second;

    public Pair(E first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        @SuppressWarnings("unchecked")
        Pair<E, T> pair = (Pair<E, T>) obj;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    //Gives an id for iterating in a HashMap
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString(){
        return "(" + first + ", " + second + ")";
    }
}
package hu.ptomi.lists;

import java.util.*;

/**
 * Backed by an array, use indexes, thread-safe.
 * <p>
 * Use Case: this class is deprecated, use arraylist instead.
 */
public class VectorExamples {
    public static void main(String[] args) {
        // Old fashioned way.
        Vector<String> names = new Vector<>(Arrays.asList("John", "Anton", "Tamas"));
        names.iterator();

        // New way, it has a better performance than Vector.
        List<String> otherNames = Collections.synchronizedList(new ArrayList<>(Arrays.asList("John", "Anton", "Tamas")));
    }
}

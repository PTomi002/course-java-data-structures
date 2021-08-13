package hu.ptomi.lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Backed by an array, use indexes, snapshot iterator, thread-safe.
 * <p>
 * Use Case: thread-safe list when iterations occur much more than mutations.
 */
public class COWIterationExamples {
    public static void main(String[] args) {
        List<String> names = new CopyOnWriteArrayList<>(Arrays.asList("John", "Anton", "Tamas"));
        names.sort(null);

        for (Iterator<String> it = names.iterator(); it.hasNext(); ) {
            String name = it.next();
            if (name.contains("o")) { // Debug point in this line.
                names.remove(name);
            }
        } // Debug point in this line.
        // Watch the names and the iterator as View as Object, see the array hashcode and the iterator snapshot hashcode (it points to the original array)!
        System.out.println(names);
    }
}

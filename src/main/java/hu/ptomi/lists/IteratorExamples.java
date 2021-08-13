package hu.ptomi.lists;

import java.util.*;

public class IteratorExamples {
    public static void main(String[] args) {
        Vector<String> names = new Vector<>(Arrays.asList("John", "Anton", "Tamas"));
        Enumeration<String> en = names.elements();
        // Old fashioned way.
        while (en.hasMoreElements()) {
            String name = en.nextElement();
            if (name.contains("o")) { // John is removed, but Anton takes John's place in the array, so Anton is skipped.
                names.remove(name);
            }
        }
        System.out.println(names);

        ArrayList<String> otherNames = new ArrayList<>(Arrays.asList("John", "Anton", "Tamas"));
        Iterator<String> it = otherNames.iterator();
        // New way.
        while (it.hasNext()) {
            String name = it.next();
            if (name.contains("o")) {
                // Causes concurrent modification exception, iterator if Fail-Fast.
//                otherNames.remove(name);
                it.remove();
            }
        }
        // Use Streams, or forEach instead of iterator.
        // Book: Erich Gamma et al - Design Patterns
        System.out.println(otherNames);
    }
}

package hu.ptomi;

import java.util.List;

/**
 * Ordered
 * RandomAccess = O(1) with indexes.
 */
public class ListExamples {
    public static void main(String[] args) {
//        List<String> abc = Arrays.asList("Aa", "Bb", "Cc"); // Mutable list.
        List<String> abc = List.of("Aa", "Bb", "Cc"); // Immutable list.
        System.out.println(abc);

        // Throws unsupported operation ex.
        abc.set(1, "Dd");
        System.out.println(abc);
        abc.add("Ee");
        abc.clear();
    }
}

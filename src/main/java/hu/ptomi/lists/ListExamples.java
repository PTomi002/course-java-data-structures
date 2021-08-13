package hu.ptomi.lists;

import java.util.List;

/**
 * Ordered, can contain duplicates or even nulls, primitives are boxed values.
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

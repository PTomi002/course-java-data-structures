package hu.ptomi.sets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Unique values = no duplicates, primitives are boxed values.
 */
public class SetExamples {
    public static void main(String[] args) {
        // Throws duplicate exception.
//        Set<Integer> numbers = Set.of(1, 2, 3, 3, 3);

        Set<Integer> oddNumbers = new HashSet<>();
        oddNumbers.add(1);
        oddNumbers.add(0); // Just for intersection.
        oddNumbers.add(3);
        oddNumbers.add(5);
        oddNumbers.add(3);
        oddNumbers.add(1);
        System.out.println("odd set= " + oddNumbers);

        Set<Integer> evenNumbers = new HashSet<>();
        evenNumbers.add(0);
        evenNumbers.add(2);
        evenNumbers.add(6);
        evenNumbers.add(4);
        evenNumbers.add(0);
        System.out.println("even set= " + evenNumbers);

        Set<Integer> union = new HashSet<>(oddNumbers);
        union.addAll(evenNumbers);
        System.out.println("union is: " + union);

        Set<Integer> intersection = new HashSet<>(oddNumbers);
        intersection.retainAll(evenNumbers);
        System.out.println("intersection is: " + intersection);
    }
}

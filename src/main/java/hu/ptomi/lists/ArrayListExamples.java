package hu.ptomi.lists;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Backed by an array.
 */
public class ArrayListExamples {
    public static void main(String[] args) {
        ArrayList<String> seasons = new ArrayList<>(4);
        seasons.add("Winter");
        seasons.add("Spring");
        seasons.add("Summer");
        seasons.add("Autumn");

        System.out.println(seasons.indexOf("Summer"));
        seasons.remove("Summer");
        // Check in Debug mode View as -> Object
        System.out.println(seasons.indexOf("Summer"));

        for (int i = 0; i < 10_000; i++) {
            // Internally grows the array with int multiplication (current with 50% = 1.5)
            seasons.add("Pretty Warm");
        }

        // Debug the size.
        System.out.println();

        seasons.removeIf(season -> Objects.equals(season, "Pretty Warm"));

        // Debug the size, internal array keeps its size!
        System.out.println();
        // Automatically not shrinks, do not use this method much.
        seasons.trimToSize();

        // Creating a new arraylist is cheaper than cleaning it ion almost all cases
        //      because Java GC is designed to create the new objects and throw them away in a short time.
        seasons.clear();
    }
}

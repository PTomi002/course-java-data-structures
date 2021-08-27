package hu.ptomi.sets.ths;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * No duplicates, snapshot iterator, thread-safe, keeps insertion order (backed by CopyOnWriteArrayList).
 * <p>
 * Use Case: thread-safe set when iterations occur much more than mutations.
 * <p>
 * TreeSet identity is depends on the compareTo(...), while this class depends on equals(...)!
 * <p>
 * Use Case: Not advised to use, but can be a lifesaver, see our hand-copied example.
 */
public class COWArraySetExamples {
    public static void main(String[] args) {
        Set<Integer> oddBetweenMinusTenAndTen = new CopyOnWriteArraySet<>(Arrays.asList(
                -9, 7, -1, 1, 3, -5, -3, 9, 5, 5, 5, -5, -7
        ));
        System.out.println("set = " + oddBetweenMinusTenAndTen);

        // Let's see the constructor of it, addAllAbsent(...) ads the element if not already present in the collection.
        for (int i = 1_000; i <= 128_000; i *= 2) {
            List<Integer> testData = IntStream.range(0, i).boxed().collect(Collectors.toList());
            long start = System.currentTimeMillis();
            Set<Integer> sut = new CopyOnWriteArraySet<>(testData); // Seems to me quadratic complexity, as the time is 4 x, as we double the size of the input.
            System.out.println("size = " + i + "time = " + (System.currentTimeMillis() - start));
        }
    }
}

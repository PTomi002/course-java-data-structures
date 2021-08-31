package hu.ptomi.hashing;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * No duplicates, backed by a hash table, identity based on equals(...).
 * <p>
 * O(1) for get/check an element, worst case O(logn).
 */
public class HashSetExamples {
    public static void main(String[] args) {
        List<Integer> toShuffle = ThreadLocalRandom.current().ints(100).boxed().collect(Collectors.toList()); // Set is not ordered!
//        List<Integer> toShuffle = IntStream.range(0, 100).boxed().collect(Collectors.toList()); // Numbers are so small they take place in the sequential buckets, as their hashCode is the number itself.
        Collections.shuffle(toShuffle);
        System.out.println("Shuffled: " + toShuffle);

        Set<Integer> numbers = new HashSet<>(toShuffle);
        System.out.println("Set: " + numbers);

        // Let's see the collision problem again.
        Set<Integer> uniqueHashCodes = new HashSet<>();
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 768; j++) {
                uniqueHashCodes.add(new Pixel(i, j).hashCode()); // All item will be added because hashes will clash but equals shows the VM they are not the same objects.
            }
        }
        System.out.println("Max: " + 1024 * 768);
        System.out.println("Size: " + uniqueHashCodes.size());

        // For thread safety.
        ConcurrentHashMap.KeySetView<Object, Boolean> thSafe = ConcurrentHashMap.newKeySet();
        Collections.synchronizedSet(new HashSet<>());
    }
}

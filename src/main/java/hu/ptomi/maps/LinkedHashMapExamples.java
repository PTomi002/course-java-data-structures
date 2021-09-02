package hu.ptomi.maps;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Same as HashMap, but maintains insertion order (not comparator order!).
 * <p>
 * Use Case: LRU cache impl.
 */
public class LinkedHashMapExamples {
    public static void main(String[] args) {
        Set<Integer> numbers = new LinkedHashSet<>(); // Maintains only insertion-order.
        numbers.add(5);
        numbers.add(-122334);
        numbers.add(523);
        numbers.add(0);
        numbers.add(12);
        System.out.println("numbers= " + numbers);
        numbers.remove(523);
        System.out.println("numbers= " + numbers);
        numbers.add(523);
        System.out.println("numbers= " + numbers);

        Map<Integer, Integer> squares = new LinkedHashMap<>(16, 0.75f, true); // Maintains insertion and access order.
        squares.put(1, 1);
        squares.put(2, 4);
        squares.put(3, 9);
        squares.put(4, 16);
        squares.put(5, 25);
        System.out.println("squares=" + squares);
        squares.get(3);
        squares.get(1);
        System.out.println("squares=" + squares);
        squares.put(10, 100);

        // LRU example: removing the eldest entry with custom code
        Map<Integer, Integer> lruCache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > 3;
            }
        };
        lruCache.put(1, 1);
        lruCache.put(2, 4);
        lruCache.put(3, 9);
        lruCache.put(4, 16);
        lruCache.get(2);
        lruCache.put(5, 25);
        System.out.println("lruCache=" + lruCache);
    }
}

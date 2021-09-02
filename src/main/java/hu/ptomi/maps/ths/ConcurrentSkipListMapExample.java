package hu.ptomi.maps.ths;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.IntStream;

/**
 * This results in a typical O(logn) performance, same as the TreeMap, but uses more memory!
 * <p>
 * No duplicates, thread-safe by non-blocking swapping mechanisms and ordered, sorted (needs Comparable objects).
 */
public class ConcurrentSkipListMapExample {
    public static void main(String[] args) {
        // try out some concurrency
//        Map<Integer, String> map = new TreeMap<>(); // Goes wrong with NPE.
//        Map<Integer, String> map = new HashMap<>(); // Goes wrong with lost elements.
        Map<Integer, String> map = new ConcurrentSkipListMap<>(); // It is sorted, not an illusion like in the case of ConcurrentHashMap.
        IntStream.range(0, 10_000).parallel().boxed().forEach(i -> map.put(i, Integer.toBinaryString(i)));
        System.out.println("map size = " + map.size());
        System.out.println("map = " + map);
    }
}

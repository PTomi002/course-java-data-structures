package hu.ptomi.sets.ths;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Linked List =    e.g.: to get to the middle of 1_000_000 entries, you have to do 500_000 traversal to get to the middle of it.
 * Skip List =      beside having pointers to from nodes to nodes, we have pointers to the middle of hte list,
 * and others e.g.: pointers for the quarters.
 * <p>
 * This results in a typical O(logn) performance, same as the TreeSet, but uses more memory!
 * No duplicates, thread-safe by non-blocking swapping mechanisms and ordered, sorted (needs Comparable objects).
 * <p>
 * Be aware this class is not widely used by JDK itself + it is very complicated -> good receipt for a bugs
 */
public class ConcurrentSkipListSetExample {
    public static void main(String[] args) {
        Set<Integer> skipSet = new ConcurrentSkipListSet<>(Collections.reverseOrder());
        ThreadLocalRandom.current().ints(100).forEach(skipSet::add);

        System.out.println("set size = " + skipSet.size());
        System.out.println("set = " + skipSet);
    }
}

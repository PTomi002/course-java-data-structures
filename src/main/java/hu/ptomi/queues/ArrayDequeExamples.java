package hu.ptomi.queues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class is basically a circular array list.
 * <p>
 * Use Case: Like a Linked List without RandomAccess. If you don't need RandomAccess capabilities this class is very well written.
 */
public class ArrayDequeExamples {
    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();

        ad.add(11);
        ad.add(12);
        ad.add(-5);

        System.out.println(ad.poll()); // O(1)
        System.out.println(ad.peek()); // O(1)
        System.out.println(ad.remove());

        System.out.println("ad: " + ad);

        for (int i = 0; i < 10_000; i++) {
            ad.add(i);
        }

        System.out.println("ad size: " + ad.size());
        ad.clear(); // Nobody uses this method, let GC do its job and forget this object.

        System.out.println(); // View As -> Object and check internal array size, this class does not shrink either.
    }
}

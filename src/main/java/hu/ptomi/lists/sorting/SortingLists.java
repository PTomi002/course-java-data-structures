package hu.ptomi.lists.sorting;

import hu.ptomi.lists.model.Student;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class SortingLists {

    public static void main(String[] args) {
        // String: natural ordering = alphabetic order
        // natural ordering = objects must implement comparable, primitives are OK!
        List<String> names = Arrays.asList("John", "Anton", "Tamas");
        names.sort(null);
        System.out.println(names);

        // Defining natural ordering for Student class.
        List<Student> students = Arrays.asList(
                new Student(1, "John", 0.0),
                new Student(2, "John", 0.0),
                new Student(3, "Heinz", 0.0),
                new Student(3, "Anton", 0.0)
        );
        students.sort(null);
        System.out.println(students);
        // Other way sorting this.
        // As year and name is a private field, now it works as it is an inner class, but we move it out and extend our class with getters.
        students.sort((o1, o2) -> {
            int comp = Integer.compare(o2.getYear(), o1.getYear());
            if (comp == 0) return o2.getName().compareTo(o1.getName());
            else return comp;
        });
        System.out.println(students);
        students.sort(
                Comparator.comparing(Student::getYear)
                        .reversed()
                        .thenComparing(Student::getName)
        );
        System.out.println(students);

        // Sorting performance: O(n*logn) = quasilinear
        // Compare with -Djava.util.Arrays.useLegacyMergeSort=true.
        /**
         * TimSort = it can happen that in an unsorted list it is slower, but on a sorted list it is quicker
         * type=java.util.ArrayList time= 4946 ms
         * type=java.util.LinkedList time= 4896 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 4706 ms
         * Already sorted.
         * type=java.util.ArrayList time= 143 ms
         * type=java.util.LinkedList time= 395 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 169 ms
         * type=java.util.ArrayList time= 5399 ms
         * type=java.util.LinkedList time= 4795 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 4528 ms
         * Already sorted.
         * type=java.util.ArrayList time= 136 ms
         * type=java.util.LinkedList time= 349 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 167 ms
         *
         * Legacy Merge Sort  = may be quicker on an unsorted list
         * type=java.util.ArrayList time= 5104 ms
         * type=java.util.LinkedList time= 4403 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 4057 ms
         * Already sorted.
         * type=java.util.ArrayList time= 458 ms
         * type=java.util.LinkedList time= 696 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 499 ms
         * type=java.util.ArrayList time= 4333 ms
         * type=java.util.LinkedList time= 4315 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 4227 ms
         * Already sorted.
         * type=java.util.ArrayList time= 464 ms
         * type=java.util.LinkedList time= 686 ms
         * type=java.util.concurrent.CopyOnWriteArrayList time= 484 ms
         */
        List<Integer> numbers = fillWithRandomNumbers();
        for (int i = 0; i < 10; i++) {
            List<Integer> numArrayList = new ArrayList<>(numbers);
            testSortingPerformance(numArrayList);
            List<Integer> numLinkedList = new LinkedList<>(numbers);
            testSortingPerformance(numLinkedList);
            List<Integer> numCowList = new CopyOnWriteArrayList<>(numbers);
            testSortingPerformance(numCowList);
            // Test parallel sorting array list.
            List<Integer> numPArSortAl = new ParallelSortingArrayList<>(numbers);
            testSortingPerformance(numPArSortAl);

            System.out.println("Already sorted.");
            testSortingPerformance(numArrayList);
            testSortingPerformance(numLinkedList);
            testSortingPerformance(numCowList);
            testSortingPerformance(numPArSortAl);
        }
    }

    private static <E> void testSortingPerformance(List<E> unsorted) {
        long start = System.currentTimeMillis();
        unsorted.sort(null); // Uses TimSort, which is a better merge sort, lets compare them.
        System.out.println("type=" + unsorted.getClass().getName() + " time= " + (System.currentTimeMillis() - start) + " ms");
    }

    private static List<Integer> fillWithRandomNumbers() {
        return ThreadLocalRandom.current()
                .ints(10_000_000)
                .parallel()
                .boxed()
                .collect(Collectors.toList());
    }
}

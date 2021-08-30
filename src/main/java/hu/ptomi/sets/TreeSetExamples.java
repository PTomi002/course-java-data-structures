package hu.ptomi.sets;

import hu.ptomi.Student;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Backed by a red-black tree, sorted (needs Comparable objects), no duplicates.
 * Every operation is O(log n).
 */
public class TreeSetExamples {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // TreeSet requires the references to point to a comparable objects, compiler does not warn about it.
        Set<Integer> oddBetweenMinusTenAndTen = new TreeSet<>(Arrays.asList(
                -9, 7, -1, 1, 3, -5, -3, 9, 5, 5, 5, -5, -7
        ));
        System.out.println(oddBetweenMinusTenAndTen);

        // This would cause an exception.
//        Set<Object> mixed = new TreeSet<>();
//        mixed.add("Hello");
//        mixed.add(12);

        /*
        With a sorted binary tree (BST) adding:
        0
         \
          1
        0
         \
          1
           \
            2
         0
          \
           1
            \
             2
              \
               3

       Example:
       5,11,2,7,8,3
          5
       /     \
      2       11
       \     /
        3    8
            /
           7
        Problem: this can cause a very deep tree, where lookup is a very costly operation O(n).
        */

        /*
        red-black tree is a self-balancing tree, keeps the layers.
        From rules and definitions (Wiki): Every Red Black Tree with n nodes has maximum height <= 2Log2(n+1)
        Bármely logaritmus visszavezethető egy tetszőleges másik alapra: log a (b) = log c (b) / log c (a)
        */
        Set<Integer> million = IntStream.range(0, 1_000_000).boxed().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(2.0 * (Math.log(million.size() + 1) / Math.log(2)));

        // Let's check it.
        final Object dummy = new Object();
        Field parentField = null;
        Map<Integer, Object> millionMap = new TreeMap<>();
        LongAccumulator maxDepth = new LongAccumulator(Long::max, 0);

        // Adding randomization will result in a better balanced tree, then come with sorted numbers.
        List<Integer> addRandomization = IntStream.range(0, 1_000_000).boxed().collect(Collectors.toList());
        // Comment this to check randomized and sequential numbers TreeSet performance and depth.
        //        Collections.shuffle(addRandomization);
        //        Collections.reverse(addRandomization);
        addRandomization.forEach(i -> millionMap.put(i, dummy));

        // Loop through all the numbers, check max height from the bottom to the top via parent ref.
        for (Map.Entry<Integer, Object> entry : millionMap.entrySet()) {
            if (parentField == null) {
                parentField = entry.getClass().getDeclaredField("parent");
                parentField.setAccessible(true);
            }
            Map.Entry<Integer, Object> node = entry;
            int depth = 0; // Root is level 0.
            while (node != null) {
                depth++;
                node = (Map.Entry<Integer, Object>) parentField.get(node);
            }
            maxDepth.accumulate(depth);
        }
        System.out.println(maxDepth);

        // Check students, the good student won't be added, comes from the definition of the set add.
        Student wrong = new Student(1, "Peter", 30.0);
        Student good = new Student(1, "Peter", 90.0);

        Set<Student> students = new TreeSet<>(Arrays.asList(wrong, good));
        System.out.println(students);
    }
}

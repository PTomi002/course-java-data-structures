package hu.ptomi.maps;

import hu.ptomi.Student;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Backed by a red-black tree, sorted (needs Comparable objects), no duplicates.
 * <p>
 * Every operation is O(log n).
 * <p>
 * Needs compareTo(...), equals(...) and hashCode(...). Equals and hash code comes together.
 */
public class TreeMapExamples {

    public static final Comparator<Student> NATURAL_ORDER =
            Comparator.comparing(Student::getName)
                    .thenComparingInt(Student::getYear)
                    .thenComparingDouble(Student::getAverage)
                    .reversed();

    public static void main(String[] args) {
        // equals, hashCode and comparable is needed!
        Map<Student, String> students = new TreeMap<>(NATURAL_ORDER);

        students.put(new Student(1991, "Tamás", 0.5), "Good student.");
        students.put(new Student(1994, "Kimberly", 1.5), "Other good student.");
        students.put(new Student(1991, "Tamás", -0.5), "Good student.");

        System.out.println(students);

        // good interview question if the following:
        Map<String, Integer> map = new TreeMap<>(Comparator.comparing(String::length));
//        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("sixteen", 16);
        map.put("fifteen", 15);
        System.out.println(map); // {one=2, sixteen=15} key remains, value replaced
    }
}

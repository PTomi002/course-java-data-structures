package hu.ptomi.maps;

import hu.ptomi.Student;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
    }
}

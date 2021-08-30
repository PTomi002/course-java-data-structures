package hu.ptomi;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private final int year;
    private final String name;
    // Extend this class during the TreeSet course.
    private final Double average;

    public Student(int year, String name, Double average) {
        this.year = year;
        this.name = name;
        this.average = average;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public Double getAverage() {
        return average;
    }

    // Added during hash set examples.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return year == student.year && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, name);
    }
    // END OF: Added during hash set examples.

    @Override
    public int compareTo(Student o) {
        // Wrong approach as you can overflow.
//            int year = this.year - o.year;
        int comp = Integer.compare(year, o.year);
        if (comp == 0) return name.compareTo(o.name);
        else return comp;
    }

    @Override
    public String toString() {
        return "Student{" +
                "year=" + year +
                ", name='" + name + '\'' +
                ", average=" + average +
                '}';
    }
}

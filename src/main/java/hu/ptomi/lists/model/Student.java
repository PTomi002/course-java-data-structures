package hu.ptomi.lists.model;

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

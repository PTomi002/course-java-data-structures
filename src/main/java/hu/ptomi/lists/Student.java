package hu.ptomi.lists;

public class Student implements Comparable<Student> {
    private int year;
    private String name;

    public Student(int year, String name) {
        this.year = year;
        this.name = name;
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
                '}';
    }
}

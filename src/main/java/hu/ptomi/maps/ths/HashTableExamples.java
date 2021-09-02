package hu.ptomi.maps.ths;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * Same as HashMap + it is thread-safe, and deprecated.
 */
public class HashTableExamples {

    private static class Person {
        private final String name;

        private Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            System.out.println("this equals with = " + person.name);
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
//            return Objects.hash(name);
            return 1; // All element will be in the same bucket.
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private static final Object DUMMY = new Object();

    public static void main(String[] args) {
        // Check this with time command: time java HashTableExamples
        // paulintamas@DESKTOP-NO2RFK5:~$ time echo "" // Same as the CPU graph/histogram on macOS/Linux.
        //      real    1m47.000s = Wall clock time, time from start to finish of the call. This is all elapsed time including time slices used by other processes and time the process spends blocked (for example if it is waiting for I/O to complete).
        //      user    2m31.000s = The amount of CPU time spent in user-mode code (outside the kernel) within the process. This is only actual CPU time used in executing the process. Other processes and time the process spends blocked do not count towards this figure.
        //      sys     0m4.000s = Sys is the amount of CPU time spent in the kernel within the process. This means executing CPU time spent in system calls within the kernel, as opposed to library code, which is still running in user-space. Like 'user', this is only CPU time used by the process.
        //      Keep in mind that real represents actual elapsed time, while user and sys values represent CPU execution time. As a result, on a multicore system, the user and/or sys time (as well as their sum) can actually exceed the real time.
        // Experiment 1: real time is higher with ConcurrentHashMap than with hashtable, maybe because the getting is better with CHashMap
//        Map<Integer, Long> map = new Hashtable<>();
        Map<Integer, Long> map = new ConcurrentHashMap<>();
        long start = System.currentTimeMillis();
        IntStream.range(0, 10_000_000).parallel().forEach(i -> map.put(i, ((long) i) * i));
//        System.out.println("map size = " + map.size());
//        System.out.println("map = " + map);
        System.out.println("time = " + (System.currentTimeMillis() - start));

        // Experiment 2: check the read time only with put part opf the code, then check with put and get, subtract (put+get time) - (put time) = (get time)
        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(IntStream.range(0, 10_000_000).parallel().mapToLong(map::get).sum());
        }
        System.out.println("time = " + (System.currentTimeMillis() - start));

        // Experiment 3: never write asymmetric equals, a=b but b!=a.
        Map<Person, Object> wLookup = new Hashtable<>(); // Other objet is the new key.
//        Map<Person, Object> wLookup = new ConcurrentHashMap<>(); // Other object is the old key, JDK developers decided to change this.
        wLookup.put(new Person("Tamas"), DUMMY);
//        wLookup.put(null, DUMMY); // Not allowed, but worked for Java 9 for HashMap.
        System.out.println(wLookup);
        System.out.println(wLookup.get(new Person("John"))); // With hashCode = 1, equals should be called
    }
}

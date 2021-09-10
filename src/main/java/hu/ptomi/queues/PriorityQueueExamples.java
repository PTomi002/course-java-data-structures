package hu.ptomi.queues;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Spliterator;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * ordered = e.g. List it maintains an order by some aspect (insertion, LRU = access).
 * sorted = e.g. TreeMap it maintains an order by some value.
 * + stable sort = preserve the equal elements order
 * + unstable sort = does not preserve the equal elements order
 */
public class PriorityQueueExamples {

    private static class Person implements Comparable<Person> {
        private final String name;

        private Person(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return name.compareTo(o.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this); // Same as object.hashCode() if not overridden
//            return Objects.hash(name);
        }
    }

    // A task for the students.
    private static class PrioritizedTask<V> implements Callable<V>, Comparable<PrioritizedTask<V>> {

        private final int priority;
        private final Callable<V> delegate;

        private PrioritizedTask(int priority, Callable<V> delegate) {
            this.priority = priority;
            this.delegate = delegate;
        }

        @Override
        public V call() throws Exception {
            return delegate.call();
        }

        @Override
        public int compareTo(PrioritizedTask o) {
            return Integer.compare(priority, o.priority);
        }

        @Override
        public String toString() {
            return "PrioritizedTask{" +
                    "priority=" + priority +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var pq = new PriorityQueue<Person>();
        // unbounded
        // unordered (???)
        // not stable sorted
        // priority thread pool

        int[] numbers = {1, 2, 2, 3, 4, 5}; // unstable sort as 2 is 2, they are the same
        Person[] persons = {
                new Person("John"),
                new Person("Alison"),
                new Person("Alison"),
                new Person("Alison"),
                new Person("Peter")
        };

        // A little differently:
//        System.out.println("Before sort:"); // J, A, A, A, P
//        Stream.of(persons).forEach(System.out::println);
//        Arrays.sort(persons);
//        System.out.println("After sort:"); // A, A, A, J, P (A, A, A are equal and their order is preserved, check hashCode(...))
//        Stream.of(persons).forEach(System.out::println);

        System.out.println("Before enqueue:"); // J, A, A, A, P
        Stream.of(persons).forEach(System.out::println);
        pq.addAll(Arrays.asList(persons));

        System.out.println("Poll from queue:");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // J, A, A, A, P (A, A, A are reordered, because this queue does not preserve equal items order)
        }
        // Check its spliterator characteristic
        System.out.println(pq.spliterator().hasCharacteristics(Spliterator.ORDERED));
        System.out.println(pq.spliterator().hasCharacteristics(Spliterator.SORTED));

        // Use Case example: prioritized runnable work queue example, this should be checked on another course!
        var priorityPool = (ExecutorService) new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.SECONDS,
                new PriorityBlockingQueue<>());
        ThreadLocalRandom.current().ints(0, 100).limit(100).parallel().forEach(i ->
                priorityPool.submit(new PrioritizedTask<>(i, () -> {
                    System.out.println("Im done: " + i);
                    return i;
                }))
        );
        priorityPool.shutdown();
        while (!priorityPool.awaitTermination(1, TimeUnit.SECONDS)) ;

        // Last thing: check JDK usage!
    }
}

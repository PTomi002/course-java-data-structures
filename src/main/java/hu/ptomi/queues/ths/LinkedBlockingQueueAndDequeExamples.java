package hu.ptomi.queues.ths;

import java.util.concurrent.*;

/**
 * Blocking  means when I call poll(...)/offer(...) I can tell how much time I want to wait till an element comes to the empty queue.
 * <p>
 * Array based structure or Linked base structure is better? In Java array based structure is always better.
 * Because:
 * + The memory layout of Java and other programming languages array based structures almost always beat the linked bases structures.
 * + In case of linked structure there are links pointing to the nodes and they are in the different part of the memory map, and memory needs to swapped into caches loaded into the cache lines.
 * <p>
 * Cache line: Cache line — the unit of data transfer between cache and memory.
 * <p>
 * Only Use Case when LinkedBlockingQueue outperforms other BlockingQueues is when 1 thread offer elements into it and 1 thread get elements from it, because of lock splitting.
 */
public class LinkedBlockingQueueAndDequeExamples {
    public static void main(String[] args) throws InterruptedException {
        // LinkedBlockingQueue/Deque
        // Use Case: in task queue e.g.: Executors
//        ExecutorService pool = Executors.newFixedThreadPool(10);
        ExecutorService pool = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()); // Reentrant locks.
//                new LinkedBlockingDeque<>()); // Not faster than the original queue.
//                new ArrayBlockingQueue<>(10_000_000)); // Faster than the original queue.
//                new LinkedTransferQueue<>()); // Slowest by far, uses CAS operations.
        ExecutorService stealingPool = Executors.newWorkStealingPool(); // Almost 2 times quicker, uses ForkJoinPool, for memory bound (not IO tasks like HTTP, JDBC call) tasks it is better.
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
//            pool.submit(() -> {
//            });
            stealingPool.submit(() -> {
            });
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) + " ms");
        pool.shutdown();
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) ;

        // Check the same without pool and re-using threads.
        Thread[] threads = new Thread[100]; // Only 100 because we do not want to run out of OS threads.
        start = System.currentTimeMillis();
        for (int i = 0; i < 1_000; i++) { // With less iteration it still takes a lot more time than with pool.
            for (int j = 0; j < threads.length; j++) {
                threads[j] = new Thread(() -> {
                });
                threads[j].start();
            }
            for (int j = 0; j < threads.length; j++) {
                threads[j].join();
            }
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) + " ms");
    }
}

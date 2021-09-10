package hu.ptomi.queues.ths;

import java.util.concurrent.*;

/**
 * Like ArrayDeque (circular list) but with blocking characteristic.
 * <p>
 * ArrayBlockingQueue uses a single lock, while LinkedBlockingQueue/Deque uses a take and a put lock.
 * ArrayBlockingQueue is bounded!
 * It may outperform LinkedBlockingQueue as it is an array based structure against linked base structure.
 */
public class ArrayBlockingQueueExamples {
    public static void main(String[] args) {
        // Check usage in JDK, not much.
        BlockingQueue<Integer> abq = new ArrayBlockingQueue<>(10);

        // Next question and experiment:
        // How many threads will it construct?
        ExecutorService pool = new ThreadPoolExecutor(10, 100,
                1L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>());
                new LinkedBlockingQueue<>(900));
        for (int i = 0; i < 10_000; i++) {
            pool.submit(() -> {
                Thread.sleep(1_000);
                return null;
            });
        }
        // Obvious answer would be 100, but we have a task queue here.
        // Create a Thread Dump, check the num of pool threads, it will be 10, because the executor does not create additional thread until it is full, which is now unbounded.
        // Change the queue limit to 900, Thread Dump, we see 100 threads.
    }
}

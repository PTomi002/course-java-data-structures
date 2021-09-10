package hu.ptomi.queues.ths;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * During insertion and fetching there is no such thing as order in a multithreaded environment:
 * + threads might race with each other for the head of the queue
 * + T1 might get it before T2, but the OS scheduler then can give an opportunity to T2 to do something with it before T1
 */
public class ConcurrentLinkQueueAndDequeExamples {
    public static void main(String[] args) {
        Queue<Integer> clq = new ConcurrentLinkedQueue<>();

        System.out.println(clq.offer(1));
        System.out.println(clq.add(2));
        System.out.println(clq.poll());
        System.out.println(clq.poll());
        System.out.println(clq.poll());

        System.out.println(clq.size()); // O(n) complexity.

        // Check concurrent execution with the available num of CPU.
        LongAccumulator accumulator = new LongAccumulator(Long::max, 0);
        System.out.println("Logical CPU: " + Runtime.getRuntime().availableProcessors());
        IntStream.range(0, 100_000_000)
                .parallel()
                .forEach(i -> {
                    clq.offer(i);
                    accumulator.accumulate(clq.size()); // With basic logic the max should be the num of hardware CPU threads, but will be much higher. The devs wrote it a way that they traverse all the elements and counting them (during this other operations are permitted and changing the size!).
                    clq.poll();
                });
        System.out.println("Maximum size: " + accumulator.get());

        // Called deck: lifo or fifo or some hybrid
        Deque<Integer> cld = new ConcurrentLinkedDeque<>();
    }
}

package hu.ptomi.queues.ths;

import java.time.Duration;
import java.util.concurrent.*;

public class HighlySpecializedQueueExamples {

    static class Insurance implements Delayed {
        private final String owner;
        private final long expire;

        Insurance(String owner, long expire) {
            this.owner = owner;
            this.expire = expire;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.toNanos(expire - System.nanoTime());
        }

        @Override
        public int compareTo(Delayed other) {
            if (other == this) {
                return 0;
            }
            if (other instanceof Insurance) {
                Insurance x = (Insurance) other;
                long diff = expire - x.expire;
                if (diff < 0) {
                    return -1;
                } else if (diff > 0) {
                    return 1;
                }
            }
            return Long.compare(getDelay(TimeUnit.NANOSECONDS), other.getDelay(TimeUnit.NANOSECONDS));
        }

        @Override
        public String toString() {
            return "Insurance{" +
                    "owner='" + owner + '\'' +
                    ", expire=" + expire +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1) DelayQueue
        // USe Case: Maybe in protocols.
        // Better solution is:
//        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        var dlq = new DelayQueue<Insurance>();

        dlq.put(new Insurance("Kimberly", Duration.ofSeconds(10).toNanos() + System.nanoTime()));
        dlq.put(new Insurance("Tamás", Duration.ofSeconds(3).toNanos() + System.nanoTime()));
        dlq.put(new Insurance("Heinz", Duration.ofSeconds(5).toNanos() + System.nanoTime()));

        for (int i = 0; i < 11; i++) {
            System.out.println(dlq.poll());
            Thread.sleep(1000);
        }

        // 2) SynchronousQueue: when you put you have to wait until someone calls take, and when you take you have to wait until someone calls put.
        // Use Case: In Executors cached pool.
        // This queue is not async.
        // What does this executor config do? It creates new threads as requests come (as SynchronousQueue is always full and always empty) and keep them alive idle for 60 sec.
//        Executors.newCachedThreadPool(); // Inline the static method here, used in many code in JDK.
        var cachedPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());

        // Experiment: SynchronousQueue is not a FIFO queue, by default it is a LIFO.
//        var sq = new SynchronousQueue<Integer>();
        var sq = new SynchronousQueue<Integer>(true);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            var t = new Thread(() -> {
                try {
                    sq.put(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            Thread.sleep(10);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(sq.take());
        }

        // 3) LinkedTransferQueue
        // Little story: divide-and-conquer, parallel decomposition: you split the problem into half and then split the half into half until they are small enough,
        //      then this point you do the tasks sequentially and merge them up until the root element.
        //      When JDK published ForkJoin people started to use it and LinkedTransferQueue was written for this Use Case,
        //      and works very well (only for work stealing), but others used it in a wrong way.
        var ltq = new LinkedTransferQueue<>();
    }
}

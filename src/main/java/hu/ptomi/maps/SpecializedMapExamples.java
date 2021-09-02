package hu.ptomi.maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAccumulator;

public class SpecializedMapExamples {

    private static class IdentityKey<K> {
        private final K key;

        private IdentityKey(K key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdentityKey<?> that = (IdentityKey<?>) o;
            return key == that.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1) EnumSet and EnumMap
        EnumSet<Thread.State> threadStates = EnumSet.allOf(Thread.State.class);
        threadStates.remove(Thread.State.BLOCKED);
        System.out.println(threadStates);

        EnumMap<Thread.State, LongAccumulator> threadStatistics = new EnumMap<>(Thread.State.class);
        // ths version
        Map<Thread.State, LongAccumulator> syncThreadStatistics = Collections.synchronizedMap(threadStatistics);

        // 2) IdentityHashMap: reference-equality in place of object-equality when comparing keys (and values).
        Map<String, Integer> identity = new IdentityHashMap<>();
        identity.put("Tamás", 1991);
        System.out.println("Check Tamás: " + identity.get("Tamás")); // Same string from the string pool.
        System.out.println("Check Tamás: " + identity.get(new String("Tamás")));

        // Thread-safe identity map
        Map<IdentityKey<String>, Integer> experimentIdentity = new ConcurrentHashMap<>();
        experimentIdentity.put(new IdentityKey<>("Tamás"), 1991);
        System.out.println("Check Tamás: " + experimentIdentity.get(new IdentityKey<>("Tamás")));
        System.out.println("Check Tamás: " + experimentIdentity.get(new IdentityKey<>(new String("Tamás"))));

        // 3) Properties: extends Hashtable, but uses ConcurrentHashMap as reading is faster than modifying, and you use properties for reading props.
        Properties prop = new Properties();
        System.out.println("ENV: " + System.getenv());
        System.out.println("PROPS: " + System.getProperties());

        int parallelism = Integer.parseInt(System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism" ,"42"));
        // Much better way to get the system prop.
        int parallelismBetter = Integer.getInteger("java.util.concurrent.ForkJoinPool.common.parallelism" ,42);

        // 4) WeakHashMap
        // See: Extreme Java Course.
    }
}

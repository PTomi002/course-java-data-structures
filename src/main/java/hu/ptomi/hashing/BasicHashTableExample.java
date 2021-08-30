package hu.ptomi.hashing;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * No duplicates, backed by buckets and hash function, identity checked with equals(...) in case of collision.
 */
public class BasicHashTableExample {

    static class BasicHashTable<K, V> {
        // Normally it is a list array for collision cases and search with equals.
        // The better the distribution the lesser chance to have a clash, we need a good hashing function.
//        private final Object[] buckets = new Object[101];
        private final Object[] buckets = new Object[128]; // we have all the items now.

        public void add(K key, V value) {
            Objects.requireNonNull(key, "key mus not be null");
            Objects.requireNonNull(value, "value mus not be null");

            int pos = calculatePosition(key);
            if (buckets[pos] != null) System.err.println("Collision with: " + pos);
            else buckets[pos] = value;
        }

        @SuppressWarnings("unchecked")
        public V get(K key) {
            Objects.requireNonNull(key, "key must be not null");

            int pos = calculatePosition(key);
            return (V) buckets[pos];
        }

        @Override
        public String toString() {
            return "BasicHashTable{" + "arr=" + Stream.of(buckets).filter(Objects::nonNull).collect(Collectors.toList()) + '}';
        }

        private int calculatePosition(K key) {
            int hash = Objects.hashCode(key);
//            System.out.println("hash: " + hash + " % size: " + buckets.length + " = " + hash % buckets.length);
//            System.out.println("hash: " + hash + " % size: " + buckets.length + " = " + (hash & 100 - 1)); // Bits on the right side, 99 bits.
            // % is a very CPU cycle consuming operator, more than 20 cycles, around 30.
//            return Math.abs(hash % buckets.length);
            // better hashing function.
            return Math.abs(hash & 127); // we have all the items now.
        }
    }

    public static void main(String[] args) {
        BasicHashTable<Integer, String> hashTable = new BasicHashTable<>();

        hashTable.add(1, "one");
        hashTable.add(2, "two");
        hashTable.add(42, "the number");
        hashTable.add(243, "lot");
        hashTable.add(244, "more lot"); // Will cause collision as hash = 244 % 101 = 42, we need a better hashing function

        System.out.println(hashTable);
    }
}

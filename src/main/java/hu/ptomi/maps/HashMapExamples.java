package hu.ptomi.maps;

import java.util.*;

/**
 * Backed by a hash table, no duplicates, identity on hashCode(...) and equals(...).
 * <p>
 * O(1) for get/check an element, worst case O(logn).
 */
public class HashMapExamples {

    private static class Person implements Comparable<Person> {
        private final String name;
        private final int year;
        private final int month;
        private final int day;

        public Person(String name, int year, int month, int day) {
            this.name = name;
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (year != person.year) return false;
            if (month != person.month) return false;
            if (day != person.day) return false;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            // With a million people there is a good cache to have clashes, because many people was born on the same year and month.
            return (name.hashCode() << 16) ^ (day << 12) ^ (month << 8) ^ year;
        }

        // Since Java 1.8 this is necessary for HashMap!
        @Override
        public int compareTo(Person o) {
            int r = name.compareTo(o.name);
            if (r == 0) r = Integer.compare(year, o.year);
            if (r == 0) r = Integer.compare(month, o.month);
            if (r == 0) return Integer.compare(day, o.day);
            else return r;
        }
    }

    /**
     * Many collection FWs implement this.
     */
    private static class MultiMap<K, V> extends HashMap<K, List<V>> {
        public List<V> putList(K key, V value) {
            List<V> v = get(key);
            if (v != null) {
                v.add(value);
            } else {
                v = new ArrayList<>(List.of(value));
                put(key, v);
            }
            return v;
        }

        @Override
        public String toString() {
            return "MultiMap{" + entrySet() + "}";
        }
    }

    public static void main(String[] args) {
        // Basic history of hashmap
        // 1.2 was born - keys were hashed with % operator (was slow but give us a good distribution)
        // 1.4 - keys were hashed with & bitmask (was fast, but can be bad distribution)
        Person p = new Person("Tamás", 1991, 9, 29);

        System.out.println("Month: " + Integer.toBinaryString(9));
        System.out.println("Month shifted: " + Integer.toBinaryString(9 << 8));
        System.out.println("Year: " + Integer.toBinaryString(1991));
        System.out.println("Sum: " + Integer.toBinaryString((9 << 8) ^ 1991));

        // HashMap, check its usage in the JDK, used in a lot of places.
        // HashMap - 2600
        // ArrayList - 4900
        // 1.8 uses a tree if there are too many clashes.
        // In the past, the HashMap used the remainder function % to decide where to place entries in the HashMap.  Since Java 1.4 they use bit masking (much faster).  The problem is that bit masking is very sensitive to strange bit patterns.  Imagine for example using float as a key.  A lot of float numbers have the same mantissa, so would end up in the same bucket.  This is why they need to XOR and SHIFT bits around a bit to give a better distribution of bits, hopefully with the differences being in the lower bits.  If you have 1 million entries in the map, and the bottom 10 bits are always the same, you won't have a great lookup performance.  Since Java 8 they changed the lookup to O(log n) instead of O(n) by implementing a tree when a bucket had too many clashes.  That was good.  However, they also oversimplified the re-hash() function inside HashMap to only do basically (hash >>> 16) ^ hash.  So my suggestion now is - your hashCode() should be as unique as possible, and the range of values should be as small as possible.

        // Few technics used with HashMap:
        Map<Integer, String> numbers = new HashMap<>(
                Map.of(2, "two", 100, "hundred", -1, "minus one")
        );
        // Few technics used with HashMap:
        MultiMap<Integer, String> mMap = new MultiMap<>();
        mMap.putList(11, "eleven");
        mMap.putList(13, "eleven");
        mMap.putList(11, "other eleven");
        System.out.println(mMap);
        // Few technics used with HashMap:
        mMap.computeIfAbsent(14, num -> new ArrayList<>(List.of("fourteen-computeIfAbsent")));
        System.out.println(mMap);

        // https://www.javaspecialists.eu/archive/Issue235-Checking-HashMaps-with-MapClashInspector.html
    }
}

package hu.ptomi.maps.ths;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Use Case: Computation overhead is not so uch, so it is advised to use this instead of HashMap, since Java 7.
 */
public class ConcurrentHashMapExamples {
    public static void main(String[] args) {
        // Segmented class, there is a small chance that more writer threads will clash.
        Map<Integer, String> map = new ConcurrentHashMap<>();
    }
}

package hu.ptomi;

public class ComputationalTimeComplexity {
    volatile static int value;

    public static void main(String[] args) {
        // to warm up the VM profiler
        for (int i = 0; i < 3; i++) {
            testAll();
        }
    }

    private static void testAll() {
        // O(1), e.g: hashing
        value = 42;
        // O(n) linear, e.g: searching a list
        for (int n = 100_000_000; n <= 1_600_000_000 && n > 0; n *= 2) { // Double the size of the n number.
            linearComplexity(n);
        }
        // O(n*n) quadratic time, e.g.: bubble sort
        for (int n = 1_000; n <= 100_000 && n > 0; n *= 2) { // Double the size of the n number.
            quadraticComplexity(n);
        }
        // O(log n) logarithmic time, e.g.: tree search
        for (long n = 1_000_000_000L; n <= 1_000_000_000_000L && n > 0; n *= 2) { // Double the size of the n number.
            logarithmicComplexity(n);
        }
        // O(n*log n) quasilinear time, e.g.: merge sort
        for (int n = 100_000; n <= 1_600_000 && n > 0; n *= 2) { // Double the size of the n number.
            quasilinearComplexity(n);
        }
    }

    private static void quasilinearComplexity(int n) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (long j = 1; j < n; j *= 2) {
                value = (int) j;
            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println(n + " time= " + time);
    }

    /**
     * n -> infinity, one step at a time, at every step we divide by half (divide and conquer).
     * In case of log2 (n), n = 1,2,4,8,16,32,...
     * <p>
     * .......
     * 128000000000 time= 0
     * 256000000000 time= 0
     * 512000000000 time= 0
     * <p>
     * It is too fast to measure the time, so measure the steps instead.
     */
    private static void logarithmicComplexity(long n) {
        int steps = 0;
        long time = System.currentTimeMillis();
        for (long i = 1; i < n; i *= 2) {
            value = (int) i;
            steps++;
        }
        time = System.currentTimeMillis() - time;
        System.out.println(n + " time= " + time + " steps= " + steps);
    }

    /**
     * 1000 time= 1
     * 2000 time= 3
     * 4000 time= 13
     * 8000 time= 52
     * 16000 time= 213
     * 32000 time= 839
     * 64000 time= 3366
     * <p>
     * As we double the input we got 2*2=4 times higher throughput
     */
    private static void quadraticComplexity(int n) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                value = j;
            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println(n + " time= " + time);
    }

    private static void linearComplexity(int n) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            // VM is good at optimizing this useless code out.
            // Putting it to a volatile field.
            value = i;
        }
        time = System.currentTimeMillis() - time;
        System.out.println(n + " time= " + time);
    }
}

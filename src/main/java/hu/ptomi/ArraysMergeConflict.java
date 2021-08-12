package hu.ptomi;

/**
 * Array based data structures.
 * Most compact and fastest way to store objects and primitives, memory map is a huge chunk of memory space.
 * While for linked based data structures we may hop from memory page to another, it is scattered in the memory.
 */
public class ArraysMergeConflict {
    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        // single-dimension array is a single object, so it has an object header which is 12 bytes
        //      on a 64-bit machine with compressedOops (make pointers 32 bit and compute them to 64 bit)
        // 4 bytes for storing length of the array
        // 3 x 4 bytes for the numbers in the array
        // 12 header + 4 length + 12 int values = 28 (rounded up to the nearest number that can be divided by 8, so it is) 32 bytes
        int[] larger = new int[1000];
        // object header 12 bytes, compressedOops
        // length 4 bytes
        // 1000 x 4 bytes numbers
        // 12 + 4 + 4000 = 4016
        // So it is O(n) space complexity

        short[] smaller; // 2 bytes per entry
        byte[] best;// 1 bytes per entry
        boolean[] sameBest; // 1 bytes per array

        // Array indexing
        String[] abc = {"Aa", "Bb", "Cc"};
        String[] abcd = new String[abc.length + 1];
        System.arraycopy(abc, 0, abcd, 0, abc.length);
        abcd[3] = "Dd";

        // Array of arrays
        int[][][][] quadro = {{{{1, 2}, {3, 4}}}};
        int[][][] sub = quadro[0];      // {{{1, 2}, {3, 4}}}
        int[][] subsub = sub[0];        // {{1, 2}, {3, 4}}
        int[] subsubsub = subsub[0];    // {1, 2}
        int number = subsubsub[1];      // 2
        System.out.println(number);

        // We do not use multi-dimensional array:
        // 1) not stored as a matrix in the memory
        // 2) consumes a lot of memory
        // The quickest way to read memory is from end->beginning or beginning->end, Java loads them to cache lines.
    }
}

/**
 * Hi,
 * <p>
 * I do have a question here:
 * <p>
 * int[] values = {1, 2, 3};
 * // Object header 12 bytes
 * // length = 4 bytes ------------------?? It's still not clear to me what a length means here? Please explain..
 * // 3 x 4 bytes for values // No we have 3 elements * 4 that is the length. Why we are again adding +4 in final calculation?
 * // 12 + 4 + 12 = 28 = 28 = 32 byte
 * <p>
 * Thank you..
 * <p>
 * Hi Karan, length is a field inside array.  Remember that you can ask how many elements are inside any array with ".length".  That's 4 bytes for the 32-bit int.
 * <p>
 * Object size is always a multiple of 8 for object alignment, hence the additional padding of 4 in that calculation.
 * <p>
 * Object headers depend on what you are running on:
 * <p>
 * 32-bit Java:
 * java.lang.Object object internals:
 * OFFSET  SIZE  TYPE DESCRIPTION                    VALUE
 * 0     4       (object header)                01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * 4     4       (object header)                f8 68 00 0d (11111000 01101000 00000000 00001101) (218130680)
 * Instance size: 8 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 * <p>
 * 64-bit Java with Compressed Oops (default usually nowadays):
 * <p>
 * java.lang.Object object internals:
 * OFFSET  SIZE  TYPE DESCRIPTION                    VALUE
 * 0     4       (object header)                01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * 4     4       (object header)                00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4       (object header)                e1 11 5c ff (11100001 00010001 01011100 11111111) (-10743327)
 * 12     4       (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * 64-bit Java without Compressed Oops:
 * <p>
 * java.lang.Object object internals:
 * OFFSET  SIZE  TYPE DESCRIPTION                    VALUE
 * 0     4       (object header)                01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * 4     4       (object header)                00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4       (object header)                60 8f 3e 1b (01100000 10001111 00111110 00011011) (457084768)
 * 12     4       (object header)                01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 * <p>
 * <p>
 * Compressed Oops make the references 4 bytes large on a 64-bit machine.  Usually they would be 8 bytes.  You can use Compressed Oops up to about 32 GB or so of heap.  It is quite a bit faster than normal Oops and of course uses less memory.
 * <p>
 * Hope this helps a bit.
 */

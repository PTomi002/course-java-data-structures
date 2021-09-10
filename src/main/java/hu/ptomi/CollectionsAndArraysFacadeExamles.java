package hu.ptomi;

import java.util.*;

public class CollectionsAndArraysFacadeExamles {
    public static void main(String[] args) {
        // Collections facade
        var lifoQ = Collections.asLifoQueue(new ArrayDeque<>());
        System.out.println(lifoQ);

//        var otherList = new ArrayList<String>(); // To avoid class cast exception and corruption.
        var otherList = Collections.checkedList(new ArrayList<>(), String.class);
        Collections.addAll(otherList, "Tamás", "Kimberly");
        // What happens now?
        List unchecked = otherList;
//        unchecked.add(14);

        System.out.println(otherList); // It works as type is erased in runtime, but the collection is corrupted.
//        for (String s : otherList) { // How to avoid this for example in a 3rd party lib if you are the vendor?
//            System.out.println(s);
//        }

        System.out.println(Collections.disjoint(
                new ArrayList<>(List.of("10", "Tamás", "car0")),
                new HashSet<>(List.of("11", "Kimberly", "truck0"))
        ));

        Collections.shuffle(new LinkedList<>()); // Not advised.

        System.out.println(Collections.emptySet());

        System.out.println(Collections.singletonList("Tamás"));

        var uList = List.copyOf(List.of(1, 2, 3, -10));

        // Arrays facade
        System.out.println(Integer.toHexString(1991));
        System.out.println(
                Integer.compareUnsigned( // leftmost number is the sign, comparing it normally would result in -1.
                        0x8912_1111,
                        0x4123_1234
                )
        );

        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2, 3, 4};
        System.out.println(a.equals(b)); // Compares them as objects: by default that is the obj references.
        System.out.println(Arrays.equals(a, b)); // Compares the data inside the array.
        // Could not compare multidimensional arrays: int[][] a = { {1,2}, {4} };
        // As this one is an object array containing an int array.

        int[][] aa = {{1, 2}, {-10}};
        int[][] bb = {{1, 2}, {-10}};
        System.out.println(Arrays.equals(aa, bb));
        System.out.println(Arrays.deepEquals(aa, bb));

        Arrays.parallelSetAll(new long[100], i -> (long) i * i);
    }
}

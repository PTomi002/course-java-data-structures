package hu.ptomi;

/**
 * What is O(1)?
 * + constant time
 *
 * What is O(log n)?
 * + logarithmic time
 *
 * What is O(k^n), thus some constant k to the power of n?
 * + exponential time
 *
 * What is O(n * log n)?
 * + quasilinear time
 *
 * For a quadratic function, when n=10_000_000 it takes 2 seconds to complete. How long will it take approximately when n=100_000_000?
 * + f(n) is quadratic
 * + f(10_000_000) = 2 sec
 * + f(100_000_000) = 200 sec
 * +    linear case: 2 * 10 sec, because the input is 10 times more, but the method is linear
 * +    quadratic case: 2 * 10 * 10 sec, because the input is 10 times more, but the method is quadratic
 *
 * In an English dictionary with ten million words, what is the maximum number of steps needed to find any single word?
 * + 24 (log2(10_000_000)) = 23.26
 *
 * What is faster? O(n) or O(n*logn)
 * + Depends on the size of n.
 *
 * As n approaches infinity, which will be slowest? O(n*logn) or O(n) or O(logn)
 * + O(n*logn)
 *
 * What is typically the most space efficient structure in Java?
 * + Linked Node based structure    []
 * + Array based structure          [X], because memory map of it is more efficient
 *
 * What is the Space Complexity of an ArrayList?
 * + O(1)       []
 * + O(n)       [X]
 * + O(logn)    []
 * + O(n*logn)  []
 *
 * What is the Space Complexity of a LinkedList?
 * + O(1)       []
 * + O(n)       [X]
 * + O(logn)    []
 * + O(n*logn)  []
 *
 * Which has the best Space Complexity?
 * + LinkedList             []
 * + ArrayList              []
 * + Array                  []
 * + All exactly the same   [X], because others have more metadata stored, but for Big O notation that does not matter
 *
 * What is the faster to traverse in a 2 dimensional int array?
 * + left -> right, top -> bottom   [X]
 * + top -> bottom, left -> right   []
 * + exactly the same               []
 *
 * How many bytes does a LinkedList use to store 10_000 objects on a 64-bit machine with compressed OOPS?
 * + 10_032 bytes []
 * + 40_032 bytes []
 * + 80_032 bytes []
 * + 160_032 bytes []
 * + 240_032 bytes [X] (???: How is it calculated?)
 *
 * How many bytes does "new ArrayList<>(10_000)" use on a 64-bit machine with compressed OOPS?
 * + 10_032 bytes   []
 * + 40_032 bytes   [X] = 10_000 x 4 bytes per object reference (compressedOops) + 12 bytes object header + rounding
 * + 80_032 bytes   []
 * + 160_032 bytes  []
 * + 240_032 bytes  []
 *
 * What is the superclass of int[]?
 * + long[]         []
 * + Object         [X]
 * + int            []
 * + none of above  []
 *
 * What is the superclass of int[][][]?
 * + int[]          []
 * + Object[]       [X] (??? Why?)
 * + Object         []
 * + none of above  []
 *
 * What is List derived from? (Mark all that apply)
 * + Set            []
 * + Iterable       [X]
 * + Stream         []
 * + Collection     [X]
 * + Iterator       []
 */
public interface QuestionsAndAnswers {
}

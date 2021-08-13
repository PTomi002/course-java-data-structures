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
 * How can we create a truly immutable List in Java?
 * + new ArrayList<>()                      []
 * + Arrays.asList(...)                     []
 * + List.of(...)                           [X]
 * + Collections.unmodifiableList(list)     []
 *
 * What is the computational time complexity to add an element at the end of an ArrayList?
 * + O(1)       [X], because you know the latest index.
 * + O(logn)    []
 * + O(n)       []
 * + O(n*n)     []
 *
 * What is the computational time complexity to add an element at the front of an ArrayList?
 * + O(1)       []
 * + O(logn)    []
 * + O(n)       [X], because you have to shift all the elements right.
 * + O(n*n)     []
 *
 * What is the computational time complexity to get an element from the middle of an ArrayList?
 * + O(1)       [X], because you use indexes.
 * + O(logn)    []
 * + O(n)       []
 * + O(n*n)     []
 *
 * What is the computational time complexity to remove all elements of an ArrayList using remove(0) until it is empty?
 * + O(1)       []
 * + O(logn)    []
 * + O(n)       []
 * + O(n*n)     [X], because you shift all the N elements N times.
 *
 * What is the computational time complexity to remove all elements of an ArrayList using removeIf() until it is empty?
 * + O(1)       []
 * + O(logn)    []
 * + O(n)       [X]
 * + O(n*n)     []
 *
 * Explanation:
 * The ArrayList does one pass through the array and marks in a bitset which entries are to be kept and which to be removed.  It then does a second pass through and only copies those that need to be kept into the new place in the ArrayList.  Once that is done, it clears the remainder of the array.  Thus the removeIf() is O(n) with the ArrayList.
 *
 * If we add 10_000 elements to an ArrayList with add(), what will be the approximate length of the internal elements[] array?
 * + 10_000 []
 * + 14_000 [X], because arraylist grows by 50% so approximately (10_000 * 0,5) + 10_000
 * + 20_000 []
 *
 * What type of iterator does ArrayList have?
 * + Fail-Fast          [X]
 * + Weak-Consistency   []
 * + Snapshot           []
 *
 * What type of iterator was Vector's Enumeration?
 * + Fail-Fast          []
 * + Weak-Consistency   []
 * + Snapshot           []
 * + None of above      [X], because Enumeration is another interface.
 *
 * What is the intent of the Iterator Design Patterns in the Gang-of-Four?
 * Provide a way to access the elements of an aggregated object sequentially without exposing its underlying representation.
 *
 * What is the computational time complexity to add an element to the end of a CopyOnWriteArrayList?
 * + O(1)       []
 * + O(logn)    []
 * + O(n)       [X], because it copies all the N element.
 * + O(n*n)     []
 *
 * What type of iterator does CopyOnWriteArrayList have?
 * + Fail-Fast          []
 * + Weak-Consistency   []
 * + Snapshot           [X]
 * + None of above      []
 *
 * What is the computational time complexity of sorting a CopyOnWriteArrayList since Java 8?
 * + O(n*logn)      [X], because it is an iterative merge sort, check impl notes from JDK.
 * + O(logn)        []
 * + O(n)           []
 * + O(n*n)         []
 * + O(n*n*logn)    []
 *
 * What is the computational time complexity of list.remove(ThreadLocalRandom.current().nextInt(list.size()) if list is a LinkedList?
 * + O(n*logn)      []
 * + O(logn)        []
 * + O(n)           [X], you have to iterate through the whole list in the worst case.
 * + O(n*n)         []
 * + O(n*n*logn)    []
 *
 * What is the computational time complexity of sorting a LinkedList?
 * + O(n*logn)      [X], because it is an iterative merge sort, check impl notes from JDK.
 * + O(logn)        []
 * + O(n)           []
 * + O(n*n)         []
 * + O(n*n*logn)    []
 *
 * What type of iterator does Vector's iterator() method return?
 * + Fail-Fast                                                                          [X]
 * + Thread-Safe, never throwing ConcurrentModifEx., because Vector is synchronized     []
 * + Weakly-Consistent                                                                  []
 * + Snapshot                                                                           []
 *
 * What is more efficient to serialize with an ObjectOutputStream?
 * + ArrayList  [X]
 * + Vector     []
 *
 */
public interface QuestionsAndAnswers {
}

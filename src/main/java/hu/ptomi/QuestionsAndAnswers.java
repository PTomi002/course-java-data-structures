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
 * + Fail-Fast                                                                          [X], because iterator checks this "int expectedModCount = modCount;"
 * + Thread-Safe, never throwing ConcurrentModifEx., because Vector is synchronized     []
 * + Weakly-Consistent                                                                  []
 * + Snapshot                                                                           []
 *
 * What is more efficient to serialize with an ObjectOutputStream?
 * + ArrayList  [X]
 * + Vector     []
 *
 * The following code does not compile. Comparator.comparingDouble(s -> s.getAverage()).thenComparing(s -> s.getName()) What can we do to fix it? (Mark all that apply)
 * + Use a type witness: Comparator.<Student>comparingDouble(s -> s.getAverage()).thenComparing(s -> s.getName())                       [X]
 * + Explicitly declare lambda parameter: Comparator.comparingDouble((Student s) -> s.getAverage()).thenComparing(s -> s.getName())     [X]
 * + Use method references: Comparator.comparingDouble(Student::getAverage).thenComparing(Student::getName)                             [X]
 *
 * What is the computational time complexity of sorting a LinkedList?
 * + O(n*logn)      [X], because it is Tim sort, check impl notes from JDK.
 * + O(logn)        []
 * + O(n)           []
 * + O(n*n)         []
 * + O(n*n*logn)    []
 *
 * What sorting algorithm does Java use for lists?
 * + Bubble sort    []
 * + Merge sort     []
 * + Quick sort     []
 * + Tim sort       [X]
 *
 * What is the computational time complexity of sorting an array in parallel?
 * + O(n*logn)      [X], divide and conquer, merge sorting
 * + O(logn)        []
 * + O(n)           []
 * + O(1)           []
 *
 * What is the computational time complexity for discovering if the TreeSet contains an element?
 * + O(n*logn)      []
 * + O(logn)        [X], as it is backed by a RB tree
 * + O(n)           []
 * + O(1)            []
 *
 * What is the computational time complexity for adding an element to a TreeSet?
 * + O(n*logn)      []
 * + O(logn)        [X], as it is backed by a RB tree
 * + O(n)           []
 * + O(1)           []
 *
 * What collection does TreeSet use internally to build up the red-black tree?
 * + LinkedHashMap  []
 * + ArrayList      []
 * + TreeMap        [X]
 *
 * What is the computational time complexity for discovering if a ConcurrentSkipListSet contains an element?
 * + O(n*logn)      []
 * + O(logn)        [X]
 * + O(n)           []
 * + O(1)           []
 *
 * Is the ConcurrentSkipListSet thread-safe?
 * + Yes it is, using compareAndSwap and VarHandles to ensure thread safety.    [X]
 * + Yes it is, using synchronized to ensure thread safety.                     []
 *
 * In the worst case scenario, what is the space complexity of the ConcurrentSkipListSet?
 * + O(n*logn)      [X]
 * + O(logn)        []
 * + O(n)           []
 * + O(1)           []
 *
 * What does CopyOnWriteArraySet use to detect duplicates?
 * + compareTo()    []
 * + hashCode()     []
 * + equals()       [X], because backed by a list.
 *
 * What is the computational time complexity of searching whether a CopyOnWriteArraySet contains an element?
 * + O(n*logn)      []
 * + O(logn)        []
 * + O(n)           [X]
 * + O(1)           []
 *
 * What is the computational time complexity of "new CopyOnWriteArraySet(some_other_collection)"?
 * + O(n*logn)      []
 * + O(logn)        []
 * + O(n)           []
 * + O(1)           []
 * + O(n * n)       [X], as it is quadtratic, check if the element is in the collection then add.
 *
 * What is the computational time complexity for finding an element in a hash structure if there are no bucket collisions (clashes on the hash)?
 * + O(n*logn)      []
 * + O(logn)        []
 * + O(n)           []
 * + O(1)           [X]
 *
 * An example of a fixed sized hash table in Java is?
 * + java.util.Hashtable    []
 * + java.util.HashMap      []
 * + String constant table  [X]
 * + ThreadLocalMap         []
 *
 * Why does "new HashSet<>(IntStream.range(1, 1000).boxed().collect(Collectors.toList())).toString()" appear perfectly sorted in Java 8 and 9, but not in earlier versions?
 * + Since Java 8, toString() adds all elements into a TreeSet and then delegates to its toString() method.     []
 * + Integer objects have hashCodes of their own value.  Since Java 8 uses a simplified bit shifting approach
 *      over the previous versions, for small numbers the Integer value will place a mask of the
 *      elements in sequential buckets.                                                                         [X]
 * + The toString() method is implemented with a Stream.collect(Collectors.joining())
 *      and this defaults to an ordered stream since Java 8.                                                    []
 *
 * If we add an element to the HashSet where an equal element already exists, which one will end up in the HashSet?
 * + The old one remains.                   [X]
 * + The new one will eject the old value.  []
 *
 * In Java 8 and 9, if the type of classes in our HashSet implement Comparable, what is the worst-case computational time complexity when adding an element?
 * + O(n*logn)      []
 * + O(logn)        [X], because adding it can be O(n) if we use the simple Nodes, but with TreeNodes it is O(logn), see TreeSet, and assume we have all the elements in the same bucket.
 * + O(n)           []
 * + O(1)           []
 *
 * Until Java 7, what was the worst-case computation time complexity when adding an element?
 * + O(n*logn)      []
 * + O(logn)        []
 * + O(n)           [X]
 * + O(1)           []
 *
 * How did the Java 1.4 HashMap allocate keys to buckets?
 *  + Using bit masking on the hashCode after shifting significant bits to the lower part of the number.    [X]
 *  + Using the remainder operation %.                                                                      []
 *  + Using bit masking directly on the hashCode.                                                           []
 *
 *  What could happen in HashMap from Java 1.2 until 7 if multiple entries pointed to the same bucket?
 *  + A linked list was created inside the HashMap, leading to O(n) performance     [X]
 *  + Entries were simply lost                                                      []
 *  + A binary tree was created when too many clashes happened                      []
 *
 *  Since Java 8, a class HashKey used as a key in a HashMap should always implement: (Mark all that apply)
 *  + hashCode()                                    [X]
 *  + hashcode()                                    []
 *  + equals()                                      [X]
 *  + equals(HashKey)                               []
 *  + compareTo(HashKey) and implement Comparable   [X]
 *
 *  Why is this not a good hash function for a Pixel class with int x and y since Java 8? public int hashCode() { return x << 16 ^ y; }
 *  + Since the left 16 bits of the hashCode are XORed with the hashCode, we would have lots of bucket collisions, even though we have a perfect hash code.     [X]
 *  + It is perfect.                                                                                                                                            []
 *
 *  How can a normal HashMap get corrupted?
 *  + In many unexpected ways.  For example, an infinite loop can be created within the map if we add whilst the map is being resized internally.   [X]
 *  + It is thread safe.                                                                                                                            []
 *
 * What methods should be implemented for Key class in a TreeMap?
 * + compareTo(Key)     [X]
 * + equals(Object)     [X]
 * + hashCode()         [X]
 * + toString()         []
 * + equals(Key)        []
 *
 * What is the computational time complexity of getting a value from a ConcurrentSkipListMap?
 * + O(n*logn)      []
 * + O(logn)        [X]
 * + O(n)           []
 * + O(1)           []
 *
 * When a lot of threads put() and get() on a Hashtable, the bottleneck is most likely going to be
 * + Thread contention, seen by voluntary context switching [X]
 * + The database                                           []
 * + Memory allocation, senn in object allocation rates     []
 * + Stop-the-World garbage collection events               []
 */
public interface QuestionsAndAnswers {
}

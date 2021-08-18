package hu.ptomi.lists;

import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Same as Vector, but as a LIFO impl.
 * <p>
 * LIFO =  what you put last, comes in first.
 * <p>
 * Use Case: this class is deprecated, use Queue/Dequeue(deck) instead.
 */
public class StackExamples {
    public static void main(String[] args) {
        Stack<String> names = new Stack<>();
        names.push("John");
        names.push("Anton");
        names.push("Tamas");

        while (names.size() != 0) {
            System.out.println(names.pop());
        }

        // New way to use.
        // Better synchronization on the queues as you can content on the tail and on the head, while on the stack you cant.
        Deque<String> otherNames = new ConcurrentLinkedDeque<>(Arrays.asList("John", "Anton", "Tamas"));
    }
}

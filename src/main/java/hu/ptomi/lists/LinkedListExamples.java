package hu.ptomi.lists;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Add to the middle: you have to iterate to the middle and change the references, there are no indexes, no need to shift elements.
 * <p>
 * Use Case: use it as a queue add/remove from the beginning/end of the list.
 * <p>
 * Note: always never a good idea to use LinkedList
 */
public class LinkedListExamples {

    public static class MyLinkedList<E> {
        private Node<E> head;
        private Node<E> tail;

        private class Node<E> {     // 12 bytes object header
            Node<E> previous;       // 4 bytes reference (compressedOops)
            Node<E> next;           // 4 bytes ref.
            final E value;          // 4 bytes ref. SUM = 12 + 3 x 4 = 24 bytes per element (while with arrays it is 4 bytes per element only!)

            public Node(E value) {
                this.value = value;
            }
        }

        public void add(E element) {
            Node<E> n = new Node<>(element);
            if (head == null && tail == null) {
                head = tail = n;
            } else {
                n.previous = tail;
                tail.next = n;
                tail = n;
            }
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> names = new MyLinkedList<>();
        names.add("John");
        names.add("Anton");
        names.add("Tamas");
        System.out.println(names);

        LinkedList<String> otherNames = new LinkedList<>(Arrays.asList("John", "Anton", "Tamas"));
        otherNames.sort(null);
        otherNames.remove("Anton");
    }
}

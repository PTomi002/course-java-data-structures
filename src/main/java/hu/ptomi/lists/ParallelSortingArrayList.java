package hu.ptomi.lists;

import java.lang.reflect.Field;
import java.util.*;

class ParallelSortingArrayList<E> extends ArrayList<E> {
    // Discouraged trick, just to represent parallel sorting.
    private static final Field elementDataField;
    private static final Field sizeField;

    static {
        try {
            // As private field we should set it accessible.
            elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
            sizeField = ArrayList.class.getDeclaredField("size");
            sizeField.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public ParallelSortingArrayList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        try {
            // Copied from super class definition.
            final int expectedModCount = modCount;
            Arrays.parallelSort((E[]) elementDataField.get(this), 0, sizeField.getInt(this), c);
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            modCount++;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

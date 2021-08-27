package hu.ptomi.sets.ths;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Copy from the original JDK codebase.
 * <p>
 * Delete all comments and fix errors.
 **/
//public class MyObservable {
//    private boolean changed = false;
//    private Vector<MyObserver> obs;
//
//    public MyObservable() {
//        obs = new Vector<>();
//    }
//
//    public synchronized void addObserver(MyObserver o) {
//        if (o == null)
//            throw new NullPointerException();
//        if (!obs.contains(o)) {
//            obs.addElement(o);
//        }
//    }
//
//    public synchronized void deleteObserver(MyObserver o) {
//        obs.removeElement(o);
//    }
//
//    public void notifyObservers() {
//        notifyObservers(null);
//    }
//
//    public void notifyObservers(Object arg) {
//        /*
//         * a temporary array buffer, used as a snapshot of the state of
//         * current Observers.
//         */
//        Object[] arrLocal;
//
//        synchronized (this) {
//            /* We don't want the Observer doing callbacks into
//             * arbitrary code while holding its own Monitor.
//             * The code where we extract each Observable from
//             * the Vector and store the state of the Observer
//             * needs synchronization, but notifying observers
//             * does not (should not).  The worst result of any
//             * potential race-condition here is that:
//             * 1) a newly-added Observer will miss a
//             *   notification in progress
//             * 2) a recently unregistered Observer will be
//             *   wrongly notified when it doesn't care
//             */
//            if (!changed)
//                return;
//            arrLocal = obs.toArray();
//            clearChanged();
//        }
//
//        for (int i = arrLocal.length - 1; i >= 0; i--)
//            ((MyObserver) arrLocal[i]).update(this, arg);
//    }
//
//    public synchronized void deleteObservers() {
//        obs.removeAllElements();
//    }
//
//    protected synchronized void setChanged() {
//        changed = true;
//    }
//
//    protected synchronized void clearChanged() {
//        changed = false;
//    }
//
//    public synchronized boolean hasChanged() {
//        return changed;
//    }
//
//    public synchronized int countObservers() {
//        return obs.size();
//    }
//}

public class MyObservable {
    private Set<MyObserver> obs = new CopyOnWriteArraySet<>();

    public void addObserver(MyObserver o) {
        Objects.requireNonNull(o, "observer must not be null");
        obs.add(o);
    }

    public void deleteObserver(MyObserver o) {
        Objects.requireNonNull(o, "observer must not be null");
        obs.remove(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        // This is enough and thread-safe as any modification creates a new array, this will be garbage collected by the VM.
        obs.forEach(o -> o.update(this, arg));
    }

    public void deleteObservers() {
        obs.clear();
    }

    public int countObservers() {
        return obs.size();
    }
}

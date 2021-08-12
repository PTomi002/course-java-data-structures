package hu.ptomi;

/**
 * Linked based data structure.
 */
public class SpaceComplexity {

    static class Holder {
        private Object o;
        private Holder n;

        public Holder(Object o) {
            this.o = o;
        }

        public void setNext(Holder n) {
            this.n = n;
        }
    }

    public static void main(String[] args) {
        Holder holder = new Holder("Hello");
        holder.setNext(new Holder("World"));
        for (Holder h = holder; h != null; h = h.n) {
            System.out.println(h.o.toString());
        }
    }
}

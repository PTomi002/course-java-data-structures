package hu.ptomi.hashing;

import java.util.Objects;

public class Pixel implements Comparable<Pixel> {
    private final int x, y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x && y == pixel.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y); // Wrong hash implementation, around 24 clash per hashCode(...).
    }

    // Adding for TreeBins, if there is a lot of clash, Java will turn the bins into a TreeBin for better performance.
    // For hasset and hasmap this is a safety way.
    @Override
    public int compareTo(Pixel o) {
        int result = Integer.compare(x, o.x);
        if (result == 0) return Integer.compare(y, o.y);
        else return result;
    }
}

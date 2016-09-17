package io.github.meijun.geo;

/**
 * point
 * Created by meijun on 6/7/2014.
 */

public class P implements Comparable<P> {

    public final double x;
    public final double y;

    public P(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public P add(P p) {
        return new P(Geo.add(x, p.x), Geo.add(y, p.y));
    }

    public P sub(P p) {
        return new P(Geo.add(x, -p.x), Geo.add(y, -p.y));
    }

    public P mul(double k) {
        return new P(x * k, y * k);
    }

    public P div(double k) {
        return new P(x / k, y / k);
    }

    public double det(P p) {
        return Geo.add(x * p.y, -y * p.x);
    }

    public double dot(P p) {
        return Geo.add(x * p.x, y * p.y);
    }

    public double abs() {
        return Math.sqrt(abs2());
    }

    /**
     * abs()<sup>2</sup>=dot(this)
     */
    public double abs2() {
        return dot(this);
    }

    /**
     * 饶原点旋转角度B（弧度值）产生的新点
     */
    public P rot(double rad) {
        return new P(Geo.add(x * Math.cos(rad), -y * Math.sin(rad)), Geo.add(x * Math.sin(rad), y * Math.cos(rad)));
    }

    public P rot90() {
        //noinspection SuspiciousNameCombination
        return new P(-y, x);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return compareTo((P) obj) == 0;
    }

    @Override
    public int compareTo(P p) {
        int b = Geo.signum(x - p.x);
        if (b != 0) return b;
        return Geo.signum(y - p.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

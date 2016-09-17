package io.github.meijun.geo;

/**
 * Geo utils
 * Created by meijun on 6/7/2016.
 */
public class Geo {
    public static final double EPS = 1e-9;

    public static double add(double a, double b) {
        if (Math.abs(a + b) < EPS * (Math.abs(a) + Math.abs(b))) return 0;
        return a + b;
    }

    public static double cmp(double a, double b) {
        return add(a, -b);
    }

    public static int signum(double x) {
        if (Math.abs(x) < EPS) return 0;
        return x < 0 ? -1 : 1;
    }

    /**
     * 返回两点和原点形成的夹角, 注意这两点都不能为原点
     * @return [0, PI]
     */
    public static double rad(P p1, P p2) {
        return Math.acos(p1.dot(p2) / p1.abs() / p2.abs());
    }

    /**
     * @return angle in [0, 2PI)
     */
    public static double rad2(P p1, P p2) {
        double r = rad(p1, p2);
        if (p1.det(p2) < 0) r = 2 * Math.PI - r;
        return r;
    }
}

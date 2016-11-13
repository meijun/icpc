package lib.geo;

/**
 * Line.
 * Created by meijun on 6/7/2016.
 */
public class Line {
    /**
     * 点到直线的垂足
     */
    public static P proj(P p1, P p2, P q) {
        return p1.add(p2.sub(p1).mul(p2.sub(p1).dot(q.sub(p1)) / p2.sub(p1).abs2()));
    }

    /**
     * 线段相交判定
     */
    public static boolean crsSS(P p1, P p2, P q1, P q2) {
        if (Math.max(p1.x, p2.x) + Geo.EPS < Math.min(q1.x, q2.x)) return false;
        if (Math.max(q1.x, q2.x) + Geo.EPS < Math.min(p1.x, p2.x)) return false;
        if (Math.max(p1.y, p2.y) + Geo.EPS < Math.min(q1.y, q2.y)) return false;
        if (Math.max(q1.y, q2.y) + Geo.EPS < Math.min(p1.y, p2.y)) return false;
        return p2.sub(p1).det(q1.sub(p1)) * p2.sub(p1).det(q2.sub(p1)) <= 0
                && q2.sub(q1).det(p1.sub(q1)) * q2.sub(q1).det(p2.sub(q1)) <= 0;
    }

    /**
     * 直线和线段的相交判定
     */
    public static boolean crsLS(P l1, P l2, P s1, P s2) {
        return s1.sub(l2).det(l1.sub(l2)) * s2.sub(l2).det(l1.sub(l2)) <= 0;
    }

    /**
     * 直线相交判定
     *
     * @return -1=重合 0=平行 1=相交
     */
    public static int crsLL(P p1, P p2, P q1, P q2) {
        if (Geo.signum(p1.sub(p2).det(q1.sub(q2))) != 0) return 1;
        if (Geo.signum(p1.sub(q2).det(q1.sub(p2))) != 0) return 0;
        return -1;
    }

    /**
     * 直线和直线的交点
     */
    public static P isLL(P p1, P p2, P q1, P q2) {
        double d = q2.sub(q1).det(p2.sub(p1));
        if (Geo.signum(d) == 0) return null;
        return p1.add(p2.sub(p1).mul(q2.sub(q1).det(q1.sub(p1)) / d));
    }

    /**
     * 线段到点的距离
     */
    public static double disSP(P p1, P p2, P q) {
        if (p2.sub(p1).dot(q.sub(p1)) <= 0) return q.sub(p1).abs();
        if (p1.sub(p2).dot(q.sub(p2)) <= 0) return q.sub(p2).abs();
        return disLP(p1, p2, q);
    }

    /**
     * 直线到点的距离
     */
    public static double disLP(P p1, P p2, P q) {
        return Math.abs(p2.sub(p1).det(q.sub(p1))) / p2.sub(p1).abs();
    }
}

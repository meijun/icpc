package lib.geo;

/**
 * Polygon.
 * Created by meijun on 6/7/2016.
 */
public class Polygon {

    /**
     * 多边形面积，顺逆时针都可以
     */
    public static double area(P... ps) {
        return Math.abs(directedArea(ps));
    }

    /**
     * 计算多边形的有向面积, 顺逆时针都可以
     *
     * @return 可能为负
     */
    public static double directedArea(P... ps) {
        double res = 0;
        for (int i = 0; i < ps.length; i++) {
            res += ps[i].det(ps[(i + 1) % ps.length]);
        }
        return res / 2;
    }

    /**
     * 点在多边形内外的判定
     *
     * @return 1=内部 0=边上 -1=外部
     */
    public static int contains(P[] ps, P q) {
        int n = ps.length;
        int res = -1;
        for (int i = 0; i < n; i++) {
            P a = ps[i].sub(q), b = ps[(i + 1) % n].sub(q);
            if (a.y > b.y) {
                P t = a;
                a = b;
                b = t;
            }
            if (a.y <= 0 && b.y > 0 && a.det(b) > 0) {
                res = -res;
            }
            if (a.det(b) == 0 && a.dot(b) <= 0) return 0;
        }
        return res;
    }
}

package io.github.meijun.geo;

import java.util.ArrayList;
import java.util.List;

/**
 * circle
 * Created by meijun on 6/7/2016.
 */
public class Circle {
    /**
     * 圆和线段的相交判定
     */
    public static boolean crsCS(P c, double r, P p1, P p2) {
        return Geo.cmp(Line.disSP(p1, p2, c), r) <= 0 &&
                (Geo.cmp(r, c.sub(p1).abs()) <= 0 || Geo.cmp(r, c.sub(p2).abs()) <= 0);
    }

    /**
     * 圆和圆的相交判定
     */
    public static boolean crsCC(P c1, double r1, P c2, double r2) {
        double dis = c1.sub(c2).abs();
        return Geo.cmp(dis, r1 + r2) <= 0 && Geo.cmp(Math.abs(r1 - r2), dis) <= 0;
    }

    /**
     * 四点共圆判定
     */
    public static boolean onC(P p1, P p2, P p3, P p4) {
        P c = CCenter(p1, p2, p3);
        if (c == null) return false; //有三点共线，返回false
        return Geo.add(c.sub(p1).abs2(), -c.sub(p4).abs2()) == 0;
    }

    /**
     * 三点共圆的圆心
     */
    public static P CCenter(P p1, P p2, P p3) {
        if (p2.sub(p1).det(p3.sub(p1)) == 0) return null; // 三点共线
        P q1 = p1.add(p2).mul(0.5);
        P q2 = q1.add(p1.sub(p2).rot90());
        P s1 = p3.add(p2).mul(0.5);
        P s2 = s1.add(p3.sub(p2).rot90());
        return Line.isLL(q1, q2, s1, s2);
    }

    /**
     * 直线和圆的交点
     */
    public static P[] isCL(P c, double r, P p1, P p2) {
        double x = p1.sub(c).dot(p2.sub(p1));
        double y = p2.sub(p1).abs2();
        double d = Geo.add(x * x, -y * (Geo.add(p1.sub(c).abs2(), -r * r)));
        if (d < 0) return new P[0];
        P q1 = p1.sub(p2.sub(p1).mul(x / y));
        P q2 = p2.sub(p1).mul(Math.sqrt(d) / y);
        return new P[]{q1.sub(q2), q1.add(q2)};
    }

    /**
     * 两圆的交点
     */
    public static P[] isCC(P c1, double r1, P c2, double r2) {
        double x = c1.sub(c2).abs2();
        double y = (Geo.add(r1 * r1, -r2 * r2) / x + 1) / 2;
        double d = Geo.add(r1 * r1 / x, -y * y);
        if (d < 0) return new P[0];
        P q1 = c1.add(c2.sub(c1).mul(y));
        P q2 = c2.sub(c1).mul(Math.sqrt(d)).rot90();
        return new P[]{q1.sub(q2), q1.add(q2)};
    }

    /**
     * 点和圆的两个切点
     */
    public static P[] tanCP(P c, double r, P p) {
        double x = p.sub(c).abs2();
        double d = Geo.add(x, -r * r);
        if (d < 0) return new P[0];
        P q1 = p.sub(c).mul(r * r / x);
        P q2 = p.sub(c).mul(-r * Math.sqrt(d) / x).rot90();
        return new P[]{c.add(q1.sub(q2)), c.add(q1.add(q2))};
    }

    /**
     * 两圆的公切线,返回的是切点对
     */
    public static P[][] tanCC(P c1, double r1, P c2, double r2) {
        List<P[]> list = new ArrayList<>();
        if (Math.abs(Geo.add(r1, -r2)) <= 0) {
            P dir = c2.sub(c1);
            dir = dir.mul(r1 / dir.abs()).rot90();
            list.add(new P[]{c1.add(dir), c2.add(dir)});
            list.add(new P[]{c1.sub(dir), c2.sub(dir)});
        } else {
            P p = c1.mul(-r2).add(c2.mul(r1)).div(r1 - r2);
            P[] ps = tanCP(c1, r1, p);
            P[] qs = tanCP(c2, r2, p);
            for (int i = 0; i < ps.length && i < qs.length; i++) {
                list.add(new P[]{ps[i], qs[i]});
            }
        }
        P p = c1.mul(r2).add(c2.mul(r1)).div(r1 + r2);
        P[] ps = tanCP(c1, r1, p);
        P[] qs = tanCP(c2, r2, p);
        for (int i = 0; i < ps.length && i < qs.length; i++) {
            list.add(new P[]{ps[i], qs[i]});
        }
        return list.toArray(new P[0][]);
    }

    /**
     * 两圆公共部分的面积
     */
    public static double areaCC(P c1, double r1, P c2, double r2) {
        double d = c1.sub(c2).abs();
        if (Geo.cmp(r1 + r2, d) <= 0) return 0;
        if (Geo.cmp(d, Math.abs(r1 - r2)) <= 0) {
            double r = Math.min(r1, r2);
            return r * r * Math.PI;
        }
        double x = (d * d + r1 * r1 - r2 * r2) / (2 * d);
        double t1 = Math.acos(x / r1);
        double t2 = Math.acos((d - x) / r2);
        return r1 * r1 * t1 + r2 * r2 * t2 - d * r1 * Math.sin(t1);
    }

    /**
     * 以r为半径的圆O与三角形Op1p2的公共面积, O为坐标原点, 注意返回值可能为负
     */
    public static double areaCT(double r, P p1, P p2) {
        P[] qs = isCL(new P(0, 0), r, p1, p2);
        if (qs.length == 0) return r * r * Geo.rad(p1, p2) / 2;
        boolean b1 = Geo.cmp(p1.abs(), r) > 0, b2 = Geo.cmp(p2.abs(), r) > 0;
        if (b1 && b2) {
            if (p1.sub(qs[0]).dot(p2.sub(qs[0])) <= 0 &&
                    p1.sub(qs[1]).dot(p2.sub(qs[1])) <= 0) {
                return (r * r * (Geo.rad(p1, p2) - Geo.rad(qs[0], qs[1])) + qs[0].det(qs[1])) / 2;
            } else {
                return r * r * Geo.rad(p1, p2) / 2;
            }
        } else if (b1) {
            return (r * r * Geo.rad(p1, qs[0]) + qs[0].det(p2)) / 2;
        } else if (b2) {
            return (r * r * Geo.rad(qs[1], p2) + p1.det(qs[1])) / 2;
        } else {
            return p1.det(p2) / 2;
        }
    }
}

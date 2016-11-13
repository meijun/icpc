package lib.geo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Convex.
 * Created by meijun on 6/7/2016.
 */
public class Convex {
    /**
     * 凸包, 逆时针 不包含线上的点; 如果需要包含线上的点 将 <= 0 改成 < 0, 但是需要注意此时不能有重点
     */
    public static P[] hull(P[] ps) {
        int n = ps.length, k = 0;
        if (n <= 1) return ps;
        Arrays.sort(ps);
        P[] qs = new P[n * 2];
        for (int i = 0; i < n; qs[k++] = ps[i++]) {
            while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k--;
        }
        for (int i = n - 2, t = k; i >= 0; qs[k++] = ps[i--]) {
            while (k > t && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k--;
        }
        P[] res = new P[k - 1];
        System.arraycopy(qs, 0, res, 0, k - 1);
        return res;
    }

    public static P[] hullByAngle(P[] ps) {
        int n = ps.length, k = 0;
        if (n <= 1) return ps;
        for (int i = 1; i < n; i++) {
            if (ps[i].y < ps[0].y || ps[i].y == ps[0].y && ps[i].x < ps[0].x) {
                // swap(ps, 0, i);
                P tmp = ps[0]; ps[0] = ps[i]; ps[i] = tmp;
            }
        }
        Arrays.sort(ps, 1, n, new CmpByAngle(ps[0]));
        P[] qs = new P[n];
        for (int i = 0; i < n; qs[k++] = ps[i++]) {
            while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k--;
        }
        return Arrays.copyOf(qs, k);
    }

    /**
     * 凸多边形的切断, 返回 p1p2 左侧凸包
     * <p>O(n)</p>
     */
    public static P[] cut(P[] ps, P p1, P p2) {
        int n = ps.length;
        ArrayList<P> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int d1 = Geo.signum(p2.sub(p1).det(ps[i].sub(p1)));
            int d2 = Geo.signum(p2.sub(p1).det(ps[(i + 1) % n].sub(p1)));
            if (d1 >= 0) res.add(ps[i]);
            if (d1 * d2 < 0) res.add(Line.isLL(p1, p2, ps[i], ps[(i + 1) % n]));
        }
        return res.toArray(new P[0]);
    }

    /**
     * 凸多边形的直径，凸多边形上最远点的距离
     * <p>O(n)</p>
     */
    public static double diameter(P[] ps) {
        int n = ps.length;
        int is = 0, js = 0;
        for (int i = 1; i < n; i++) {
            if (ps[i].x > ps[is].x) is = i;
            if (ps[i].x < ps[js].x) js = i;
        }
        double maxD = ps[is].sub(ps[js]).abs();
        int i = is, j = js;
        do {
            if (ps[(i + 1) % n].sub(ps[i]).det(ps[(j + 1) % n].sub(ps[j])) >= 0) {
                j = (j + 1) % n;
            } else {
                i = (i + 1) % n;
            }
            maxD = Math.max(maxD, ps[i].sub(ps[j]).abs());
        } while (i != is || j != js);
        return maxD;
    }

    /**
     * 凸多边形与外部点的距离
     */
    public static double disCvP(P[] ps, P q) {
        int n = ps.length;
        int left = 0, right = n;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (in(ps[(left + n - 1) % n], ps[left], ps[mid], ps[(mid + 1) % n], q)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return Line.disSP(ps[left], ps[right % n], q);
    }

    private static boolean in(P p1, P p2, P p3, P p4, P q) {
        P o12 = p1.sub(p2).rot90();
        P o23 = p2.sub(p3).rot90();
        P o34 = p3.sub(p4).rot90();
        return in(o12, o23, q.sub(p2)) || in(o23, o34, q.sub(p3))
                || in(o23, p3.sub(p2), q.sub(p2)) && in(p2.sub(p3), o23, q.sub(p3));
    }

    private static boolean in(P p1, P p2, P q) {
        return p1.det(q) >= 0 && p2.det(q) <= 0;
    }

    /**
     * 按相对于 p0 的极角逆时针排序; 角度相同，则离 p0 距离更近的放在前面
     */
    public static class CmpByAngle implements Comparator<P> {
        P p0;

        CmpByAngle(P p0) {
            this.p0 = p0;
        }

        @Override
        public int compare(P o1, P o2) {
            double det = o1.sub(p0).det(o2.sub(p0));
            if (det != 0) return det > 0 ? -1 : 1;
            double dis = Geo.add(o1.sub(p0).abs2(), -o2.sub(p0).abs2());
            if (dis != 0) return dis > 0 ? 1 : -1;
            return 0;
        }
    }
}

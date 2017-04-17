package main;

import lib.geo.Circle;
import lib.geo.Line;
import lib.geo.P;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        double res = 1e111;
        for (int i = 0; i < n; i++) {
//            res = Math.min(res, minDis(ps[i], ps[(i + 1) % n], ps[(i + 2) % n], res));
            res = Math.min(res, Line.disLP(ps[i], ps[(i + 2) % n], ps[(i + 1) % n]) / 2);
        }
        out.printf("%.10f%n", res);
    }

    private double minDis(P p1, P p2, P p3, double r) {
        double l = 0;
        double pr = p2.sub(p1).det(p3.sub(p2));
        for (int i = 0; i < 100; i++) {
            double m = (l + r) / 2;
            List<P> p1s = new ArrayList<>();
            List<P> p2s = new ArrayList<>();
            List<P> p3s = new ArrayList<>();
            tan(p1, p2, m, p1s, p2s);
            tan(p2, p3, m, p2s, p3s);
            tan(p1, p3, m, p1s, p3s);
            boolean crs = false;
            outer: for (P q1 : p1s) {
                for (P q2 : p2s) {
                    for (P q3 : p3s) {
                        if (q2.sub(q1).det(q3.sub(q2)) * pr <= 0) {
                            crs = true;
                            break outer;
                        }
                    }
                }
            }
            if (crs) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    private void tan(P p1, P p2, double m, List<P> p1s, List<P> p2s) {
        P[][] ps = Circle.tanCC(p1, m, p2, m);
        for (P[] le : ps) {
            p1s.add(le[0]);
            p2s.add(le[1]);
        }
    }
}

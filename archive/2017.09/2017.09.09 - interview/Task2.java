package main;

import lib.geo.Line;
import lib.geo.P;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task2 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            ys[i] = in.nextInt();
        }
        int nn = 0;
        int[] ws = new int[n];
        for (int i = 0; i < n; i++) {
            boolean found = false;
            for (int j = 0; j < nn; j++) {
                if (xs[j] == xs[i] && ys[j] == ys[i]) {
                    ws[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                xs[nn] = xs[i];
                ys[nn] = ys[i];
                ws[nn] = 1;
                nn++;
            }
        }
        int nbk = n;
        n = nn;
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(xs[i], ys[i]);
        }

        if (n <= 3 || inOneLine(n, ps)) {
            out.println(nbk);
            return ;
        }
        int res = 0;
        for (int x0 = 0; x0 < n; x0++) {
            for (int x1 = x0 + 1; x1 < n; x1++) {
                P px0 = ps[x0];
                P px1 = ps[x1];
                for (int y0 = 0; y0 < n; y0++) {
                    if (y0 != x0 && y0 != x1) {
                        P py0 = ps[y0];
                        P py1 = Line.proj(px0, px1, py0);
                        if (!py0.equals(py1)) {
                            res = Math.max(res, calc(px0, px1, py0, py1, n, ws, ps));
                        }
                    }
                }
            }
        }
        out.println(res);
    }

    private int calc(P px0, P px1, P py0, P py1, int n, int[] ws, P[] ps) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (Line.disLP(px0, px1, ps[i]) == 0 || Line.disLP(py0, py1, ps[i]) == 0) {
                res += ws[i];
            }
        }
        return res;
    }

    private boolean inOneLine(int n, P[] ps) {
        for (P p : ps) {
            if (Line.disLP(ps[0], ps[1], p) != 0) {
                return false;
            }
        }
        return true;
    }
}

package main;

import lib.graph.SCC;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            $.debug(i);
            out.printf("Case #%d: ", i);
            solve(in, out);
        }

    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] sp = new int[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                sp[i][j] = in.nextInt();
            }
        }
        int l = 1;
        int r = (int) (1e9);
//        $.debug(fit(n, sp, 4));
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(n, sp, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        out.println(l);
    }

    private boolean fit(int n, int[][] sp, int e) {
        SCC.V[] vs = new SCC.V[n * 2];
        for (int i = 0; i < vs.length; i++) vs[i] = new SCC.V();
        for (int i = 0; i < n; i++) {
            if (dis(sp[i], sp[i]) > e) return false;
            for (int j = i + 1; j < n; j++) {
                if (dis(sp[i], sp[j]) > e) {
                    vs[i * 2].add(vs[j * 2 + 1]);
                    vs[i * 2 + 1].add(vs[j * 2]);
                    vs[j * 2].add(vs[i * 2 + 1]);
                    vs[j * 2 + 1].add(vs[i * 2]);
//                    $.debug(i, j);
                }
            }
        }
        SCC.scc(vs);
        for (int i = 0; i < n; i++) {
            if (vs[i * 2].comp == vs[i * 2 + 1].comp) {
                return false;
            }
        }
        return true;
    }

    private int dis(int[] a, int[] b) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            res = Math.max(res, Math.max(a[i] + a[3], b[i] + b[3]) - Math.min(a[i] - a[3], b[i] - b[3]));
        }
        return res;
    }

    private void solveSmall(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] sp = new int[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                sp[i][j] = in.nextInt();
            }
        }
        int res = Integer.MAX_VALUE;
        for (int s = 0; s < (1 << (n-1)); s++) {
            res = Math.min(res, Math.max(calc(n, sp, s), calc(n, sp, ~s)));
        }
        out.println(res);
    }

    private int calc(int n, int[][] sp, int s) {
        int c = 0;
        for (int i = 0; i < n; i++) {
            if ((s & (1 << i)) != 0) {
                c++;
            }
        }
        if (c == 0) return 0;

        int[] min = new int[3];
        int[] max = new int[3];
        Arrays.fill(min, Integer.MAX_VALUE);
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            if ((s & (1 << i)) != 0) {
                for (int j = 0; j < 3; j++) {
                    min[j] = Math.min(min[j], sp[i][j] - sp[i][3]);
                    max[j] = Math.max(max[j], sp[i][j] + sp[i][3]);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            res = Math.max(res, max[i] - min[i]);
        }
        return res;
    }
}

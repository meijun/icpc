package main;

import lib.math.FFT;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[] c = new int[n];
        T[] ts = new T[n];
        for (int i = 0; i < n; i++) {
            ts[i] = new T();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            ts[u].add(v);
            ts[v].add(u);
        }
        int cp = 0;
        Arrays.fill(c, -1);
        for (int i = 0; i < n; i++) {
            if (c[i] == -1) {
                dfs(i, -1, ts, c, cp++);
            }
        }
        int[] dep = new int[n];
        int[] lef = new int[n];
        int[] rig = new int[n];
        Arrays.fill(dep, -1);
        for (int i = 0; i < n; i++) {
            if (dep[i] == -1) {
                dfs1(i, -1, ts, dep);
                dfs2(i, -1, ts, dep, lef, rig, -1);
            }
        }
        double[][] cnt = new double[cp][];
        int[] max = new int[cp];
        for (int i = 0; i < n; i++) {
            max[c[i]] = Math.max(max[c[i]], dep[i]);
        }
        for (int i = 0; i < cp; i++) {
            cnt[i] = new double[max[i] + 1];
        }
        int[][] num = new int[n][];
        for (int i = 0; i < n; i++) {
            cnt[c[i]][dep[i]]++;
        }
        for (int i = 0; i < cp; i++) {
            num[i] = new int[cnt[i].length];
            for (int j = 0; j < num[i].length; j++) {
                num[i][j] = (int) (cnt[i][j] + .5) + (j > 0 ? num[i][j - 1] : 0);
            }
        }
        Map<Long, Double> g = new HashMap<>();
//        $.debug(c);
        while (q-- > 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            if (c[u] == c[v]) {
                out.println(-1);
            } else {
                int cu = c[u];
                int cv = c[v];
                if (cu < cv) { int t = cu; cu = cv; cv = t; }
                out.printf("%.10f%n", calc(cnt, cu, cv, num, g));
            }
        }
    }

    private double calc(double[][] cnt, int u, int v, int[][] num, Map<Long, Double> g) {
        long key = (((long) u) << 32) | v;
        Double res = g.get(key);
        if (res != null) return res;
        if (500 > Math.min(cnt[u].length, cnt[v].length)) {
            if (cnt[u].length < cnt[v].length) { int t = u; u = v; v = t; }
            double[] a = cnt[u], b = cnt[v];
            long up = 0;
            long dn = 0;
            for (int j = 0; j < b.length; j++) {
                for (int k = 0; k < b.length; k++) {
                    int i = a.length - b.length + k;
                    int l = Math.max(a.length - 1, i + j + 1);
//                    $.debug(a.length, b.length, i, j);
                    up += l * (long) (a[i] + .5) * (long) (b[j] + .5);
                }
            }
            if (a.length - b.length - 1 >= 0) {
                up += (long) num[u][a.length - b.length - 1] * num[v][num[v].length - 1] * (a.length - 1);
            }
            dn = (long) num[u][num[u].length-1] * num[v][num[v].length-1];
//            $.debug(up, dn);
            res = (double) up / dn;
        } else {
            res = fft(cnt[u], cnt[v]);
        }
        g.put(key, res);
        return res;
    }

    private Double fft(double[] doubles, double[] doubles1) {
        Double res;
        double[] a = doubles, b = doubles1;
        double[] r = FFT.mul(a, b);
        int m = Math.max(a.length - 1, b.length - 1);
        long up = 0;
        long dn = 0;
        for (int i = 0; i < r.length; i++) {
            int d = Math.max(i+1, m);
            long ri = (long) (r[i] + 0.5);
            up += ri * d;
            dn += ri;
        }
//        $.debug(up, dn);
        res = (double) up / dn;
        return res;
    }

    private void dfs2(int u, int fa, T[] ts, int[] dep, int[] lef, int[] rig, int hi) {
        dep[u] = Math.max(dep[u], hi + 1);
        int last = -1;
        for (int v : ts[u]) {
            if (v !=fa) {
                lef[v] = Math.max(last == -1 ? -1 : lef[last], dep[v]);
                last = v;
            }
        }
        last = -1;
        for (int i = ts[u].size() - 1; i >= 0; i--) {
            int v = ts[u].get(i);
            if (v != fa) {
                rig[v] = Math.max(last == -1 ? -1 : rig[last], dep[v]);
                last = v;
            }
        }
        for (int i = 0; i < ts[u].size(); i++) {
            int v = ts[u].get(i);
            if (v != fa) {
                int pre = (i-1 >= 0 && ts[u].get(i-1) != fa ? i-1 : i-2 >= 0 ? i-2 : -1);
                int nxt = (i+1 < ts[u].size() && ts[u].get(i+1) != fa ? i+1 : i+2 < ts[u].size() ? i+2 : -1);
                int dl = (pre != -1 ? lef[ts[u].get(pre)] : -1);
                int dr = (nxt != -1 ? rig[ts[u].get(nxt)] : -1);
                int lr = Math.max(dl, dr);
                dfs2(v, u, ts, dep, lef, rig, Math.max(hi + 1, lr + 1));
            }
        }
    }

    private void dfs1(int u, int fa, T[] ts, int[] dep) {
        for (int v : ts[u]) {
            if (v != fa) {
                dfs1(v, u, ts, dep);
                dep[u] = Math.max(dep[u], dep[v]);
            }
        }
        dep[u]++;
    }

    private void dfs(int u, int fa, T[] ts, int[] c, int cl) {
        c[u] = cl;
        for (int v : ts[u]) {
            if (v != fa) {
                dfs(v, u, ts, c, cl);
            }
        }
    }

    class T extends ArrayList<Integer> {

    }

}

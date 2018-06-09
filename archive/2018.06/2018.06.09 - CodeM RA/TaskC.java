package main;

import lib.ds.LCA;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        List<Integer>[] vs = new List[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new ArrayList<>();
        }
        long res = 0;
        long M = (long) (1e9 + 7);
        int[] eu = new int[n - 1];
        int[] ev = new int[n - 1];
        int[] ew = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            int x = in.nextInt();
            res += (long) w * ((x + 1) / 2 * 2) % M;
            eu[i] = u;
            ev[i] = v;
            ew[i] = (x % 2 == 1 ? -w : w);
            vs[u].add(v);
            vs[v].add(u);
        }
        res %= M;
        LCA lca = new LCA(vs, 0);
        long[] wr = new long[n];
        for (int i = 0; i < n - 1; i++) {
            int u = eu[i];
            int v = ev[i];
            if (lca.depth[u] > lca.depth[v]) v = u;
            wr[v] = ew[i];
        }
        dfs(vs, 0, -1, wr);
        for (int i = 0; i < n; i++) wr[i] %= M;
        int m = in.nextInt();
        while (m-- > 0) {
            int s = in.nextInt() - 1;
            int t = in.nextInt() - 1;
            int l = lca.lca(s, t);
            long r = res;
            r += wr[s];
            r += wr[t];
            r -= wr[l] * 2;
            r %= M;
            r += M;
            r %= M;
            out.println(r);
        }
    }

    private void dfs(List<Integer>[] vs, int u, int p, long[] wr) {
        for (int v : vs[u]) {
            if (v != p) {
                wr[v] += wr[u];
                dfs(vs, v, u, wr);
            }
        }
    }
}

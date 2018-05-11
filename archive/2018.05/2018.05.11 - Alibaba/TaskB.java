package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(vs[v]);
        }
        long res = 0;
        for (V v : vs) {
            res = Math.max(res, dfs(v));
        }
        out.println(res);
    }

    private long dfs(V v) {
        if (v.res != 0) return v.res;
        for (V u : v) {
            v.res += dfs(u) + 1;
        }
        return v.res;
    }

    class V extends ArrayList<V> {
        long res;
    }
}

package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int d = in.nextInt();
        in.nextDouble();
        V[] vs = new V[n];
        int[] fs = new int[m];
        int[] ts = new int[m];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            vs[v].add(u);
            fs[i] = u;
            ts[i] = v;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            set.clear();
            int u = fs[i];
            int v = ts[i];
            if (vs[u].size() > vs[v].size()) {
                int t = u; u = v; v = t;
            }
            set.addAll(vs[u]);
            int cnt = 0;
            for (int w : vs[v]) {
                if (set.contains(w)) cnt++;
                if (cnt == 2) {
                    out.println(1);
                    return ;
                }
            }
        }
        out.println(0);
    }

    class V extends ArrayList<Integer> {

    }
}

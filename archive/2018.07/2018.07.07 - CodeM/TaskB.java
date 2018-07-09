package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] us = new int[m];
        int[] vs = new int[m];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int uu = in.nextInt();
            int vv = in.nextInt();
            uu -= 1;
            vv -= 1;
            int u = uu - ans;
            int v = vv - ans;
            u = (u + n) % n;
            v = (v + n) % n;
            if (u == 0) u = n;
            if (v == 0) v = n;
            us[i] = u - 1;
            vs[i] = v - 1;
            ans = 1;
        }
        int l = 1;
        int r = m;
        while (l < r) {
            int mid = (l + r) / 2;
            if (one(n, m, us, vs, mid)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        for (int i = 0; i < m; i++) {
            out.println(i < l ? 1 : 0);
        }
    }

    private boolean one(int n, int m, int[] us, int[] vs, int mid) {
        int[] c = new int[n];
        V[] ves = new V[n];
        for (int i = 0; i < n; i++) {
            ves[i] = new V();
        }
        for (int i = 0; i <= mid; i++) {
            c[vs[i]] += 1;
            ves[us[i]].add(vs[i]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (c[i] == 0) q.add(i);
        int out = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            out++;
            for (int v : ves[u]) {
                c[v]--;
                if (c[v] == 0) {
                    q.add(v);
                }
            }
        }
        return out == n;
    }

    class V extends ArrayList<Integer> {

    }
}

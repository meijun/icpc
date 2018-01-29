package main;

import lib.graph.SCC;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        SCC.V[] vs = new SCC.V[n];
        for (int i = 0; i < n; i++) vs[i] = new SCC.V();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(vs[v]);
        }
        int k = SCC.scc(vs);
        if (k == n) {
            out.println("YES");
            return;
        }
        int mul = 0;
        int[] cnt = new int[k];
        for (SCC.V v : vs) cnt[v.comp]++;
        int ck = -1;
        for (int i = 0; i < k; i++) {
            int c = cnt[i];
            if (c > 1) {
                mul++;
                ck = i;
            }
        }
        Set<SCC.V> vk = new HashSet<>();
        for (SCC.V v : vs) if (v.comp == ck) vk.add(v);
        if (mul > 1) {
            out.println("NO");
            return;
        }
        SCC.V u1 = null, v1 = null;
        for (SCC.V u : vs) {
            if (u.comp == ck) {
                u1 = u;
                for (SCC.V v : u.fs) {
                    if (v.comp == ck) {
                        v1 = v;
                        break;
                    }
                }
                break;
            }
        }

        u1.fs.remove(v1);
        v1.rs.remove(u1);
        k = SCC.scc(vs);
        if (k == n) {
            out.println("YES");
            return ;
        }
        cnt = new int[k];
        for (SCC.V v : vs) cnt[v.comp]++;
        ck = -1;
        for (int i = 0; i < k; i++) {
            int c = cnt[i];
            if (c > 1) {
                mul++;
                ck = i;
            }
        }
        if (mul > 1) {
            out.println("NO2");
            return;
        }
        SCC.V u2 = null, v2 = null;
        for (SCC.V u : vs) {
            if (u.comp == ck && vk.contains(u)) {
                for (SCC.V v : u.fs) {
                    if (v.comp == ck && vk.contains(v)) {
                        u2 = u;
                        v2 = v;
                        break;
                    }
                }
                if (u2 != null) break;
            }
        }
        if (u2 == null) {
            out.println("NO");
            return;
        }
        u1.add(v1);
        u2.fs.remove(v2);
        v2.rs.remove(u2);
        k = SCC.scc(vs);
        if (k != n) {
            out.println("NO");
        } else {
            out.println("YES");
        }
    }
}

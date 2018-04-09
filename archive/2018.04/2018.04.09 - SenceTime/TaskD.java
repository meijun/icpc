package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(vs[v]);
            vs[v].add(vs[u]);
        }
        Queue<V> q = new ArrayDeque<>();
        for (V v : vs) {
            if (!v.vis) {
                v.vis = true;
                q.add(v);
                while (!q.isEmpty()) {
                    v = q.poll();
                    for (V u : v) {
                        u.fa = v;
                        if (!u.vis) {
                            u.vis = true;
                            q.add(u);
                            u.fa = v;
                            for (V w : u) {
                                if (w.vis && w.fa == v) {
                                    out.println(1);
                                    return ;
                                }
                            }
                        }
                    }
                }
            }
        }
        out.println(0);
    }

    class V extends ArrayList<V> {
        int id;
        boolean vis;
        V fa;

        public V(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "" + id;
        }
    }
}

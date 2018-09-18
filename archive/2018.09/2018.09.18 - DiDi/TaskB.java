package main;

import lib.geo.Line;
import lib.geo.P;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            solve(in, out);
            if (t > 0) out.println();
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        P[] ps = new P[n];
        P[] qs = new P[n];
        int idx = 0;
        UnionFindSet uf = new UnionFindSet(n);
        while (n-- > 0) {
            if (in.next().charAt(0) == 'T') {
                ps[idx] = new P(in.nextDouble(), in.nextDouble());
                qs[idx] = new P(in.nextDouble(), in.nextDouble());
                for (int i = 0; i < idx; i++) {
                    if (Line.crsSS(ps[i], qs[i], ps[idx], qs[idx])) {
                        if (uf.find(i) != uf.find(idx)) {
                            uf.union(i, idx);
                        }
                    }
                }
                idx++;
            } else {
                int qid = in.nextInt() - 1;
                out.println(uf.cnt[uf.find(qid)]);
            }
        }
    }

    class UnionFindSet {
        public int[] pre;
        public int[] cnt;

        public UnionFindSet(int n) {
            pre = new int[n];
            cnt = new int[n];
            for (int i = 0; i < n; i++) {
                pre[i] = i;
                cnt[i] = 1;
            }
        }

        public int find(int id) {
            if (pre[id] != id) {
                pre[id] = find(pre[id]);
            }
            return pre[id];
        }

        public void union(int u, int v) {
            cnt[find(v)] += cnt[find(u)];
            pre[find(u)] = find(v);
        }

        public boolean isSame(int u, int v) {
            return find(u) == find(v);
        }

        public boolean isRoot(int id) {
            return pre[id] == id;
        }
    }

}

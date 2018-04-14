package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class TaskG {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        E[] es = new E[m];
        for (int i = 0; i < m; i++) {
            es[i] = new E(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        Arrays.sort(es);
        UnionFindSet uf = new UnionFindSet(n, m);
        for (E e : es) {
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.update(e.u, e.v, e.w);
            }
        }
        for (int i = 0; i < n; i++) uf.find(i);
        long res = 0;
        BigInteger Res = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            res ^= (i + 1) * uf.sum[i];
            Res = Res.xor(BigInteger.valueOf(i + 1).multiply(BigInteger.valueOf(uf.sum[i])));
        }
        out.println(Res);
    }

    class UnionFindSet {
        int[] pre;
        long[] cnt;
        long[] sum;
        int id;

        public UnionFindSet(int n, int m) {
            pre = new int[n + m];
            cnt = new long[n + m];
            sum = new long[n + m];
            for (int i = 0; i < n + m; i++) {
                pre[i] = i;
                cnt[i] = (i < n ? 1 : 0);
                sum[i] = 0;
            }
            id = n;
        }

        void update(int u, int v, int w) {
            u = pre[u];
            v = pre[v];
            pre[u] = id;
            pre[v] = id;
            cnt[id] = cnt[u] + cnt[v];
            sum[u] = cnt[v] * w;
            sum[v] = cnt[u] * w;
            id++;
        }

        public int find(int id) {
            if (pre[id] != id) {
                find(pre[id]);
                sum[id] += sum[pre[id]];
                pre[id] = pre[pre[id]];
            }
            return pre[id];
        }

        public void union(int u, int v) {
            pre[find(u)] = find(v);
        }

        public boolean isSame(int u, int v) {
            return find(u) == find(v);
        }

        public boolean isRoot(int id) {
            return pre[id] == id;
        }
    }

    class E implements Comparable<E> {
        int u;
        int v;
        int w;

        public E(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(E o) {
            return Integer.compare(w, o.w);
        }
    }
}

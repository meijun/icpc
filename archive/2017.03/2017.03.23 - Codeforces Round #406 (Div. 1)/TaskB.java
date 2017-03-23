package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    private static final long INF = Long.MAX_VALUE / 4;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int s = in.nextInt() - 1;
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        Up up = new Up(vs);
        Dn dn = new Dn(vs);
        while (q-- > 0) {
            int t = in.nextInt();
            if (t == 1) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                long w = in.nextLong();
                vs[u].add(vs[v], w);
            } else {
                int v = in.nextInt() - 1;
                int l = in.nextInt() - 1;
                int r = in.nextInt();
                long w = in.nextLong();
                if (t == 2) { // Dn
                    dn.connect(l, r, v, w);
                } else { // Up
                    up.connect(l, r, v, w);
                }
            }
        }
        dijkstra(vs[s]);
        for (int i = 0; i < n; i++) {
            out.print((vs[i].min < INF ? vs[i].min : -1) + " ");
        }
    }
    public static void dijkstra(V s) {
        PriorityQueue<E> que = new PriorityQueue<>();
        s.min = 0;
        que.offer(new E(s, 0));
        while (!que.isEmpty()) {
            E crt = que.poll();
            if (crt.cost > crt.to.min) continue;
            for (E e : crt.to.es) {
                long dis = crt.cost + e.cost;
                if (dis < e.to.min) {
                    e.to.min = dis;
                    que.offer(new E(e.to, e.to.min));
                }
            }
        }
    }

    public static class V {
        public ArrayList<E> es = new ArrayList<>();
        public long min = INF;

        public void add(V to, long cost) {
            es.add(new E(to, cost));
        }
    }

    public static class E implements Comparable<E> {
        public V to;
        public long cost;

        public E(V to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return Long.compare(cost, o.cost);
        }
    }

    class Up {
        int N;
        V[] vs;

        public Up(V[] us) {
            int n = us.length;
            N = Integer.highestOneBit(n) << 1;
            vs = new V[N * 2];
            for (int i = 0; i < n; i++) {
                vs[i + N] = us[i];
            }
            for (int i = N - 1; i > 0; i--) {
                vs[i] = new V();
                if (vs[i * 2] != null) {
                    vs[i * 2].add(vs[i], 0);
                }
                if (vs[i * 2 + 1] != null) {
                    vs[i * 2 + 1].add(vs[i], 0);
                }
            }
        }

        public void connect(int l, int r, int v, long w) {
            if (l < r) update(1, 0, N, l, r, v, w);
        }

        private void update(int o, int L, int R, int l, int r, int v, long w) {
            if (l <= L && R <= r) {
                vs[o].add(vs[v + N], w);
            } else {
                int M = (L + R) >> 1;
                if (l < M) update(o << 1, L, M, l, r, v, w);
                if (r > M) update(o << 1 | 1, M, R, l, r, v, w);
            }
        }
    }
    class Dn {
        int N;
        V[] vs;

        public Dn(V[] us) {
            int n = us.length;
            N = Integer.highestOneBit(n) << 1;
            vs = new V[N * 2];
            for (int i = 0; i < n; i++) {
                vs[i + N] = us[i];
            }
            for (int i = N - 1; i > 0; i--) {
                vs[i] = new V();
                if (vs[i * 2] != null) {
                    vs[i].add(vs[i * 2], 0);
                }
                if (vs[i * 2 + 1] != null) {
                    vs[i].add(vs[i * 2 + 1], 0);
                }
            }
        }

        public void connect(int l, int r, int v, long w) {
            if (l < r) update(1, 0, N, l, r, v, w);
        }

        private void update(int o, int L, int R, int l, int r, int v, long w) {
            if (l <= L && R <= r) {
                vs[v + N].add(vs[o], w);
            } else {
                int M = (L + R) >> 1;
                if (l < M) update(o << 1, L, M, l, r, v, w);
                if (r > M) update(o << 1 | 1, M, R, l, r, v, w);
            }
        }
    }

}

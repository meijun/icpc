package io.github.meijun.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * min cost flow
 * Created by meijun on 6/15/2014.
 */
public class MinCostFlow {
    public static final int INF = Integer.MAX_VALUE / 4;

    public static int minCostFlow(V[] vs, V s, V t, int flow) {
        int res = 0;
        while (flow > 0) {
            for (V v : vs) v.min = INF;
            PriorityQueue<E> que = new PriorityQueue<>();
            s.min = 0;
            que.offer(new E(s, 0, 0));
            while (!que.isEmpty()) {
                E crt = que.poll();
                if (crt.cost == crt.to.min) {
                    for (E e : crt.to.es) {
                        int tmp = crt.cost + e.cost + crt.to.h - e.to.h;
                        if (e.cap > 0 && e.to.min > tmp) {
                            e.to.min = tmp;
                            e.to.prev = e;
                            que.offer(new E(e.to, 0, e.to.min));
                        }
                    }
                }
            }
            if (t.min == INF) return INF;
            int d = flow;
            for (E e = t.prev; e != null; e = e.rev.to.prev) {
                d = Math.min(d, e.cap);
            }
            for (E e = t.prev; e != null; e = e.rev.to.prev) {
                res += d * e.cost;
                e.cap -= d;
                e.rev.cap += d;
            }
            flow -= d;
            for (V v : vs) v.h += v.min;
        }
        return res;
    }

    public static int minCostFlow(V[] vs, V s, V t) {
        int res = 0;
        for (;;) {
            for (V v : vs) v.min = INF;
            PriorityQueue<E> que = new PriorityQueue<>();
            s.min = 0;
            que.offer(new E(s, 0, 0));
            while (!que.isEmpty()) {
                E crt = que.poll();
                if (crt.cost == crt.to.min) {
                    for (E e : crt.to.es) {
                        int tmp = crt.cost + e.cost + crt.to.h - e.to.h;
                        if (e.cap > 0 && e.to.min > tmp) {
                            e.to.min = tmp;
                            e.to.prev = e;
                            que.offer(new E(e.to, 0, e.to.min));
                        }
                    }
                }
            }
            if (t.min == INF) return res;
            int d = INF;
            for (E e = t.prev; e != null; e = e.rev.to.prev) {
                d = Math.min(d, e.cap);
            }
            for (E e = t.prev; e != null; e = e.rev.to.prev) {
                res += d * e.cost;
                e.cap -= d;
                e.rev.cap += d;
            }
            for (V v : vs) v.h += v.min;
        }
    }

    public static class V {
        public ArrayList<E> es = new ArrayList<>();
        public E prev;
        public int min;
        public int h;

        public void add(V to, int cap, int cost) {
            E e = new E(to, cap, cost), rev = new E(this, 0, -cost);
            e.rev = rev;
            rev.rev = e;
            es.add(e);
            to.es.add(rev);
        }
    }

    public static class E implements Comparable<E> {
        public V to;
        public E rev;
        public int cap;
        public int cost;

        public E(V to, int cap, int cost) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
        }

        public int compareTo(E o) {
            return cost - o.cost;
        }
    }
}

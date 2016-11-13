package lib.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Max flow.
 * Created by meijun on 6/27/2014.
 */
public class MaxFlow {
    private static int p = 0;

    public static int dinic(V s, V t) {
        int flow = 0;
        for (p++; ; p++) {
            Queue<V> que = new LinkedList<>();
            s.level = 0;
            s.p = p;
            que.offer(s);
            while (!que.isEmpty()) {
                V v = que.poll();
                v.iter = v.es.size() - 1;
                for (E e : v.es) {
                    if (e.to.p != p && e.cap > 0) {
                        e.to.level = v.level + 1;
                        e.to.p = p;
                        que.offer(e.to);
                    }
                }
            }
            if (t.p != p) return flow;
            for (int f; (f = dfs(s, t, Integer.MAX_VALUE)) > 0; ) flow += f;
        }
    }

    public static int dfs(V v, V t, int f) {
        if (v == t) return f;
        for (; v.iter >= 0; v.iter--) {
            E e = v.es.get(v.iter);
            if (v.level < e.to.level && e.cap > 0) {
                int d = dfs(e.to, t, Math.min(f, e.cap));
                if (d > 0) {
                    e.cap -= d;
                    e.rev.cap += d;
                    return d;
                }
            }
        }
        return 0;
    }

    public static class V {
        public ArrayList<E> es = new ArrayList<>();
        public int level;
        public int p;
        public int iter;

        public void add(V to, int cap) {
            E e = new E(to, cap), rev = new E(this, 0);
            e.rev = rev;
            rev.rev = e;
            es.add(e);
            to.es.add(rev);
        }
    }

    public static class E {
        public V to;
        public E rev;
        public int cap;

        public E(V to, int cap) {
            this.to = to;
            this.cap = cap;
        }
    }
}

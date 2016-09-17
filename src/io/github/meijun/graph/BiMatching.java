package io.github.meijun.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Bi-Matching
 * Created by meijun on 6/27/2014.
 */
public class BiMatching {

    public static int hungary(V[] vs) {
        int match = 0;
        for (V v : vs)
            if (v.pair == null) {
                for (V u : vs) u.used = false;
                if (dfs(v)) match++;
            }
        return match;
    }

    public static boolean dfs(V v) {
        v.used = true;
        for (V u : v.vs) {
            u.used = true;
            V w = u.pair;
            if (w == null || !w.used && dfs(w)) {
                v.pair = u;
                u.pair = v;
                return true;
            }
        }
        return false;
    }

    /**
     * O(E sqrt V)
     */
    public static int hopcroftKarp(V[] vs) {
        for (int match = 0; ; ) {
            Queue<V> que = new LinkedList<>();
            for (V v : vs) v.level = -1;
            for (V v : vs)
                if (v.pair == null) {
                    v.level = 0;
                    que.offer(v);
                }
            while (!que.isEmpty()) {
                V v = que.poll();
                for (V u : v.vs) {
                    V w = u.pair;
                    if (w != null && w.level < 0) {
                        w.level = v.level + 1;
                        que.offer(w);
                    }
                }
            }
            for (V v : vs) v.used = false;
            int d = 0;
            for (V v : vs) if (v.pair == null && dfs2(v)) d++;
            if (d == 0) return match;
            match += d;
        }
    }

    public static boolean dfs2(V v) {
        v.used = true;
        for (V u : v.vs) {
            V w = u.pair;
            if (w == null || !w.used && v.level < w.level && dfs2(w)) {
                v.pair = u;
                u.pair = v;
                return true;
            }
        }
        return false;
    }

    public static class V {
        public List<V> vs = new ArrayList<>();
        public V pair;
        public boolean used;
        public int level; // for hopcroft karp

        public void connect(V v) {
            vs.add(v);
            v.vs.add(this);
        }
    }
}

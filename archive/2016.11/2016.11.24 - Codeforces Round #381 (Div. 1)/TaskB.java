package main;

import lib.ds.LCA;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V(in.nextInt());
        }
        List<Integer>[] us = new List[n];
        for (int i = 0; i < n; i++) {
            us[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int p = in.nextInt() - 1;
            int w = in.nextInt();
            vs[p].es.add(new E(w, vs[i]));
            us[p].add(i);
        }
        dfs(vs[0], NULL);
        LCA lca = new LCA(us, 0);
        for (int i = 1; i < n; i++) {
            if (vs[i].cnt != 0) {
                int f = lca.pre[0][i];
                int g = vs[i].cnt == 1 ? f : lca.climb(f, vs[i].cnt - 1);
                vs[f].delta += 1;
                if (g != 0) vs[lca.pre[0][g]].delta -= 1;
            }
        }
        go(vs[0]);
        for (int i = 0; i < n; i++) {
            out.print(vs[i].res + " ");
        }
        out.println();
    }

    private void go(V v) {
        for (E e : v.es) {
            go(e.to);
            v.delta += e.to.delta;
        }
        v.res += v.delta;
    }

    void dfs(V v, T t) {
        T[] ts = splitSize(t, v.w);
        v.cnt = ts[0].s;
        t = merge(ts[0], ts[1]);
        for (E e : v.es) {
            T t0 = new T(e.w);
            t = merge(t0, t);
            dfs(e.to, t);
            ts = splitSize(t, e.w);
            t = ts[1];
        }
    }


    public static final T NULL = new T(0, 0, 0, null, null);

    public T[] splitSize(T t, long size) {
        T[] res;
        if (t == NULL) return new T[] {NULL, NULL};
        if (size <= 0) {
            res = new T[]{NULL, t};
        } else if (size < t.push().left.size + t.key) {
            res = splitSize(t.left, size);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitSize(t.right, size - t.left.size - t.key);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    public void print(T t, String indent) {
        if (t != NULL) {
            print(t.push().right, indent + "      ");
            System.err.printf("%s%3d%3d%n", indent, t.key, t.size);
            print(t.left, indent + "      ");
        }
        if (indent.length() == 0)
            System.err.println("----------------------------------");
    }

    public T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.push().change(t1.left, merge(t1.right, t2));
        return t2.push().change(merge(t1, t2.left), t2.right);
    }

    public static class T {
        public long key;
        public long size;
        public int s;
        public double p;
        public T left;
        public T right;

        public T(long key, long size, double p, T left, T right) {
            this.key = key;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        public T(long key) {
            this(key, key, Math.random(), NULL, NULL);
            s = 1;
        }

        public T change(T left, T right) {
            size = left.size + right.size + key;
            s = left.s + right.s + 1;
            this.left = left;
            this.right = right;
            return this;
        }

        public T push() {
            if (this != NULL) {

            }
            return this;
        }
    }

    class V {
        List<E> es = new ArrayList<>();
        int w;
        int cnt;
        int delta;
        long res;

        public V(int w) {
            this.w = w;
        }
    }
    class E {
        int w;
        V to;

        public E(int w, V to) {
            this.w = w;
            this.to = to;
        }
    }
}

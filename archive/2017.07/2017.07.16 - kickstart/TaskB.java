package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug("Case", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int a0 = in.nextInt();
        int b0 = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int e0 = in.nextInt();
        int e1 = in.nextInt();
        int f = in.nextInt();
        int x = a0, y = b0, r = 0, s = 0;
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (r % 2 == 0 ? 1 : -1) * x;
            b[i] = (s % 2 == 0 ? 1 : -1) * y;
            int x_ = (c * x + d * y + e0) % f;
            int y_ = (d * x + c * y + e1) % f;
            int r_ = (c * r + d * s + e0) % 2;
            int s_ = (d * r + c * s + e1) % 2;
            x = x_;
            y = y_;
            r = r_;
            s = s_;
        }
        int[] as, bs;
        if ((long) n * (n + 1) / 2 <= k * 2) {
            as = sum(a, k);
            bs = sum(b, k);
        } else {
            as = sum2(a, k);
            bs = sum2(b, k);
        }
//        $.debug(k, a);
//        $.debug(sum(a, k));
//        $.debug(sum2(a, k));
        PriorityQueue<Item> que = new PriorityQueue<Item>();
        Set<Pair> set = new HashSet<>();
        int ai = 0, aj = as.length - 1;
        int bi = 0, bj = bs.length - 1;
        push(as, bs, que, set, ai, bi);
        push(as, bs, que, set, aj, bj);
        while (!que.isEmpty()) {
            k--;
            Item it = que.poll();
            if (k == 0) {
                out.println(it.v);
                break;
            }
            ai = it.ai;
            bi = it.bi;
            if (ai + 1 < as.length) push(as, bs, que, set, ai + 1, bi);
            if (ai - 1 >= 0) push(as, bs, que, set, ai - 1, bi);
            if (bi + 1 < bs.length) push(as, bs, que, set, ai, bi + 1);
            if (bi - 1 >= 0) push(as, bs, que, set, ai, bi - 1);
        }
    }

    private void push(int[] as, int[] bs, PriorityQueue<Item> que, Set<Pair> set, int ai, int bi) {
        if (!set.contains(new Pair(ai, bi))) {
            que.add(new Item((long)as[ai] * bs[bi], ai, bi));
            set.add(new Pair(ai, bi));
        }
    }

    class Pair {
        int ai;
        int bi;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (ai != pair.ai) return false;
            return bi == pair.bi;
        }

        @Override
        public int hashCode() {
            int result = ai;
            result = 31 * result + bi;
            return result;
        }

        public Pair(int ai, int bi) {

            this.ai = ai;
            this.bi = bi;
        }
    }
    class Item implements Comparable<Item> {
        long v;
        int ai;
        int bi;

        public Item(long v, int ai, int bi) {
            this.v = v;
            this.ai = ai;
            this.bi = bi;
        }

        @Override
        public int compareTo(Item o) {
            return -Long.compare(v, o.v);
        }
    }

    private int[] sum(int[] a, int k) {
        int n = a.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + a[i];
        }
        int[] s = new int[n * (n + 1) / 2];
        int u = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                s[u++] = pre[j] - pre[i];
            }
        }
        Arrays.sort(s);
        return s;
    }

    private int[] sum2(int[] a, int k) {
        int n = a.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + a[i];
        }
        int min = (int) (-1e8 - 1000);
        int max = (int) (1e8 + 1000);
        int l = min, r = max;
        while (l < r - 5) {
            int m = l + (r - l) / 2;
            long cnt = count(pre, m);
            if (cnt >= k) l = m;
            else r = m;
        }
        while (count(pre, l) >= k) l++;
        int max_k = l - 1;
        l = min; r = max;
        while (l < r - 5) {
            int m = l + (r - l) / 2;
            long cnt = count2(pre, m);
            if (cnt >= k) r = m;
            else l = m;
        }
        while (count2(pre, r) >= k) r--;
        int min_k = r + 1;
        List<Integer> l_max = maxNumber(pre, max_k);
        List<Integer> l_min = minNumber(pre, min_k);
        while (l_max.size() < k) l_max.add(max_k);
        while (l_min.size() < k) l_min.add(min_k);
        List<Integer> ls = new ArrayList<>();
        ls.addAll(l_max);
        ls.addAll(l_min);
        int[] res = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            res[i] = ls.get(i);
        }
        Arrays.sort(res);
        return res;
    }
    List<Integer> minNumber(int[] pre, int min_k) {
        T t = NULL;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < pre.length; i++) {
            if (i > 0) {
                T[] ts = splitKey(t, pre[i] - min_k);
                add(res, ts[1], pre[i]);
                t = merge(ts[0], ts[1]);
            }
            T[] ts = splitKey(t, pre[i]);
            t = merge(merge(ts[0], new T(pre[i])), ts[1]);
        }
        return res;
    }
    List<Integer> maxNumber(int[] pre, int max_k) {
        T t = NULL;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < pre.length; i++) {
            if (i > 0) {
                T[] ts = splitKey(t, pre[i] - max_k - 1);
                add(res, ts[0], pre[i]);
                t = merge(ts[0], ts[1]);
            }
            T[] ts = splitKey(t, pre[i]);
            t = merge(merge(ts[0], new T(pre[i])), ts[1]);
        }
        return res;
    }

    private void add(List<Integer> res, T t, int pre) {
        if (t != NULL) {
            add(res, t.left, pre);
            res.add(pre - t.key);
            add(res, t.right, pre);
        }
    }

    private long count(int[] pre, int m) {
        T t = NULL;
        long res = 0;
        for (int i = 0; i < pre.length; i++) {
            if (i > 0) {
                T[] ts = splitKey(t, pre[i] - m);
                res += ts[0].size;
                t = merge(ts[0], ts[1]);
            }
            T[] ts = splitKey(t, pre[i]);
            t = merge(merge(ts[0], new T(pre[i])), ts[1]);
        }
        return res;
    }
    private long count2(int[] pre, int m) {
        T t = NULL;
        long res = 0;
        for (int i = 0; i < pre.length; i++) {
            if (i > 0) {
                T[] ts = splitKey(t, pre[i] - m - 1);
                res += ts[1].size;
                t = merge(ts[0], ts[1]);
            }
            T[] ts = splitKey(t, pre[i]);
            t = merge(merge(ts[0], new T(pre[i])), ts[1]);
        }
        return res;
    }
    public static final T NULL = new T(0, 0, 0, null, null);

    public T[] splitSize(T t, int size) {
        T[] res;
        if (size <= 0) {
            res = new T[]{NULL, t};
        } else if (size <= t.push().left.size) {
            res = splitSize(t.left, size);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitSize(t.right, size - t.left.size - 1);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    public T[] splitKey(T t, int key) {
        T[] res;
        if (t == NULL) {
            res = new T[]{NULL, NULL};
        } else if (key < t.push().key) {
            res = splitKey(t.left, key);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitKey(t.right, key);
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
        public int key;
        public int size;
        public double p;
        public T left;
        public T right;

        public T(int key, int size, double p, T left, T right) {
            this.key = key;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        public T(int key) {
            this(key, 1, Math.random(), NULL, NULL);
        }

        public T change(T left, T right) {
            size = left.size + right.size + 1;
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

}

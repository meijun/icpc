package main;

import lib.ds.seg.SegLite;
import lib.ds.seg.SegLiteMin;
import lib.ds.seg.SegMax;
import lib.ds.seg.SegMin;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Map<Integer, Item> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int h = in.nextInt();
            Item it = map.get(b);
            if (it == null) {
                it = new Item(a, b, 0);
                map.put(b, it);
            }
            it.h += h;
            it.a = Math.min(it.a, a);
        }
        Item[] is = map.values().toArray(new Item[0]);
        Arrays.sort(is);
        n = is.length;
//        long[] dp = new long[n];
        long max = 0;
        SegMax seg = new SegMax(n);
        for (int i = 0; i < n; i++) {
//            dp[i] = is[i].h;
//            for (int j = 0; j < i; j++) {
//                if (is[j].b > is[i].a) {
//                    dp[i] = Math.max(dp[i], dp[j] + is[i].h);
//                }
//            }
            int bb = upperBound(is, 0, i, is[i].a);
            long dpi = (bb == i ? 0 : seg.query(bb, i)) + is[i].h;
            seg.update(i, i + 1, 0, dpi);
            max = Math.max(max, dpi);
        }
        out.println(max);

    }

    int upperBound(Item[] a, int l, int r, int v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (a[m].b > v) r = m;
            else l = m + 1;
        }
        return l;
    }

    class Item implements Comparable<Item> {
        int a;
        int b;
        long h;

        public Item(int a, int b, long h) {
            this.a = a;
            this.b = b;
            this.h = h;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(b, o.b);
        }
    }
}

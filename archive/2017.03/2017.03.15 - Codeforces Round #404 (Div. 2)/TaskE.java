package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] is = new int[n];
        Seg seg = new Seg(n);
        for (int i = 0; i < n; i++) {
            is[i] = i;
        }
        long all = 0;
        while (q-- > 0) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            if (l > r) {
                int t = l; l = r; r = t;
            }
            if (l == r) {
                out.println(all);
            } else {
                if (l + 1 == r) {
                    if (is[l] < is[r]) {
                        all++;
                        out.println(all);
                    } else {
                        all--;
                        out.println(all);
                    }
                } else {
                    int less_l = seg.query(l + 1, r, is[l]);
                    int greater_l = (r - l - 1) - less_l;
                    int less_r = seg.query(l + 1, r, is[r]);
                    int greater_r = (r - l - 1) - less_r;
                    int delta =  greater_l - less_l + less_r - greater_r;
                    delta += (is[l] < is[r] ? 1 : -1);
                    all += delta;
                    out.println(all);
                }
                seg.update(l, is[r]);
                seg.update(r, is[l]);
                int t = is[l]; is[l] = is[r]; is[r] = t;
            }
        }
    }
    class Seg {
        int N;
        int[][] iss;
        boolean[] dirty;
        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            iss = new int[N * 2][];
            dirty = new boolean[N * 2];
            for (int i = 0; i < N; i++) {
                iss[i + N] = new int[1];
                iss[i + N][0] = i;
            }
            for (int i = N - 1; i > 0; i--) {
                iss[i] = new int[iss[i * 2].length * 2];
                for (int j = 0; j < iss[i].length; j++) {
                    iss[i][j] = iss[i * 2][0] + j;
                }
            }
        }
        void update(int k, int x) {
            k += N;
            iss[k][0] = x;
            for (k >>= 1; k > 0; k >>= 1) {
                dirty[k] = true;
//                $.debug("->", iss[k * 2], iss[k * 2 + 1], iss[k]);
            }
        }
        void realUpdate(int k) {
            if (dirty[k * 2]) realUpdate(k * 2);
            if (dirty[k * 2 + 1]) realUpdate(k * 2 + 1);
            for (int i = 0, j = 0; i + j < iss[k].length; ) {
                if (i < iss[k * 2].length && (j == iss[k * 2].length || iss[k * 2][i] < iss[k * 2 + 1][j])) {
                    iss[k][i + j] = iss[k * 2][i];
                    i++;
                } else {
                    iss[k][i + j] = iss[k * 2 + 1][j];
                    j++;
                }
            }
            dirty[k] = false;
        }
        int query(int s, int t, int x) {
//            $.debug(s, t, x, iss);
            int res = 0;
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
                res += qr(i, x);
                s += s & -s;
            }
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
                res += qr(i, x);
                t -= t & -t;
            }
//            $.debug(res);
            return res;
        }

        private int qr(int i, int x) {
            if (dirty[i]) realUpdate(i);
            return $.lowerBound(iss[i], x);
        }
    }
}

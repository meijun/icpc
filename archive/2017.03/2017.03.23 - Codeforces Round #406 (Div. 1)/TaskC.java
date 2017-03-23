package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    private int n;
    private int[] is;
    private int[] dp;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt() - 1;
        }

        vis = new int[n];
        dp = new int[n + 1];
//        for (int i = 1; i <= n; i++) {
//            $.debug(i, seg(i));
//        }
        for (int i = n; i >= 1; i--) {
            int l = 1, r = i;
            dp[i] = seg(i);
            while (l < r) {
                int m = (l + r) / 2;
//                $.debug(i, l, r, m, seg(m));
                if (seg(m) > dp[i]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            i = l;
        }
        int last = n;
        for (int i = 1; i <= n; i++) {
            if (dp[i] != 0) {
                last = dp[i];
            }
            out.print(last + " ");
        }
    }
    int[] vis;
    int p = 1;

    private int seg(int c) {
        if (dp[c] != 0) return dp[c];
        int cnt = 0;
        int res = 0;
        p++;
        for (int i = 0; i < n; i++) {
            if (vis[is[i]] != p) {
                cnt++;
                if (cnt == c + 1) {
                    cnt = 1;
                    p++;
                    res++;
                }
            }
            vis[is[i]] = p;
//            $.debug(i, cnt, res);
        }
        if (cnt != 0) res++;
        return dp[c] = res;
    }
}

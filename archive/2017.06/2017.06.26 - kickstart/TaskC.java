package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        long[] ans = new long[n];
        long my = 0;
        for (int i = 0; i < n + 1; i++) {
            long a = 0;
            String s = in.next();
            for (int j = 0; j < q; j++) {
                if (s.charAt(j) == 'T') {
                    a |= (1L << j);
                }
            }
            if (i < n) {
                ans[i] = a;
            } else {
                my = a;
            }
        }
        int[] sc = new int[n];
        for (int i = 0; i < n; i++) sc[i] = in.nextInt();
        if (n == 1) {
            int[][] dp = new int[q + 1][q + 1];
            $.fill(dp, -1);
            dp[0][0] = 0;
            for (int i = 0; i < q; i++) {
                for (int j = 0; j <= q; j++)
                    if (dp[i][j] >= 0) {
                        for (long k = 0; k < 2; k++) {
                            int nj = j + ((ans[0] & (1L << i)) == (k << i) ? 1 : 0);
                            int rj = ((my & (1L << i)) == (k << i) ? 1 : 0);
//                    $.debug(i, j, k, nj, rj, ans[0], my);
                            dp[i + 1][nj] = Math.max(dp[i + 1][nj], dp[i][j] + rj);
                        }
                    }
//            $.debug(dp[i + 1]);
            }
            out.println(dp[q][sc[0]]);
        } else {
            int[][][] dp = new int[q + 1][q + 1][q + 1];
            $.fill(dp, -1);
            dp[0][0][0] = 0;
            for (int i = 0; i < q; i++) {
                for (int j = 0; j <= q; j++) {
                    for (int k = 0; k <= q; k++) {
                        if (dp[i][j][k] >= 0) {
                            for (long l = 0; l < 2; l++) {
                                int nj = j + ((ans[0] & (1L << i)) == (l << i) ? 1 : 0);
                                int nk = k + ((ans[1] & (1L << i)) == (l << i) ? 1 : 0);
                                int rj = ((my & (1L << i)) == (l << i) ? 1 : 0);
                                dp[i + 1][nj][nk] = Math.max(dp[i + 1][nj][nk], dp[i][j][k] + rj);
                            }
                        }
                    }
                }
            }
            out.println(dp[q][sc[0]][sc[1]]);
        }
//        int res = 0;
//        for (long s = 0; s < (1L << q); s++) {
//            boolean ok = true;
//            for (int i = 0; i < n; i++) {
//                int c = q - Long.bitCount(ans[i] ^ s);
//                if (c != sc[i]) { ok = false; break; }
//            }
//            if (ok) {
//                int c = q - Long.bitCount(my ^ s);
//                res = Math.max(res, c);
//            }
//        }
//        out.println(res);
    }
    private void solve2(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        long[] ans = new long[n];
        long my = 0;
        for (int i = 0; i < n + 1; i++) {
            long a = 0;
            String s = in.next();
            for (int j = 0; j < q; j++) {
                if (s.charAt(j) == 'T') {
                    a |= (1L << j);
                }
            }
            if (i < n) {
                ans[i] = a;
            } else {
                my = a;
            }
        }
        int[] sc = new int[n];
        for (int i = 0; i < n; i++) sc[i] = in.nextInt();
        int res = 0;
        for (long s = 0; s < (1L << q); s++) {
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                int c = q - Long.bitCount(ans[i] ^ s);
                if (c != sc[i]) { ok = false; break; }
            }
            if (ok) {
                int c = q - Long.bitCount(my ^ s);
                res = Math.max(res, c);
            }
        }
        out.println(res);
    }
}

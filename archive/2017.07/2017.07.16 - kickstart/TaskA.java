package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

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
        long ts = in.nextInt();
        long tf = in.nextInt();
        long[] s = new long[n];
        long[] f = new long[n];
        long[] d = new long[n];
        for (int i = 0; i < n - 1; i++) {
            s[i] = in.nextInt();
            f[i] = in.nextInt();
            d[i] = in.nextInt();
        }
        long[][] dp = new long[n][n];
        long INF = Long.MAX_VALUE / 4;
        $.fill(dp, INF);
        dp[0][0] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= i; j++) if (dp[i][j] < tf) {
                long x = (Math.max(0, dp[i][j] - s[i]) + f[i] - 1) / f[i];
                dp[i + 1][j] = Math.min(dp[i + 1][j], s[i] + f[i] * x + d[i]);
                x = (Math.max(0, dp[i][j] + ts - s[i]) + f[i] - 1) / f[i];
                dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], s[i] + f[i] * x + d[i]);
            }
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            if (dp[n - 1][i] <= tf) {
                res = i;
            }
        }
        out.println(res == -1 ? "IMPOSSIBLE" : res);
    }
}

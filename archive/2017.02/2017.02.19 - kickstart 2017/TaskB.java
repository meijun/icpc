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
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        double[][] dp = new double[n + m + 1][m + 1];
        $.fill(dp, Double.NEGATIVE_INFINITY);
        dp[0][0] = 0;
        for (int i = 0; i < n + m; i++) {
            for (int j = 0; j <= m; j++) {
                if (Double.isFinite(dp[i][j])) {
//                if (dp[i][j] > 0) {
                    if (j <= i + 1 && j >= i + 1 - n) dp[i + 1][j] = lse(dp[i + 1][j], dp[i][j]);
//                    if (j <= i + 1 && j >= i + 1 - n) dp[i + 1][j] += dp[i][j];
                    if (j + 1 <= i + 1 && j + 1 <= m && j + 1 >= i + 1 - n && (i + 1) - (j + 1) > j + 1) {
                        dp[i + 1][j + 1] = lse(dp[i + 1][j + 1], dp[i][j]);
//                        dp[i + 1][j + 1] += dp[i][j];
                    }
                }
            }
//            $.debug(dp[i]);
        }
//        $.debug(dp[n + m]);
        double ok = Double.NEGATIVE_INFINITY;
//        double ok = 0;
        for (int i = 0; i <= m; i++) {
            ok = lse(ok, dp[n + m][i]);
//            $.debug("ok", ok);
        }
//        for (int i = 0; i <= m; i++) ok += dp[n + m][i];
//        $.debug(ok);
        double f = 0;
        double nf = 0, mf = 0;
        for (int i = 1; i <= n + m; i++) {
            f += Math.log(i);
            if (i <= n) nf += Math.log(i);
            if (i <= m) mf += Math.log(i);
        }
//        $.debug(ok, nf, mf, f, Math.exp(ok + nf + mf - f));
        out.println(Math.exp(ok + nf + mf - f));
//        double f = 1;
//        double nf = 1, mf = 1;
//        for (int i = 1; i <= n + m; i++) {
//            f *= i;
//            if (i <= n) nf *= i;
//            if (i <= m) mf *= i;
//        }
//        out.println(ok * nf * mf / f);
    }

    private double lse(double a, double b) {
        double max = Math.max(a, b);
        if (Double.isInfinite(max)) return max;
        double sum = Math.exp(a - max) + Math.exp(b - max);
        double res = Math.log(sum) + max;
        return res;
    }
}

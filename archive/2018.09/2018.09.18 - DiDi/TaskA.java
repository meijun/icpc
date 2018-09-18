package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    private int[] ps;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int p = in.nextInt();
        int q = in.nextInt();
        int r = in.nextInt();
        if (p > q) {int t = p; p = q; q = t;}
        if (p > r) {int t = p; p = r; r = t;}
        if (q > r) {int t = q; q = r; r = t;}
        if (p + q < r - 1) {
            out.println(0);
            return ;
        }
//        ps = new int[p + q + r];
//        int res = dfs(0, p, q, r);
        long res = smart(p, q, r);
        out.println(res);
    }

    private long smart(int p, int q, int r) {
        long[][][][] dp = new long[p + 1][q + 1][r + 1][3];
        dp[1][0][0][0] = 1;
        dp[0][1][0][1] = 1;
        dp[0][0][1][2] = 1;
        for (int i = 0; i <= p; i++) {
            for (int j = 0; j <= q; j++) {
                for (int k = 0; k <= r; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (dp[i][j][k][l] > 0) {
                            for (int t = 0; t < 3; t++) {
                                if (t != l) {
                                    if (t == 0 && i < p) dp[i + 1][j][k][t] += dp[i][j][k][l];
                                    if (t == 1 && j < q) dp[i][j + 1][k][t] += dp[i][j][k][l];
                                    if (t == 2 && k < r) dp[i][j][k + 1][t] += dp[i][j][k][l];
                                }
                            }
                        }
                    }
                }
            }
        }
        return dp[p][q][r][0] + dp[p][q][r][1] + dp[p][q][r][2];
    }

    private int dfs(int i, int p, int q, int r) {
        if (i == ps.length) return 1;
        int res = 0;
        if (p > 0) {
            ps[i] = 0;
            if (i == 0 || ps[i - 1] != 0) res += dfs(i + 1, p - 1, q, r);
        }
        if (q > 0) {
            ps[i] = 1;
            if (i == 0 || ps[i - 1] != 1) res += dfs(i + 1, p, q - 1, r);
        }
        if (r > 0) {
            ps[i] = 2;
            if (i == 0 || ps[i - 1] != 2) res += dfs(i + 1, p, q, r - 1);
        }
        return res;
    }
}

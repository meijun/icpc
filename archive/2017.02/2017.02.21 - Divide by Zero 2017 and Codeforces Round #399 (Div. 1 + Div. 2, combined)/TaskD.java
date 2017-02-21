package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int k = in.nextInt();
        int q = in.nextInt();
        int N = 7500;
        double[][] dp = new double[N + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= k; j++) {
                if (dp[i][j] > 0) {
                    if (j < k) dp[i + 1][j + 1] += dp[i][j] * (k - j) / k;
                    dp[i + 1][j] += dp[i][j] * j / k;
                }
            }
        }
        while (q-- > 0) {
            double p = in.nextInt() / 2000.0;
            for (int i = 0; i <= N; i++) {
                if (dp[i][k] >= p) {
                    out.println(i);
                    break;
                }
            }
        }
    }
}

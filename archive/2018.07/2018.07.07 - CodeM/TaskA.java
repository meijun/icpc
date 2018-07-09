package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        double[] ds = new double[n];
        for (int i = 0; i < n; i++) {
            ds[i] = Math.log(in.nextInt()) / (i + 1);
        }
        long mod = (long) (1e9 + 7);
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (ds[j] < ds[i]) {
                    dp[i] += dp[j];
                    dp[i] %= mod;
                }
            }
        }
        long res = 0;
        for (long d : dp) res += d;
        res %= mod;
        out.println(res);
    }
}

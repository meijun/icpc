package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    private double[] dp;
    private int[] fr;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        double[] pa = new double[n];
        double[] pb = new double[n];
        dp = new double[n + 1];
        fr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pa[i] = in.nextDouble();
        }
        for (int i = 0; i < n; i++) {
            pb[i] = in.nextDouble();
        }
        double la = 0, ra = 1, lb = 0, rb = 1;
        for (int i = 0; i < 50; i++) {
            double ma = (la + ra) / 2;
            lb = 0;
            rb = 1;
            for (int j = 0; j < 50; j++) {
                double mb = (lb + rb) / 2;
                if (calc2(n, pa, pb, ma, mb).b > b) lb = mb;
                else rb = mb;
            }
            if (calc2(n, pa, pb, ma, rb).a > a) la = ma;
            else ra = ma;
        }
        out.println(dp[n] + ra * a + rb * b);
    }

    private P calc2(int n, double[] pa, double[] pb, double ma, double mb) {
        Arrays.fill(dp, -1e100);
        Arrays.fill(fr, 0);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i + 1] <= dp[i]) {
                dp[i + 1] = dp[i];
                fr[i + 1] = 0;
            }
            if (dp[i + 1] <= dp[i] + pa[i] - ma) {
                dp[i + 1] = dp[i] + pa[i] - ma;
                fr[i + 1] = 1;
            }
            if (dp[i + 1] <= dp[i] + pb[i] - mb) {
                dp[i + 1] = dp[i] + pb[i] - mb;
                fr[i + 1] = 2;
            }
            if (dp[i + 1] <= dp[i] + pa[i] + pb[i] - pa[i] * pb[i] - ma - mb) {
                dp[i + 1] = dp[i] + pa[i] + pb[i] - pa[i] * pb[i] - ma - mb;
                fr[i + 1] = 3;
            }
        }
        int ca = 0;
        int cb = 0;
        for (int i = n; i > 0; i--) {
            if (fr[i] > 1) cb++;
            if (fr[i] % 2 == 1) ca++;
        }
        return new P(ca, cb);
    }

    class P {
        int a;
        int b;

        public P(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "P{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}

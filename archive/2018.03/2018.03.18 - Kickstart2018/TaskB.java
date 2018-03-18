package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            out.printf("Case #%d: ", i + 1);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] vs = new int[n];
        for (int i = 0; i < n; i++) {
            vs[i] = in.nextInt();
        }
        Arrays.sort(vs);
        long[] ss = new long[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) ss[i] = vs[i];
            else ss[i] = ss[i - 1] + vs[i];
        }
        double mean = ss[n - 1] / (double) n;
        for (int i = 0; i < k; i++) {
            int id = $.upperBound(vs, (int) mean);
            mean = (sum(ss, n, id) + mean * id) / (double) n;
        }
        out.printf("%.6f%n", mean);
    }

    private double sum(long[] ss, int n, int id) {
        if (id == n) return 0;
        if (id == 0) return ss[n - 1];
        return ss[n - 1] - ss[id - 1];
    }

    private void solveSmall(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] vs = new int[n];
        for (int i = 0; i < n; i++) {
            vs[i] = in.nextInt();
        }
        if (k > 1) {
            out.println("K>1");
            return ;
        }
        long sum = 0;
        for (int v : vs) sum += v;
        double mean = sum / (double) n;
        if (k == 0 || n == 1) {
            out.printf("%.6f%n", mean);
            return ;
        }
        double mean2 = 0;
        for (int v : vs) {
            mean2 += Math.max(mean, v);
        }
        mean2 /= n;
        out.printf("%.6f%n", mean2);
    }
}

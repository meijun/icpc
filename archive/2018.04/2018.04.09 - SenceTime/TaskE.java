package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] d = new int[n];
        int N = 0;
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
            N += d[i];
        }
        int[][] a = new int[n][];
        double[][] p = new double[n][];
        int[] x = new int[N];
        int xi = 0;
        for (int i = 0; i < n; i++) {
            a[i] = new int[d[i]];
            p[i] = new double[d[i]];
            for (int j = 0; j < d[i]; j++) {
                p[i][j] = in.nextDouble();
                a[i][j] = in.nextInt();
                x[xi++] = a[i][j];
            }
        }
        Arrays.sort(x);
        x = $.unique(x);
        N = x.length;
        SegLite seg = new SegLite(N, 0, Double::sum);
        double res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d[i]; j++) {
                a[i][j] = $.lowerBound(x, a[i][j]);
                res += seg.query(a[i][j] + 1, N) * p[i][j];
            }
            for (int j = 0; j < d[i]; j++) {
                seg.update(a[i][j], p[i][j]);
            }
        }
        out.printf("%.2f%n", res);
    }

    class SegLite {

        public final double init;
        public final DoubleBinaryOperator op;
        public final int N;
        public double[] is;

        public SegLite(int n, double init, DoubleBinaryOperator op) {
            this.init = init;
            this.op = op;
            N = Integer.highestOneBit(n) << 1;
            is = new double[N * 2];
            Arrays.fill(is, N, N + N, init);
            for (int i = N - 1; i > 0; i--) {
                is[i] = this.op.applyAsDouble(is[i << 1], is[i << 1 | 1]);
            }
        }

        public void update(int k, double v) {
            k += N;
            is[k] += v;
            for (k >>= 1; k > 0; k >>= 1) {
                is[k] = op.applyAsDouble(is[k << 1], is[k << 1 | 1]);
            }
        }

        public double query(int s, int t) {
            double left = init;
            double right = init;
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
                left = op.applyAsDouble(left, is[i]);
                s += s & -s;
            }
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
                right = op.applyAsDouble(is[i], right);
                t -= t & -t;
            }
            return op.applyAsDouble(left, right);
        }
    }
}

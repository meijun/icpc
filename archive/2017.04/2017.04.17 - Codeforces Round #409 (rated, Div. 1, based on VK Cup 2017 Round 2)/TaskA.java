package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.IntStream;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        long sum_a = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            sum_a += a[i];
            b[i] = in.nextInt();
        }
        if (sum_a <= p) {
            out.println(-1);
            return;
        }
//        double l = 0, r = 1e11;
//        for (int times = 0; times < 100; times++) {
//            double m = (l + r) / 2;
//            double need = IntStream.range(0, n)
//                    .mapToDouble(i -> Math.max(0, a[i] * m - b[i]))
//                    .sum();
////            for (int i = 0; i < n; i++) {
////                need += Math.max(0, a[i] * m - b[i]);
////            }
//            if (need > m * p) {
//                r = m;
//            } else {
//                l = m;
//            }
//        }
//        out.printf("%.9f%n", l);

        BigDecimal[] A = new BigDecimal[n];
        BigDecimal[] B = new BigDecimal[n];
        for (int i = 0; i < n; i++) {
            A[i] = BigDecimal.valueOf(a[i]);
            B[i] = BigDecimal.valueOf(b[i]);
        }
        BigDecimal L = BigDecimal.ZERO, R = BigDecimal.valueOf(1e11);
        BigDecimal P = BigDecimal.valueOf(p);
        for (int times = 0; times < 100; times++) {
            BigDecimal M = L.add(R).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
            BigDecimal Need = BigDecimal.ZERO;
            for (int i = 0; i < n; i++) {
                BigDecimal tmp = A[i].multiply(M, MathContext.DECIMAL128).subtract(B[i]);
                if (tmp.compareTo(BigDecimal.ZERO) > 0) {
                    Need = Need.add(tmp);
                }
            }
            if (Need.compareTo(M.multiply(P)) > 0) {
                R = M;
            } else {
                L = M;
            }
        }
        out.printf("%.10f%n", L);

    }
}

package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class TaskATestCase {

    Random in = new Random(0);
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 100000;
        int p = 1000000000;
        input.append(n + " " + p + ln);
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < 99999; i++) {
            input.append(10000 + " " + 100000 + ln);
            a[i] = 10000;
            b[i] = 100000;
        }
        input.append(10001 + " " + 100000 + ln);
        a[n - 1] = 10001;
        b[n - 1] = 100000;
        BigDecimal[] A = new BigDecimal[n];
        BigDecimal[] B = new BigDecimal[n];
        for (int i = 0; i < n; i++) {
            A[i] = BigDecimal.valueOf(a[i]);
            B[i] = BigDecimal.valueOf(b[i]);
        }
//        double l = 0, r = 1e11;
        BigDecimal L = BigDecimal.ZERO, R = BigDecimal.valueOf(1e11);
        BigDecimal P = BigDecimal.valueOf(p);
        for (int times = 0; times < 100; times++) {
//            double m = (l + r) / 2;
            BigDecimal M = L.add(R).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
//            double need = 0;
            BigDecimal Need = BigDecimal.ZERO;
            for (int i = 0; i < n; i++) {
//                need += Math.max(0, a[i] * m - b[i]);
                BigDecimal tmp = A[i].multiply(M, MathContext.DECIMAL128).subtract(B[i]);
                if (tmp.compareTo(BigDecimal.ZERO) > 0) {
                    Need = Need.add(tmp);
                }
            }
//            if (need > m * p) {
//                r = m;
//            } else {
//                l = m;
//            }
            if (Need.compareTo(M.multiply(P)) > 0) {
                R = M;
            } else {
                L = M;
            }
        }
//        output.append(String.format("%.10f", l));
        output.append(String.format("%.10f", L));
        return new Test(input.toString(), output.toString());
    }
}

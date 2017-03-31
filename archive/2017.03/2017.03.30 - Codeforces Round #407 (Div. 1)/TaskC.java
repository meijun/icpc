package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int N = 1000;
        boolean[] a = new boolean[N + 1 + N];
        boolean less = false, grater = false;
        for (int i = 0; i < k; i++) {
            a[n - in.nextInt() + N] = true;
        }
        List<Integer> contains = new ArrayList<>();
        for (int i = -N; i <= N; i++) {
            if (a[i + N]) {
                if (i < 0) less = true;
                if (i > 0) grater = true;
                if (i == 0) {
                    out.println(1);
                    return ;
                }
                contains.add(i);
            }
        }
        if (k == 1) {
            out.println(a[0 + N] ? 1 : -1);
            return ;
        }
        if (!less || !grater) {
            out.println(-1);
            return ;
        }
        int cnt = 0;
        for (boolean b : a) if (b) cnt++;
        int M = 1100 + 10;
        BigInteger BN = BigInteger.ONE.shiftLeft(M);
        BigInteger dp = BN;
        BigInteger mod = BigInteger.ONE.shiftLeft(M * 2).subtract(BigInteger.ONE);
        for (int i = 0; i < 1000; i++) {
            BigInteger dpn = BigInteger.ZERO;
            for (int ai : contains) {
                dpn = dpn.or(dp.shiftLeft(ai).and(mod));
            }
            dp = dpn;
            if (dp.and(BN).equals(BN)) {
                out.println(i + 1);
                return;
            }
        }
    }
}

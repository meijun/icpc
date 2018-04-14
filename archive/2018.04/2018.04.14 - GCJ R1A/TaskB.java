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
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int r = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int[] ms = new int[c];
        int[] ss = new int[c];
        int[] ps = new int[c];
        for (int i = 0; i < c; i++) {
            ms[i] = in.nextInt();
            ss[i] = in.nextInt();
            ps[i] = in.nextInt();
        }
        long L = 1, R = (long) 2e18;
        long[] cs = new long[c];
        while (L < R) {
            long M = (L + R) / 2;
            for (int i = 0; i < c; i++) {
                long cnt = Math.max(0, M - ps[i]) / ss[i];
                cs[i] = Math.min(ms[i], cnt);
            }
            Arrays.sort(cs);
            long cnt = 0;
            for (int i = 0; i < r; i++) {
                cnt += cs[i + c - r];
            }
            if (cnt < b) {
                L = M + 1;
            } else {
                R = M;
            }
        }
        out.println(L);
    }
}

package main;

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
        int n = in.nextInt();
        int[] is = new int[n];
        String ss = in.next();
        for (int i = 0; i < n; i++) {
            is[i] = ss.charAt(i) - '0';
        }
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + is[i];
        }
        int res = 0;
        int m = (n + 1) / 2;
        for (int i = 0; i < n - m + 1; i++) {
            res = Math.max(res, pre[i + m] - pre[i]);
        }
        out.println(res);
    }
}

package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int d = in.nextInt();
        int n = in.nextInt();
        double res = 0;
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            int s = in.nextInt();
            double t = (double) (d - k) / s;
            res = Math.max(res, t);
        }
        out.printf("%.16f%n", d / res);
    }
}

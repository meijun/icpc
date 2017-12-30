package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        int[] xs = new int[n];
        for (int i = 0; i < n; i++) xs[i] = in.nextInt();
        double[] ys = new double[n];
        for (int i = 0; i < n; i++) {
            int x = xs[i];
            double y = r;
            for (int j = 0; j < i; j++) {
                y = Math.max(y, calc(xs[j], ys[j], x, r));
            }
            ys[i] = y;
        }
        for (int i = 0; i < n; i++) {
            out.printf("%.10f ", ys[i]);
        }
        out.println();
    }

    private double calc(int x, double y, int x1, int r) {
        int dx = Math.abs(x - x1);
        if (dx > 2 * r) return 0;
        double dy = Math.sqrt(4 * r * r - dx * dx);
        return y + dy;
    }
}

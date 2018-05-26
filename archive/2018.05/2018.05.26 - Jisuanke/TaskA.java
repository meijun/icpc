package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long max = 0;
        long min = Long.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                int u = (int) Math.sqrt(n / i);
                while (n / i % u != 0) u--;
                int v = n / i / u;
                long r = (long) (i + 1) * (u + 2) * (v + 2);
                min = Math.min(min, r);
                max = Math.max(max, r);
            }
        }
        out.println((min - n) + " " + (max - n));
    }
}

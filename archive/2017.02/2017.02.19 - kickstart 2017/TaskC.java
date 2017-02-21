package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int l = in.nextInt();
        int r = in.nextInt();
        int n = Math.min(l, r);
        out.println((long) n * (n + 1) / 2);
    }
}

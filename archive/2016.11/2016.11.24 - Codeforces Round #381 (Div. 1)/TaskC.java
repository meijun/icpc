package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long[] delta = new long[n];
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            delta[i] = k;
            a[i] = k;
            if (i > 0) delta[i] -= delta[i - 1];
        }

    }
}

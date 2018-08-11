package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (i > 0) a[i] += a[i - 1];
        }
        int m = in.nextInt();
        while (m-- > 0) {
            int x = in.nextInt();
            int k = $.lowerBound(a, x);
            out.println(k + 1);
        }
    }
}

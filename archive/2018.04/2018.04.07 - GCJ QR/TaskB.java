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
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        int[] a = new int[(n + 1) / 2];
        int[] b = new int[n / 2];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) a[i / 2] = is[i];
            else b[i / 2] = is[i];
        }
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) is[i] = a[i / 2];
            else is[i] = b[i / 2];
        }
        for (int i = 0; i + 1 < n; i++) {
            if (is[i] > is[i + 1]) {
                out.println(i);
                return ;
            }
        }
        out.println("OK");
    }
}

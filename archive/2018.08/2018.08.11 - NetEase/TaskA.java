package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] t = new int[n];
        int wake = 0;
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
            if (t[i] == 1) {
                wake += a[i];
                a[i] = 0;
            }
        }
        int res = 0;
        int crt = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j < i + k && j < n) {
                crt += a[j];
                j += 1;
            }
            res = Math.max(res, crt);
            crt -= a[i];
        }
        out.println(res + wake);
    }
}

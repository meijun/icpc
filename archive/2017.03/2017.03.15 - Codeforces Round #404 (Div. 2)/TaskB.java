package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] l1 = new int[n];
        int[] r1 = new int[n];
        for (int i = 0; i < n; i++) {
            l1[i] = in.nextInt();
            r1[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] l2 = new int[m];
        int[] r2 = new int[m];
        for (int i = 0; i < m; i++) {
            l2[i] = in.nextInt();
            r2[i] = in.nextInt();
        }
        int res = Math.max(calc(r1, l2), calc(r2, l1));
        out.println(res);
    }

    private int calc(int[] r, int[] l) {
        int min = r[0], max = l[0];
        for (int i : r) min = Math.min(min, i);
        for (int i : l) max = Math.max(max, i);
        return Math.max(0, max - min);
    }
}

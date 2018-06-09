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
        int[] num = new int[]{2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
        int[] x = new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] y = new int[]{0, 0, 1, 2, 0, 1, 2, 0, 1, 2};
        int res = 0;
        int p = 1;
        for (char c : in.next().toCharArray()) {
            int q = num[c - 'A'];
            res += Math.abs(x[p] - x[q]) + Math.abs(y[p] - y[q]);
            p = q;
        }
        out.println(res);
    }
}

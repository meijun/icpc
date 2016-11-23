package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int res = n;
        for (int i = 0; i < m; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            res = Math.min(res, r - l + 1);
        }
        out.println(res);
        for (int i = 0; i < n; i++) {
            out.print((i % res));
            if (i != n - 1) out.print(' ');
        }
        out.println();
    }
}

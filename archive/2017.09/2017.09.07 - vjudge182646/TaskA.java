package main;

import lib.ds.seg.SegSum;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long[] is = new long[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        SegSum seg = new SegSum(is);
        for (;;) {
            char t = in.next().charAt(0);
            if (t == 'E') break;

            int i = in.nextInt() - 1;
            int j = in.nextInt();
            if (t == 'A') {
                seg.update(i, i + 1, 1, j);
            } else if (t == 'S') {
                seg.update(i, i + 1, 1, -j);
            } else if (t == 'Q') {
                out.println(seg.query(i, j));
            }
        }
    }
}

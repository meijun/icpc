package main;

import lib.ds.seg.SegSum;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        SegSum seg = new SegSum(n);
        seg.update(0, n, 0, 1);
        while (q-- > 0) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            int z = in.nextInt();
            seg.update(l, r, 0, z);
        }
        long res = seg.query(0, n);
        out.printf("The total value of the hook is %d.%n", res);
    }
}

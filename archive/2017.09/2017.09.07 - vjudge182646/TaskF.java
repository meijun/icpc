package main;

import lib.ds.seg.SegSum;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskF {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        long[] is = new long[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        SegSum seg = new SegSum(is);
        while (q-- > 0) {
            char t = in.next().charAt(0);
            int a = in.nextInt() - 1;
            int b = in.nextInt();
            if (t == 'Q') {
                out.println(seg.query(a, b));
            } else {
                int c = in.nextInt();
                seg.update(a, b, 1, c);
            }
        }
    }
}

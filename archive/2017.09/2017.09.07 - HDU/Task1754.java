package main;

import lib.ds.seg.SegMax;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task1754 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (in.hasNext()) {
            solve(in, out);
            System.gc();
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        SegMax seg = new SegMax(n);
        for (int i = 0; i < n; i++) {
            seg.update(i, i + 1, 0, in.nextInt());
        }
        while (m-- > 0) {
            char c = in.next().charAt(0);
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            if (c == 'U') {
                seg.update(l, l + 1, 0, r);
            } else {
                out.println(seg.query(l, r));
            }
        }
    }

}

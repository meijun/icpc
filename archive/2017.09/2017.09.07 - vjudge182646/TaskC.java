package main;

import lib.ds.seg.SegLite;
import lib.ds.seg.SegSum;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (in.hasNext()) {
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[2 * n];
        for (int i = 0; i < n; i++) {
            is[i] = is[i + n] = in.nextInt();
        }
        int res = number(n, is, 0);
        int pre = res;
        for (int i = 0; i < n; i++) {
            int x = is[i];
            int num = pre - x + (n - x - 1);
            res = Math.min(res, num);
            pre = num;
        }
        out.println(res);
    }

    private int number(int n, int[] is, int b) {
        SegLite seg = new SegLite(n, 0, Integer::sum);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int x = is[b + i];
            res += seg.query(x + 1, n);
            seg.update(x, 1);
        }
        return res;
    }
}

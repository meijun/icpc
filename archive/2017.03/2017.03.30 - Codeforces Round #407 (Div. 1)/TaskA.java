package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long[] is = new long[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextLong();
        }
        long ans = getAns(is);
        long ans2 = getAns(Arrays.copyOfRange(is, 1, n));
//        $.debug(ans, ans2);
        out.println(Math.max(ans, ans2));
    }

    private long getAns(long[] is) {
        int n = is.length;
        if (n < 2) return 0;
        long pre = 0;
        long res = 0;
        long min = 0;
        long minPre = 0;
        for (int i = 0; i + 1 < n; i += 2) {
//            $.debug(i, pre, res, min, minPre);
            pre += Math.abs(is[i] - is[i + 1]);
            res = Math.max(res, pre - min);
            if (i + 2 < n) {
                pre -= Math.abs(is[i + 1] - is[i + 2]);
                minPre += Math.abs(is[i] - is[i + 1]) - Math.abs(is[i + 1] - is[i + 2]);
                min = Math.min(min, minPre);
            }
        }
//        $.debug(pre, res, min, minPre);
        return res;
    }
}

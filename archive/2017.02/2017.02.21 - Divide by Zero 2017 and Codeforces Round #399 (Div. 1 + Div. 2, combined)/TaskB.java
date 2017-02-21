package main;

import lib.ds.LCA;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    private long[] size;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextLong();
        long l = in.nextLong();
        long r = in.nextLong();
//        $.debug(r - l, r - l + 1);
        size = new long[65];
        for (int i = 1; i < 65; i++) {
            size[i] = size[i - 1] * 2 + 1;
        }
        long res = dfs(n, l, r);
        out.println(res);
    }

    private long dfs(long n, long l, long r) {
        if (n < 2) {
            if (l <= 1 && 1 <= r) return n;
            return 0;
        }
        long s = size[64 - Long.numberOfLeadingZeros(n / 2)];
        long res = 0;
        if (s >= l) res += dfs(n / 2, l, Math.min(s, r));
        if (r >= s + 2) res += dfs(n / 2, Math.max(1, l - s - 1), r - s - 1);
        if (l <= s + 1 && s + 1 <= r) res += n % 2;
//        $.debug(n, l, r, s, res);
        return res;
    }
}

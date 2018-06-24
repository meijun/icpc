package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        char[] s = in.next().toCharArray();
        int n = s.length;
        if (n <= 100) {
            int nn = Integer.parseInt(String.valueOf(s), 2);
            int res = 0;
            for (int l = 0; l <= nn; l++) {
                for (int r = 0; r <= nn; r++) {
                    res = Math.max(res, flr(l, r));
                }
            }
            out.println(res);
            return;
        }
        int z0 = 0;
        int z1 = 0;
        for (int i = 1; i < n; i++) {
            if (s[i] == '1') {
                z0++;
            }
        }
        int zc = z0;
        for (int i = n - 1; i > 0; i--) {
            if (s[i] == '1') {
                zc--;
                z1 = Math.max(z1, zc + (n - i - 1));
            }
        }
        int res = 0;
        for (int i = n; i > 1; i--) {
            res++;
            res += i - 2;
        }
        res += Math.max(z0, z1);
        out.println(res);
    }
    int flr(int l, int r) {
        if (l >= r) return 0;
        int rr = r - (r & -r);
        if (rr >= l) return flr(l, rr) + 1;
        return flr(l, r-1) + 1;
    }
}

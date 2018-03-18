package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            out.printf("Case #%d: ", i + 1);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        long n = in.nextLong();
        char[] cs = Long.toString(n).toCharArray();
        char[] zs = cs.clone();
        char[] es = cs.clone();
        for (int i = 0; i < cs.length; i++) {
            zs[i] = '0';
            es[i] = '8';
        }
        for (int i = 0; i < cs.length; i++) {
            zs[i] = '1';
            if ((cs[i] - '0') % 2 == 1) {
                long res = n;
                if (cs[i] != '9') {
                    long tail = Long.parseLong(String.valueOf(cs, i, cs.length - i));
                    zs[i] = (char) (cs[i] + 1);
                    long up = Long.parseLong(String.valueOf(zs, i, zs.length - i));
                    long tmp = up - tail;
                    res = Math.min(res, tmp);
                }
                long tail = Long.parseLong(String.valueOf(cs, i, cs.length - i));
                es[i] = (char) (cs[i] - 1);
                long down = Long.parseLong(String.valueOf(es, i, es.length - i));
                long tmp = tail - down;
                res = Math.min(res, tmp);
                out.println(res);
                return ;
            }
        }
        out.println(0);
    }

    private void solveSmall(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i <= n; i++) {
            if (fit(n + i) || fit(n - i)) {
                out.println(i);
                return ;
            }
        }
    }

    private boolean fit(int x) {
        for (char c : Integer.toString(x).toCharArray()) {
            if ((c - '0') % 2 == 1) return false;
        }
        return true;
    }
}

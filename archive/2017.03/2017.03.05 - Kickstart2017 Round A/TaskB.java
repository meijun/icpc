package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    private char[] a;
    private char[] b;
    private int n;
    private int m;
    private int ns;
    private int ms;
    private int[] as;
    private int[] bs;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            $.debug(i);
            out.printf("Case #%d: ", i);
            solve(in, out);
        }

    }

    private void solve(Scanner in, PrintWriter out) {
        a = (in.next() + "#").toCharArray();
        b = (in.next() + "#").toCharArray();
        n = a.length;
        m = b.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
//                $.debug(i, j, dp[i][j]);
                if (dp[i][j]) {
                    if (a[i] == b[j]) dp[i + 1][j + 1] = true;
                    if (a[i] == '*') {
                        dp[i + 1][j] = true;
                        for (int k = j, c = 0; k < m; k++) {
                            if (b[k] != '*') c++;
//                            $.debug(i, j, k, c);
                            if (c > 4) break;
                            dp[i + 1][k + 1] = true;
                        }
                    }
                    if (b[j] == '*') {
                        dp[i][j + 1] = true;
                        for (int k = i, c = 0; k < n; k++) {
                            if (a[k] != '*') c++;
                            if (c > 4) break;
                            dp[k + 1][j + 1] = true;
                        }
                    }
//                    $.debug(i, j);
                }
            }
//            $.debug(dp[i]);
        }
//        $.debug(dp[n]);
        out.println(dp[n - 1][m - 1] ? "TRUE" : "FALSE");
    }

    private void solveSmall(Scanner in, PrintWriter out) {
        a = in.next().toCharArray();
        b = in.next().toCharArray();
        n = a.length;
        m = b.length;
        ns = 0;
        ms = 0;
        for (char c : a) if (c == '*') ns++;
        for (char c : b) if (c == '*') ms++;
        as = new int[ns];
        bs = new int[ms];
        if (dfs(ns, ms)) {
            out.println("TRUE");
        } else {
            out.println("FALSE");
        }
    }

    private boolean dfs(int nr, int mr) {
        if (nr == 0 && mr == 0) {
            int len = n - m;
            for (int i : as) len += i - 1;
            for (int i : bs) len -= i - 1;
            if (len != 0) return false;
            int ni = 0;
            int ri = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] == '*') {
                    ri += as[ni++];
                } else {
                    if (!match(ri, a[i])) return false;
                    ri++;
                }
            }
            return true;
        }
        if (nr == 0) {
            for (int i = 0; i < 5; i++) {
                bs[mr-1] = i;
                if (dfs(nr, mr-1)) return true;
            }
        } else {
            for (int i = 0; i < 5; i++) {
                as[nr-1] = i;
                if (dfs(nr-1, mr)) return true;
            }
        }
        return false;
    }

    private boolean match(int ri, char c) {
        int rj = 0;
        int mj = 0;
        for (int j = 0; j < m; j++) {
            if (b[j] == '*') {
                rj += bs[mj++];
                if (rj > ri) return true;
            } else {
                if (rj == ri) {
                    return b[j] == c;
                }
                rj++;
            }
        }
        return false;
    }
}

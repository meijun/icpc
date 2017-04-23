package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] e = new int[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            e[i] = in.nextInt();
            s[i] = in.nextInt();
        }
        int[][] d = new int[n][n];
        BigDecimal INF = BigDecimal.valueOf(1e100);
        BigDecimal[][] g = new BigDecimal[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                $.debug(i, j);
                d[i][j] = in.nextInt();
                if (d[i][j] == -1) {
                    g[i][j] = INF;
                } else {
                    g[i][j] = BigDecimal.valueOf(d[i][j]);
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    BigDecimal add = g[i][k].add(g[k][j]);
                    if (g[i][j].compareTo(add) > 0) {
                        g[i][j] = add;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j].compareTo(BigDecimal.valueOf(e[i])) <= 0) {
                    g[i][j] = g[i][j].divide(BigDecimal.valueOf(s[i]), MathContext.DECIMAL128);
                } else {
                    g[i][j] = INF;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    BigDecimal add = g[i][k].add(g[k][j]);
                    if (g[i][j].compareTo(add) > 0) {
                        g[i][j] = add;
                    }
                }
            }
        }
        for (int i = 0; i < q; i++) {
            int fr = in.nextInt();
            int to = in.nextInt();
            if (i != 0) out.print(" ");
            out.printf("%.16f", g[fr - 1][to - 1]);
        }
        out.println();
    }

    private void bfs(double[] res, int[][] d, int s) {

    }
}

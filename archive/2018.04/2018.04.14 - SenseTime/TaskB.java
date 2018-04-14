package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            char[] cs = in.next().toCharArray();
            for (int j = 0; j < n; j++) {
                g[i][j] = cs[j] == '1';
            }
        }
        BitSet[][] g2 = new BitSet[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g2[i][j] = new BitSet(n);
                for (int k = 0; k < n; k++) {
                    g2[i][j].set(k, g[i][k] && g[k][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j]) {
                    for (int k = 0; k < n; k++) {
                        boolean ikj = g2[i][k].get(j);
                        boolean kji = g2[k][j].get(i);
                        g2[i][k].set(j, false);
                        g2[k][j].set(i, false);
                        if (k != i && k != j && !g2[i][k].isEmpty() && !g2[k][j].isEmpty()) {
                            if (g2[i][k].cardinality() > 1 || g2[k][j].cardinality() > 1 ||
                                    g2[i][k].nextSetBit(0) != g2[k][j].nextSetBit(0)) {
                                out.println("Starfish!");
                                return ;
                            }
                        }
                        g2[i][k].set(j, ikj);
                        g2[k][j].set(i, kji);
                    }
                }
            }
        }
        out.println("Walk Walk");
    }
}

package main;

import lib.graph.MaxFlow;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = in.nextInt();
        }
        int[][] q = new int[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                q[i][j] = in.nextInt();
            }
            Arrays.sort(q[i]);
        }
        int[] ni = new int[n];
        int[] L = new int[n];
        int[] R = new int[n];
        int res = 0;
        for (;;) {
            for (int i = 0; i < n; i++) {
                if (ni[i] == p) {
                    out.println(res);
                    return ;
                }
                int li = 10 * q[i][ni[i]] / (9 * r[i]);
                int ri = (10 * q[i][ni[i]] + (11 * r[i] - 1)) / (11 * r[i]);
                L[i] = ri;
                R[i] = li;
                ni[i]++;
            }
//            $.debug("------------");
//            $.debug(L);
//            $.debug(R);
            for (;;) {
                int rMin = R[0];
                int ri = 0;
                for (int i = 0; i < n; i++) rMin = Math.min(rMin, R[i]);
                for (int i = 0; i < n; i++) if (rMin == R[i]) ri = i;
                int lMax = L[0];
                for (int i = 0; i < n; i++) lMax = Math.max(lMax, L[i]);
                if (lMax > rMin) {
                    if (ni[ri] == p) {
                        out.println(res);
                        return ;
                    }
                    R[ri] = 10 * q[ri][ni[ri]] / (9 * r[ri]);
                    L[ri] = (10 * q[ri][ni[ri]] + (11 * r[ri] - 1)) / (11 * r[ri]);
                    ni[ri]++;
                } else {
                    res++;
                    break;
                }
            }
        }
    }
    class Item {
        int v;
        int i;

        public Item(int v, int i) {
            this.v = v;
            this.i = i;
        }
    }
}

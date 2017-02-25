package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int q = in.nextInt();
        Q[] qs = new Q[q];
        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            qs[i] = new Q(i, l, r);
        }
        Arrays.sort(qs);
        TreeMap<Integer, Integer> min = new TreeMap<>();
        int[] b = new int[m];
        min.put(0, m);
        int qb = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i > 0 && a[i][j] < a[i - 1][j]) {
                    min.merge(b[j], -1, Integer::sum);
                    b[j] = i;
                    min.merge(b[j], 1, Integer::sum);
                }
            }
            int mini;
            for (;;) {
                Map.Entry<Integer, Integer> fe = min.firstEntry();
                if (fe.getValue() == 0) {
                    min.remove(fe.getKey());
                } else {
                    mini = fe.getKey();
                    break;
                }
            }
            while (qb < q && qs[qb].y == i) {
                if (qs[qb].x >= mini) {
                    qs[qb].res = true;
                }
                qb++;
            }
        }
        boolean[] res = new boolean[q];
        for (Q q0 : qs) {
            res[q0.id] = q0.res;
        }
        for (int i = 0; i < q; i++) {
            out.println(res[i] ? "Yes" : "No");
        }
    }
    class Q implements Comparable<Q> {
        int id;
        int x;
        int y;
        boolean res;

        @Override
        public int compareTo(Q o) {
            return Integer.compare(y, o.y);
        }

        public Q(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
}

package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.next().charAt(0) - 'a';
            E e = new E(c, v);
            vs[u].add(e);
        }
        boolean[][][] dp = new boolean[n][n][26];
        boolean[][][] vis = new boolean[n][n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean win = dfs(i, j, 0, dp, vis, vs);
                out.print(win ? 'A' : 'B');
            }
            out.println();
        }
        out.println();
    }

    private boolean dfs(int i, int j, int c, boolean[][][] dp, boolean[][][] vis, V[] vs) {
        if (vis[i][j][c]) return dp[i][j][c];
        boolean win = false;
        for (E e : vs[i]) {
            if (e.c >= c) {
                if (!dfs(j, e.to, e.c, dp, vis, vs)) {
                    win = true;
                    break;
                }
            }
        }
        vis[i][j][c] = true;
        dp[i][j][c] = win;
        return win;
    }

    class V extends ArrayList<E> {

    }

    class E {
        int c;
        int to;

        public E(int c, int to) {
            this.c = c;
            this.to = to;
        }
    }
}

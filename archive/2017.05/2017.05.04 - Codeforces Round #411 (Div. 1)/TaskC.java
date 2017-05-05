package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    private int[] set;
    private int p;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        T[] ts = new T[n];
        int[][] cs = new int[n][];
        for (int i = 0; i < n; i++) {
            ts[i] = new T();
            int s = in.nextInt();
            cs[i] = new int[s];
            for (int j = 0; j < s; j++) cs[i][j] = in.nextInt() - 1;
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            ts[u].add(v);
            ts[v].add(u);
        }
        int[] color = new int[m];
        Arrays.fill(color, -1);
        set = new int[m];
        dfs(0, -1, ts, cs, color);
        int r = 0;
        for (int i : color) r = Math.max(r, i);
        out.println(r + 1);
        for (int i : color) out.print((Math.max(0, i) + 1) + " ");
        out.println();
    }

    private void dfs(int u, int fa, T[] ts, int[][] cs, int[] color) {
        p++;
        for (int c : cs[u]) {
            if (color[c] != -1) {
                set[color[c]] = p;
            }
        }
        int cl = 0;
        for (int c : cs[u]) {
            if (color[c] == -1) {
                while (set[cl] == p) cl++;
                color[c] = cl++;
            }
        }
        for (int v : ts[u]) {
            if (v != fa) {
                dfs(v, u, ts, cs, color);
            }
        }
    }

    class T extends ArrayList<Integer> {

    }
}

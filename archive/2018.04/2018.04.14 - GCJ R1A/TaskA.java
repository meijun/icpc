package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int r = in.nextInt();
        int c = in.nextInt();
        int h = in.nextInt();
        int v = in.nextInt();
        char[][] map = new char[r][];
        for (int i = 0; i < r; i++) {
            map[i] = in.next().toCharArray();
        }
        int[] hs = new int[r];
        int[] vs = new int[c];
        for (int i = 0; i < r; i++) {
            hs[i] = (i == 0 ? 0 : hs[i - 1]);
            for (int j = 0; j < c; j++) {
                hs[i] += (map[i][j] == '@' ? 1 : 0);
            }
        }
        for (int j = 0; j < c; j++) {
            vs[j] = (j == 0 ? 0 : vs[j - 1]);
            for (int i = 0; i < r; i++) {
                vs[j] += (map[i][j] == '@' ? 1 : 0);
            }
        }
        int n = hs[r - 1] / ((h + 1) * (v + 1));
        if (n * (h + 1) * (v + 1) != hs[r - 1]) {
            out.println("IMPOSSIBLE");
            return ;
        }
        int[] hi = new int[h];
        int[] vi = new int[v];
        Arrays.fill(hi, -1);
        Arrays.fill(vi, -1);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < r; j++) {
                if (hs[j] == (i + 1) * n * (v + 1)) {
                    hi[i] = j;
                }
            }
            if (hi[i] == -1) {
                out.println("IMPOSSIBLE");
                return ;
            }
        }
        for (int j = 0; j < v; j++) {
            for (int i = 0; i < c; i++) {
                if (vs[i] == (j + 1) * n * (h + 1)) {
                    vi[j] = i;
                }
            }
            if (vi[j] == -1) {
                out.println("IMPOSSIBLE");
                return ;
            }
        }
        int[][] pre = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                pre[i][j] = (j == 0 ? 0 : pre[i][j - 1]) + (map[i][j] == '@' ? 1 : 0);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                pre[i][j] = (i == 0 ? 0 : pre[i - 1][j]) + pre[i][j];
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < v; j++) {
                if (pre[hi[i]][vi[j]] != (i + 1) * (j + 1) * n) {
                    out.println("IMPOSSIBLE");
                    return ;
                }
            }
        }
        out.println("POSSIBLE");
    }
}

package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] mat = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = in.nextLong();
            }
        }
        long min = Long.MAX_VALUE, max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != 0) {
                    long cnt = dfs(mat, i, j);
                    min = Math.min(min, cnt);
                    max = Math.max(max, cnt);
                }
            }
        }
        out.println(max);
        out.println(min);
    }

    private long dfs(long[][] mat, int i, int j) {
        long cnt = mat[i][j];
        mat[i][j] = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    int x = i + dx;
                    int y = j + dy;
                    if (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) {
                        if (mat[x][y] != 0) {
                            cnt += dfs(mat, x, y);
                        }
                    }
                }
            }
        }
        return cnt;
    }
}

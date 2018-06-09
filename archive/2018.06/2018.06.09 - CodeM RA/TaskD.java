package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        boolean[][] left = new boolean[n][m-1];
        boolean[][] down = new boolean[n-1][m];
        boolean[][] ts = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] cs = in.next().toCharArray();
            for (int j = 0; j < m-1; j++) left[i][j] = cs[j] == '0';
        }
        for (int i = 0; i < n - 1; i++) {
            char[] cs = in.next().toCharArray();
            for (int j = 0; j < m; j++) down[i][j] = cs[j] == '0';
        }
        int x = -1, y = -1;
        for (int i = 0; i < k; i++) {
            x = in.nextInt() - 1;
            y = in.nextInt() - 1;
            ts[x][y] = true;
        }
        long res = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        int xy = x * m + y;
        queue.add(xy);
        int[] w = new int[n * m];
        w[xy] = 0;
        ts[x][y] = false;
        int[] vis = new int[n * m];
        int p = 1;
        vis[xy] = p;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            xy = queue.poll();
            x = xy / m;
            y = xy % m;
            if (ts[x][y]) {
                res += w[xy];

                queue.clear();
                queue.add(xy);
                w[xy] = 0;
                ts[x][y] = false;
                p++;
                vis[xy] = p;
            }
            for (int i = 0; i < 4; i++) {
                int u = x + dx[i];
                int v = y + dy[i];
                int uv = u * m + v;
                if (u >= 0 && v >= 0 && u < n && v < m && vis[uv] != p) {
                    if (cross(x, y, i, left, down)) {
                        vis[uv] = p;
                        w[uv] = w[xy] + 1;
                        queue.add(uv);
                    }
                }
            }
        }
        out.println(res);
    }

    private boolean cross(int x, int y, int i, boolean[][] left, boolean[][] down) {
        if (i == 0) {
            return down[x-1][y];
        } else if (i == 1) {
            return left[x][y];
        } else if (i == 2) {
            return down[x][y];
        } else {
            return left[x][y-1];
        }
    }
}

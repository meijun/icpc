package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = in.nextInt();
            }
        }
        int[][] lb = new int[m][n];
        int[][] rt = new int[m][n];
        int[][] vis = new int[m][n];
        int p = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == n - 1) {
                    dfs(m, n, map, lb, vis, p++, i, j);
                }
                if (j == 0 || i == m - 1) {
                    dfs(m, n, map, rt, vis, p++, i, j);
                }
            }
        }
//        $.debug(rightTop(m, n, map, rt, vis, p++, 2, 1));
//        if (false)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                boolean lbOK = leftBottom(m, n, map, lb, vis, p++, i, j);
                boolean lbOK = lb[i][j] == 1; // leftBottom(m, n, map, lb, vis, p++, i, j);
                if (lbOK) {
//                    boolean rtOK = rightTop(m, n, map, rt, vis, p++, i, j);
                    boolean rtOK = rt[i][j] == 1; // rightTop(m, n, map, rt, vis, p++, i, j);
//                    $.debug(i, j, rtOK);
                    if (rtOK) {
                        out.println(i + " " + j);
                    }
                }
            }
        }
    }

    private void dfs(int m, int n, int[][] map, int[][] lb, int[][] vis, int p, int x, int y) {
        lb[x][y] = 1;
        vis[x][y] = p;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < m && ny < n && vis[nx][ny] != p && map[nx][ny] >= map[x][y]) {
                dfs(m, n, map, lb, vis, p, nx, ny);
            }
        }
    }

    private boolean rightTop(int m, int n, int[][] map, int[][] rt, int[][] vis, int p, int x, int y) {
//        $.debug(x, y, x == 0 || y == n - 1, rt[x][y]);
        if (rt[x][y] != 0) return rt[x][y] == 1;
        vis[x][y] = p;
        boolean res = false;
        if (x == 0 || y == n - 1) res = true;
        else for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < m && ny < n && vis[nx][ny] != p && map[nx][ny] <= map[x][y]) {
                res |= rightTop(m, n, map, rt, vis, p, nx, ny);
                if (res) break;
            }
        }
        rt[x][y] = res ? 1 : -1;
        return res;
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    private boolean leftBottom(int m, int n, int[][] map, int[][] lb, int[][] vis, int p, int x, int y) {
        if (lb[x][y] != 0) return lb[x][y] == 1;
        vis[x][y] = p;
        boolean res = false;
        if (x == m - 1 || y == 0) res = true;
        else for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < m && ny < n && vis[nx][ny] != p && map[nx][ny] <= map[x][y]) {
//                $.debug(nx, ny);
                res |= leftBottom(m, n, map, lb, vis, p, nx, ny);
                if (res) break;
            }
        }
        lb[x][y] = res ? 1 : -1;
        return res;
    }
}

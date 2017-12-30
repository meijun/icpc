package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = in.next().toCharArray();
        }
        char[] p = in.next().toCharArray();
        int res = 0;
        int[] is = new int[]{0, 1, 2, 3};
        do {
            int[] d = mapping(is, p);
            if (fit(n, m, map, d)) res++;
        } while ($.nextPermutation(is));
        out.println(res);
    }

    private boolean fit(int n, int m, char[][] map, int[] d) {
        int x = -1, y = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x != -1) break;
        }
        for (int p : d) {
            if (p == 0) {
                x += 0;
                y += 1;
            } else if (p == 1) {
                x += 0;
                y += -1;
            } else if (p == 2) {
                x += 1;
                y += 0;
            } else {
                x += -1;
                y += 0;
            }
            if (x < 0 || x >= n || y < 0 || y >= m || map[x][y] == '#') return false;
            if (map[x][y] == 'E') return true;
        }
        return false;
    }

    private int[] mapping(int[] is, char[] p) {
        int n = p.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = is[p[i] - '0'];
        }
        return res;
    }
}

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
        int n = in.nextInt();
        int[] is = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
            sum += is[i];
        }
        if (is[0] == 0 || is[n-1] == 0 || sum != n) {
            out.println("IMPOSSIBLE");
            return ;
        }
        int res = 0;
        int[] to = new int[n];
        Arrays.fill(to, -1);
        for (int i = 0; i < n; i++) if (is[i] > 0) {
            for (int j = 0; j < n; j++) {
                if (to[j] == -1) {
                    to[j] = i;
                    is[i] -= 1;
                    res = Math.max(res, Math.abs(j - i));
                    if (is[i] == 0) break;
                }
            }
        }
        out.println(res + 1);
        char[][] map = new char[res][n];
        for (int i = 0; i < res; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = '.';
            }
        }
        for (int i = 0; i < n; i++) {
            int d = to[i] < i ? -1 : 1;
            char r = to[i] < i ? '/' : '\\';
            for (int j = i; j != to[i]; j += d) {
                map[Math.abs(i-j)][j] = r;
            }
        }
        for (int i = 0; i < res; i++) {
            out.println(String.valueOf(map[i]));
        }
        for (int i = 0; i < n; i++) {
            out.print('.');
        }
        out.println();
    }
}

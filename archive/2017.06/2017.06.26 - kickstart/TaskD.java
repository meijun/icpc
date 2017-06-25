package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    private int max;
    private int avg;
    private int mid;
    private int min;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        min = in.nextInt();
        max = in.nextInt();
        avg = in.nextInt();
        mid = in.nextInt();
        if (min <= avg && avg <= max && min <= mid && mid <= max) {
            if (min == max) {
                out.println(1);
            } else {
                max -= min;
                avg -= min;
                mid -= min;
                min -= min;
                if (mid < avg) {
                    mid = max - mid;
                    avg = max - avg;
                }
                int sum = min + max;

            }
        } else {
            out.println("IMPOSSIBLE");
        }
    }
    private void solve2(Scanner in, PrintWriter out) {
        min = in.nextInt();
        max = in.nextInt();
        avg = in.nextInt();
        mid = in.nextInt();
        if (min <= avg && avg <= max && min <= mid && mid <= max) {
            if (min == max) {
                out.println(1);
            } else {
                max -= min;
                avg -= min;
                mid -= min;
                min -= min;
                if (mid < avg) {
                    mid = max - mid;
                    avg = max - avg;
                }
                int res1 = dfs(2, min + max, min, max);
                int res2 = dfs(3, min + max + mid, min, max);
                int res = Math.min(res1, res2);
                if (res <= 14) out.println(res);
                else out.println("IMPOSSIBLE");
            }
        } else {
            out.println("IMPOSSIBLE");
        }
    }

    private int dfs(int n, int s, int a, int b) {
        if (s == n * avg && (n % 2 == 1 || a + b == mid * 2)) return n;
        if (n >= 14) return 15;
        int res = 15;
        for (int i = a; i <= mid; i++) {
            for (int j = b; j >= mid; j--) {
                res = Math.min(res, dfs(n + 2, s + i + j, i, j));
            }
        }
        return res;
    }
}

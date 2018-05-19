package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = 500;
        List<Item> is = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i != 0 || j != 0) {
                    if (i * (i + 1) / 2 <= n && j * (j + 1) / 2 <= n) {
                        is.add(new Item(i, j));
                    }
                }
            }
        }
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(is, in, out);
        }
    }

    private void solve(List<Item> is, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][][] dp = new int[2][n + 1][m + 1];
        for (Item xy : is) {
            for (int i = 0; i <= n; i++) {
                System.arraycopy(dp[0][i], 0, dp[1][i], 0, m + 1);
            }
            for (int i = 0; i <= n; i++) if (i + xy.x <= n) {
                for (int j = 0; j <= m; j++) if (j + xy.y <= m) {
                    dp[1][i + xy.x][j + xy.y] = Math.max(dp[1][i + xy.x][j + xy.y], dp[0][i][j] + 1);
                }
            }
            int[][] t = dp[0]; dp[0] = dp[1]; dp[1] = t;
        }
        out.println(dp[0][n][m]);
    }

    class Item {
        int x;
        int y;

        public Item(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

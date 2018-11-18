package main;

import lib.math.Combination;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public static final long M = (int) (1e9 + 7);
    private long[] f;
    private long[] two;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int max = 2 * 100000;
        f = new long[max + 1];
        for (int i = 0; i <= max; i++) {
            if (i == 0) f[i] = 1;
            else f[i] = f[i - 1] * i % M;
        }
        two = new long[max + 1];
        for (int i = 0; i <= max; i++) {
            if (i == 0) two[i] = 1;
            else two[i] = two[i - 1] * 2 % M;
        }

        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] ps = new long[2 * n + 1][m + 1];
        for (int i = 0; i <= 2 * n; i++) ps[i][0] = 1;
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j <= m; j++) {
                if (ps[i][j] != 0) {
                    if (j != 0) {
                        ps[i + 1][j] += ps[i][j];
                        ps[i + 1][j] %= M;
                    }
                    if (i + 2 <= 2 * n && j + 1 <= m) {
                        ps[i + 2][j + 1] += ps[i][j];
                        ps[i + 2][j + 1] %= M;
                    }
                }
            }
        }
        for (long[] p : ps) {
//            $.debug(p);
        }
        long[] c = Combination.row(m, M);
        long res = f[2*n];
        for (int i = 1; i <= m; i++) {
            long sign = (i % 2 == 1 ? -1 : 1);
            long crt = ps[2 * n][i] * f[i] % M * f[n * 2 - i * 2] % M * (i == m ? 1 : c[i]) % M * two[i] % M ;
//            $.debug(i, crt, ps[2 * n][i], f[i], two[i], f[n * 2 - i * 2], (i == m ? 1 : c[i]));
            res += sign * crt;
            res %= M;
        }
        res += M;
        res %= M;
        out.println(res);
    }
}

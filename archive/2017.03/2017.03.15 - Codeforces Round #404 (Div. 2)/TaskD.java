package main;

import lib.math.Factorial;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        char[] s = in.next().toCharArray();
        int n = s.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = (s[i] == '(' ? 1 : 0);
            if (i > 0) left[i] += left[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            right[i] = (s[i] == ')' ? 1 : 0);
            if (i < n - 1) right[i] += right[i + 1];
        }
        long M = (long) (1e9 + 7);
        long[] f = Factorial.table(n + 1, M);
        long[] ivf = Factorial.invTable(n + 1, M);
        long res = 0;
        for (int i = 1; i < n; i++) {
            if (s[i] == ')') {
                int x = left[i];
                int y = right[i] - 1;
                if (y <= x - 1) {
                    res += f[x + y] * ivf[y + 1] % M * ivf[x - 1] % M;
                } else if (x > 0) {
                    res += f[x + y] * ivf[x - 1] % M * ivf[y + 1] % M;
                }
//                $.debug(i, x, y, res);
            }
        }
        out.println((res % M + M) % M);
    }
}

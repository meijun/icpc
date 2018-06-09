package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n * 2];
        int[] js = new int[n * 2];
        for (int i = 0; i < n; i++) {
            is[n + i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            js[n + i] = in.nextInt();
        }
        int j = n * 2 - 1;
        long res = 0;
        for (int i = n * 2 - 1; i >= 0; i--) {
            if (i + 1 < n * 2 && js[i + 1] > 0) {
                js[2 * n - i - 1] += js[i + 1];
                js[i + 1] = 0;
            }
            while (is[i] > 0) {
                int s = Math.min(is[i], js[j]);
                is[i] -= s;
                js[j] -= s;
                res += (long) (i - j) * s;
                if (js[j] == 0) {
                    j--;
                }
            }
        }
        out.println(res);
    }
}

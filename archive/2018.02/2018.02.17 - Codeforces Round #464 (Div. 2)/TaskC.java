package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;
        int m = f - s;
        int id = 0;
        int max = 0;
        int crt = 0;
        for (int i = 0; i < m; i++) crt += is[i];
        max = crt;
        int res = (n - id + s) % n + 1;
        for (int i = 1; i < n; i++) {
            crt += is[(i - 1 + m) % n];
            crt -= is[i - 1];
            if (crt > max || crt == max && (n - i + s) % n + 1 < res) {
                max = crt;
                id = i;
                res = (n - id + s) % n + 1;
            }
        }
        out.println(res);
    }
}

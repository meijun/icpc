package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task3 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        int[] left = new int[n];
        int[] near = new int[101];
        Arrays.fill(near, -1);
        for (int i = 0; i < n; i++) {
            near[is[i]] = i;
            left[i] = 0;
            for (int j = is[i] - 1; j >= 0; j--) {
                left[i] = Math.max(left[i], near[j] + 1);
            }
        }
        int[] right = new int[n];
        Arrays.fill(near, n);
        for (int i = n - 1; i >= 0; i--) {
            near[is[i]] = i;
            right[i] = n - 1;
            for (int j = is[i] - 1; j >= 0; j--) {
                right[i] = Math.min(right[i], near[j] - 1);
            }
        }
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = (i == 0 ? 0 : pre[i - 1]) + is[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int u = left[i];
            int v = right[i];
            int s = pre[v] - (u == 0 ? 0 : pre[u - 1]);
            res = Math.max(res, s * is[i]);
        }
        out.println(res);
    }
}

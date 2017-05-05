package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int[] p2 = new int[n + 1];
        p2[0] = 1;
        int M = (int) (1e9 + 7);
        for (int i = 1; i <= n; i++) {
            p2[i] = p2[i - 1] + p2[i - 1];
            p2[i] %= M;
        }
        int[] b = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (cs[i] == 'b') b[i] = 1;
            if (i < n - 1) b[i] += b[i + 1];
        }
//        for (int i = 0; i < n; ) {
//            if (cs[i] == 'a') {
//                int j = i;
//                while (j < n && cs[j] == 'a') j++;
//                int k = j;
//                while (k < n && cs[k] == 'b') k++;
//
//            } else i++;
//        }
        int na = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == 'a') {
                res += (long) b[i] * p2[na] % M;
                na++;
            }
        }
        out.println(res % M);
    }
}

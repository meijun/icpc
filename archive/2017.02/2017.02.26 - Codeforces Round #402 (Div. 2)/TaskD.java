package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        char[] s = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        int n = s.length;
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt() - 1;
        }
        int l = 0, r = n;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(s, t, n, is, m)) l = m + 1;
            else r = m;
        }
        out.println(l - 1);
    }

    private boolean fit(char[] s, char[] t, int n, int[] is, int m) {
        boolean[] del = new boolean[n];
        for (int i = 0; i < m; i++) {
            del[is[i]] = true;
        }
        for (int i = 0, j = 0; i < t.length; i++) {
            while (j < n && (del[j] || s[j] != t[i])) j++;
            j++;
            if (j > n) return false;
        }
        return true;
    }
}

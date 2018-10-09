package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n];
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x >= n) ok = false;
            if (x < n) is[x]++;
        }
        if (n == 1) {
            out.println(is[0] == 1 ? "Yes" : "No");
            return ;
        }
        if (!ok) {
            out.println("No");
            return ;
        }
        long s = 0;
        for (int i = 0; i < n; i++) s += i * is[i];
        if (s != (n - 1) * 2) {
            out.println("No");
            return ;
        }
        s -= is[1];
        if (s - 2 * n + is[1] + 2 != 0) {
            out.println("No");
            return ;
        }
        out.println("Yes");
    }
}

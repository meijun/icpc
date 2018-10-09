package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        int s = 0;
        for (int i : is) s += i;
        boolean[] dp = new boolean[s + 1];
        dp[0] = true;
        for (int i : is) {
            for (int j = s; j >= 0; j--) {
                if (dp[j]) dp[j + i] = true;
            }
        }
        int r = s;
        for (int i = s; i >= x; i--) if (dp[i]) r = i;
        out.println(r);
    }
}

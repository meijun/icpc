package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k1 = in.nextInt();
        int[] s1 = new int[k1];
        for (int i = 0; i < k1; i++) {
            s1[i] = in.nextInt();
        }
        int k2 = in.nextInt();
        int[] s2 = new int[k2];
        for (int i = 0; i < k2; i++) {
            s2[i] = in.nextInt();
        }
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[n - 1] = -1;
        dp2[n - 1] = -1;
        for (;;) {
            boolean updated = false;
            for (int i = n - 2; i >= 0; i--) {
                boolean allWin = true;
                if (dp1[i] == 0) for (int s : s1) {
                    if (dp2[(i + s) % n] == -1) {
                        dp1[i] = 1;
                        updated = true;
                        break;
                    } else if (dp2[(i + s) % n] == 0) {
                        allWin = false;
                    }
                }
                if (dp1[i] == 0 && allWin) {
                    dp1[i] = -1;
                    updated = true;
                }

                boolean allWin2 = true;
                if (dp2[i] == 0) for (int s : s2) {
                    if (dp1[(i + s) % n] == -1) {
                        dp2[i] = 1;
                        updated = true;
                        break;
                    } else if (dp1[(i + s) % n] == 0) {
                        allWin2 = false;
                    }
                }
                if (dp2[i] == 0 && allWin2) {
                    dp2[i] = -1;
                    updated = true;
                }
            }
            if (!updated) break;
        }
        for (int i = 0; i < n - 1; i++) {
            out.print((dp1[i] == 1 ? "Win" : dp1[i] == 0 ? "Loop" : "Lose") + " ");
        }
        out.println();
        for (int i = 0; i < n - 1; i++) {
            out.print((dp2[i] == 1 ? "Win" : dp2[i] == 0 ? "Loop" : "Lose") + " ");
        }
        out.println();
    }
}

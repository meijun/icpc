package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt() - 1;
        }
        for (int i = 0; i < n; i++) {
            int a = i;
            int b = is[a];
            int c = is[b];
            int d = is[c];
            if (a == d) {
                out.println("YES");
                return;
            }
        }
        out.println("NO");
    }
}

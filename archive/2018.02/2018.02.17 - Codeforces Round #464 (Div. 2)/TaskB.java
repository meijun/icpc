package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextLong();
        int k = in.nextInt();
        long[] is = new long[k];
        for (int i = 0; i < k; i++) {
            is[i] = in.nextLong();
        }
        int ri = 0;
        for (int i = 0; i < k; i++) {
            if (n % is[ri] > n % is[i]) {
                ri = i;
            }
        }
        out.println((ri + 1) + " " + (n / is[ri]));
    }
}

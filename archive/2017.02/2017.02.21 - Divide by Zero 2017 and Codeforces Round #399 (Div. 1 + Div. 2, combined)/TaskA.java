package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : is) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int cnt = 0;
        for (int i : is) {
            if (i != min && i != max) cnt++;
        }
        out.println(cnt);
    }
}

package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        for (int i = 0; i < k; i++) {
            Arrays.sort(is);
            for (int j = 0; j < n; j += 2) {
                is[j] ^= x;
            }
            $.debug(is);
        }
        int min = is[0], max = is[0];
        for (int i : is) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        out.println(max + " " + min);
    }
}

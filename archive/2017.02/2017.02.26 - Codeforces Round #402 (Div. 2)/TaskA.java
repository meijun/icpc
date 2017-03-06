package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] as = getInts(in, n);
        int[] bs = getInts(in, n);
        int[] ca = new int[5];
        int[] cb = new int[5];
        for (int i : as) ca[i]++;
        for (int i : bs) cb[i]++;
        int c = 0;
        int r = 0;
        int m = n;
        for (int i = 0; i < 5; i++) {
            if ((ca[i] + cb[i]) % 2 != 0) {
                out.println(-1);
                return ;
            }
            int should = (ca[i] + cb[i]) / 2;
            r += Math.max(0, should - ca[i]);
        }
//        $.debug(r, m);
        out.println(Math.max(r, 0));
    }

    private int[] getInts(Scanner in, int n) {
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt() - 1;
        }
        return as;
    }
}

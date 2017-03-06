package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            $.debug(i);
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int r = in.nextInt() - 1;
        int c = in.nextInt() - 1;
        long M = (long) (1e9 + 7);
        if (r < c) { int t = r; r = c; c = t; }
        long res = 0;
        for (int i = 1; i <= c; i++) {
            res += (long) (r - c + i) * i % M * (c - i + 1) % M;
            res %= M;
        }
//        for (int i = 1; i <= c; i++) {
////            res += (r - c + i) * i * (c - i);
//        }
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//
//            }
//        }
        out.println(res);
    }
}

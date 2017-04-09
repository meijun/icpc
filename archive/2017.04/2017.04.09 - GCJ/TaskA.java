package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        char[] r = in.next().toCharArray();
        int n = in.nextInt();
        int c = 0;
        for (int i = 0; i < r.length; i++) {
            if (r[i] == '-') {
                c++;
                if (i + n > r.length) {
                    out.println("IMPOSSIBLE");
                    return;
                }
                for (int j = 0; j < n; j++) {
                    r[i + j] = (r[i + j] == '+' ? '-' : '+');
                }
            }
        }
        out.println(c);
    }
}

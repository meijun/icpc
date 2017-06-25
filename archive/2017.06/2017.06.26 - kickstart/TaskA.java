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
        String s = in.next();
        if (s.length() % 2 == 0) {
            int n = s.length();
            int[] res = new int[n];
            for (int i = 0; i < n; i += 2) {
                int b = (i - 1 < 0 ? 0 : res[i - 1]);
                int e = (s.charAt(i) - 'A' - b + 26) % 26;
                if (i + 1 < n) res[i + 1] = e;
            }
            for (int i = n - 1; i >= 0; i -= 2) {
                int e = (i + 1 >= n ? 0 : res[i + 1]);
                int b = (s.charAt(i) - 'A' - e + 26) % 26;
                if (i - 1 >= 0) res[i - 1] = b;
            }
            StringBuilder r = new StringBuilder();
            for (int i = 0; i < n; i++) r.append((char) (res[i] + 'A'));
            out.println(r.toString());
        } else {
            out.println("AMBIGUOUS");
        }
    }
}

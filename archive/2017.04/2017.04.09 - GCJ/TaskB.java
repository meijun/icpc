package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        long n = in.nextLong();
        char[] s = Long.toString(n).toCharArray();
        for (int times = 0; times < 100; times++) {
            for (int i = 0; i < s.length; i++) {
                if (i + 1 < s.length && s[i] > s[i + 1]) {
                    s[i]--;
                    for (int j = i + 1; j < s.length; j++) {
                        s[j] = '9';
                    }
                }
            }
        }
        out.println(Long.parseLong(String.valueOf(s)));
    }
}

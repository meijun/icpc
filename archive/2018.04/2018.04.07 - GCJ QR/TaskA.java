package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int d = in.nextInt();
        char[] p = in.next().toCharArray();
        for (int cnt = 0; ; cnt++) {
            if (damage(p) <= d) {
                out.println(cnt);
                return ;
            }
            if (!swap(p)) {
                out.println("IMPOSSIBLE");
                return ;
            }
        }
    }

    private boolean swap(char[] p) {
        boolean s = false;
        for (int i = p.length - 1; i >= 0; i--) {
            if (p[i] == 'S') {
                s = true;
            } else {
                if (s) {
                    char t = p[i]; p[i] = p[i + 1]; p[i + 1] = t;
                    return true;
                }
            }
        }
        return false;
    }

    private int damage(char[] p) {
        int res = 0;
        int d = 1;
        for (char c : p) {
            if (c == 'C') {
                d *= 2;
            } else {
                res += d;
            }
        }
        return res;
    }
}

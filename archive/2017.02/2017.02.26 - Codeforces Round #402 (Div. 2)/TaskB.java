package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        if (n == 0) {
            out.println(0);
            return ;
        }
        char[] cs = Integer.toString(n).toCharArray();
        int c = 0;
        int nc = 0;
        for (int i = cs.length - 1; i > 0; i--) {
            if (cs[i] == '0') {
                c++;
            } else {
                nc++;
            }
            if (c == k) {
                out.println(nc);
                return;
            }
        }
        out.println(cs.length - 1);
    }
}

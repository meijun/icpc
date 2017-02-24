package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String a = in.next();
        String b = in.next();
        int n = in.nextInt();
        out.println(a + " " + b);
        for (int i = 0; i < n; i++) {
            String x = in.next();
            String y = in.next();
            if (a.equals(x)) {
                a = y;
            } else if (b.equals(x)) {
                b = y;
            }
            out.println(a + " " + b);
        }
    }
}

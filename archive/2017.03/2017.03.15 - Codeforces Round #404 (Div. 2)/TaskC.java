package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextLong();
        long m = in.nextLong();
        if (n <= m) {
            out.println(n);
        } else {
            long y = (n - m) * 2;
            long x = (long) Math.sqrt(y);
            while (x * x + x >= y) x--;
            while (x * x + x < y) x++;
            out.println(x + m);
        }
    }
}

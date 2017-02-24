package main;

import lib.math.GCD;
import lib.math.Multiplicative;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextLong();
        long k = in.nextLong();
        long res = n;
        boolean f = false;
        for (int i = 0; i < k + 1; i++) {
            if (f) {
                res = fun(res);
            } else {

            }
            f ^= true;
            if (res == 1) break;
        }
        out.println(res % 1000000007);
    }

    private long fun(long n) {
        return Multiplicative.value(n, Multiplicative.PHI);
    }
}

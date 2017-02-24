package main;

import lib.math.Prime;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        if (n < 3) {
            out.println(1);
            for (int i = 0; i < n; i++) {
                out.print(1 + " ");
            }
            return ;
        }
        boolean[] pr = Prime.table(n + 2);
        out.println(2);
        for (int i = 2; i <= n + 1; i++) {
            if (pr[i]) {
                out.print(1 + " ");
            } else {
                out.print(2 + " ");
            }
        }
    }
}

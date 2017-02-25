package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        n %= 6;
        for (int b = 0; b < 3; b++) {
            int s = b;
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    if (s == 0) s = 1;
                    else if (s == 1) s = 0;
                } else {
                    if (s == 1) s = 2;
                    else if (s == 2) s = 1;
                }
            }
            if (s == x) {
                out.println(b);
                return ;
            }
        }
    }
}

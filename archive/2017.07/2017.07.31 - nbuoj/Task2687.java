package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task2687 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int x = in.nextInt();
        String a = in.next();
        int r = in.nextInt();
        int v = Integer.valueOf(a, x);
        out.println(Integer.toString(v, r).toUpperCase());
    }
}

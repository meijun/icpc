package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int res = 0;
        for (char c: in.next().toCharArray()) {
            if ("13579aeiou".indexOf(c) != -1) {
                res++;
            }
        }
        out.println(res);
    }
}

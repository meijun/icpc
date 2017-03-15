package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] cnt = new int[128];
        cnt['T'] = 4;
        cnt['C'] = 6;
        cnt['O'] = 8;
        cnt['D'] = 12;
        cnt['I'] = 20;
        int res = 0;
        while (n-- > 0) {
            res += cnt[in.next().charAt(0)];
        }
        out.println(res);
    }
}

package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.next();
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        int res = (n + 1 + 1) * (n + 1) / 2;
        res -= n;
        for (int x : cnt) {
            res -= x * (x - 1) / 2;
        }
        out.println(res);
    }
}
